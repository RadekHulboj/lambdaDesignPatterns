package dp.validator;

import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
public interface Validator<T> {

    ValidatorSupplier<T> on(T p);

    @FunctionalInterface
    interface ValidatorSupplier<T> extends Supplier<T> {
        default void validate() {
            get();
        }
    }

    class ValidationException extends RuntimeException {
        ValidationException(String errorMessage) {
            super(errorMessage);
        }
    }

    default Validator<T> then(Predicate<T> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                return getValidatorSupplier(errorMessage, p, predicate.test(p));
            } catch (ValidationException validationException) {
                if (!predicate.test(p)) {
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                }
                return () -> {
                    throw validationException;
                };
            }
        };
    }

    static <T> Validator<T> validator(Predicate<T> predicate, String errorMessage) {
        return p -> getValidatorSupplier(errorMessage, p, predicate.test(p));
    }

    static <T> ValidatorSupplier<T> getValidatorSupplier(String errorMessage, T p, boolean test) {
        if (test) {
            return () -> p;
        }
        ValidationException validationException = new ValidationException("The object is not valid by:");
        validationException.addSuppressed(new IllegalArgumentException(errorMessage));
        throw validationException;
    }
}

