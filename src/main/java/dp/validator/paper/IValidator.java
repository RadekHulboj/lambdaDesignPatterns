package dp.validator.paper;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface IValidator<O> {
    Supplier<O> on(O object);

    class ValidationException extends RuntimeException {
        ValidationException(String errorMessage) {
            super(errorMessage);
        }
    }

    static <O> IValidator<O> init() {
        return obj -> () -> obj;
    }

    default IValidator<O> match(Predicate<O> predicate, String message) {
        return obj -> {
            try {
                on(obj);
                if(!predicate.test(obj)) {
                    throw new ValidationException("The root of problem:");
                }
            } catch (ValidationException validationException) {
                if(!predicate.test(obj)) {
                    validationException.addSuppressed(new IllegalArgumentException(message));
                }
                throw validationException;
            }
            return () -> obj;
        };
    }
}
