package dp.factory;

import java.util.function.Supplier;

public interface FactorySupplier<T> {
    T instance();

    static <T> FactorySupplier<T> create(Supplier<T> supplier) {
        return supplier::get;
    }
}
