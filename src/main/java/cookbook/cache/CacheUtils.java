package cookbook.cache;

import java.util.Objects;
import java.util.function.Supplier;


public final class CacheUtils<V> implements Supplier<V> {

    private Supplier<? extends V> mSupplier;

    private V mValue;


    private CacheUtils(Supplier<? extends V> supplier) {
        this.mSupplier = supplier;
    }


    public static <V> Supplier<V> memoize(Supplier<V> supplier) {
        return supplier instanceof CacheUtils ? (CacheUtils) supplier : new CacheUtils<>(supplier);
    }

    @Override
    public V get() {
        return Objects.isNull(mSupplier) ? this.mValue : this.computeValueIfSupplier();
    }


    private synchronized V computeValueIfSupplier() {
        Supplier<? extends V> supp = this.mSupplier;
        if (Objects.nonNull(supp)) {
            this.mValue = supp.get();
            this.mSupplier = null;
        }
        return this.mValue;
    }
}
