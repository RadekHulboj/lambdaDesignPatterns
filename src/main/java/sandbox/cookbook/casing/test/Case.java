package sandbox.cookbook.casing.test;

import java.util.function.Supplier;


//TODO rahu: how to change to interface :), 2 fields and by chaining I will grab this how?
// pewnie w of zrobie zbieracza consumer<zbieraj> i tam z mapy wyciagne te dane zamiast z case... i to bedzie roznica :)
public final class Case<V> {
    private V value;
    private Supplier<Object> supplier;

    private Case(V value, Supplier<Object> supplier) {
        this.value = value;
        this.supplier = supplier;
    }

    static <V> Case<V> $(V value, Supplier<Object> supplier) {
        return new Case<>(value, supplier);
    }

    public Supplier<Object> getSupplier() {
        return supplier;
    }

    V getValue() {
        return value;
    }
}
