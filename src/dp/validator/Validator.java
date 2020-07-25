package dp.validator;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Validator<T> {

    ValidatorSupplier on(T p);

    default Validator<T> thenValidating(Predicate<T> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                if (predicate.test(p)) {
                    return () -> p;
                } else {
                    return () -> {
                        ValidationException exception = new ValidationException("The object is not valid");
                        exception.addSuppressed(new IllegalArgumentException(errorMessage));
                        throw exception;
                    };
                }
            } catch (ValidationException validationException) {
                if (predicate.test(p)) {
                    return () -> {
                        throw validationException;
                    };
                } else {
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                    return () -> {
                        throw validationException;
                    };
                }
            }
        };
    }

    static <T> Validator<T> validating(Predicate<T> predicate, String errorMessage) { // v1
        return p -> {
            if (predicate.test(p)) {
                return () -> p;
            } else {
                return () -> {
                    ValidationException exception = new ValidationException("The object is not valid");
                    exception.addSuppressed(new IllegalArgumentException(errorMessage));
                    throw exception;
                };
            }
        };
    }

    interface ValidatorSupplier<T> extends Supplier<T> {
        default T validate() {
            return get();
        }
    }

    class ValidationException extends RuntimeException {
        public ValidationException(String errorMessage) {
            super(errorMessage);
        }
    }
}

