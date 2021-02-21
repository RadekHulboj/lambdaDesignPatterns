package cookbook.cache;

import java.util.Objects;
import java.util.function.Supplier;

public interface Cache<T> {
    T getValue();

    class Holder<T> {
        T value;
        Supplier<T> supplier = null;
    }

    static <T> Cache<T> of(Supplier<T> supplier) {
        Holder<T> holder = new Holder<>();
        return () -> {
            if(Objects.isNull(holder.supplier)) {
                holder.supplier = supplier;
                holder.value = supplier.get();
            }
            return holder.value;
        };
    }
}
