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
    default Validator<T> match(Predicate<T> predicate, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                return getValidatorSupplier(p, predicate.test(p));
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
    static <T> Validator<T> create() {
        return p -> () -> p;
    }
    static <T> ValidatorSupplier<T> getValidatorSupplier(T p, boolean test) {
        if (test) {
            return () -> p;
        }
        throw new ValidationException("The object is not valid by:");
    }
}

