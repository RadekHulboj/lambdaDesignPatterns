package sandbox.cookbook.casing.test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@FunctionalInterface
public interface Switcher<V> {
    V getSearchValue();

    static <V> Switcher<V> Match(V searchValue) {
        return () -> searchValue;
    }

    default Optional<Object> of(Case<V>... cases) {
        Optional<Case<V>> first = Arrays.stream(cases)
                .filter(c1 -> c1.getValue() == getSearchValue() || Objects.isNull(c1.getValue()))
                .findFirst();
        return Optional.of(first.get().getSupplier().get());
    }
}
