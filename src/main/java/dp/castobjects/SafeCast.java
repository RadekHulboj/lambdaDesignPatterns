package dp.castobjects;

import java.util.Optional;

@FunctionalInterface
public interface SafeCast<T> {
    T cast(Object castTo);

    static <T> SafeCast<T> castTo(Class<T> clazz) {
        return castTo -> Optional.of(castTo)
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .get();
    }
}
