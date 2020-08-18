package cookbook.cache;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Wenn einige Methoden mehrmals aufgerufen sind, kann diese Klasse verwendet werden,
 * um die Methode nur einmal aufzurufen. Nächste Aufrufe der Methode werden vermieden und
 * der Rückgabewert wird aus dem Cache ermittelt.
 *
 * @param <V> Der generische Parameter, der zwichengespeichert werden soll.
 */
public final class CacheUtils<V> implements Supplier<V> {
    /**
     * Behält das Ergebnis des V-Paramerers bei.
     */
    private Supplier<? extends V> mSupplier;
    /**
     * gecached Wert
     */
    private V mValue;

    /**
     * Die Klasse arbeitet als Utility Klasse ( kein öffentlicher Konstrukteur )
     *
     * @param supplier Supplier
     */
    private CacheUtils(Supplier<? extends V> supplier) {
        this.mSupplier = supplier;
    }

    /**
     * @param supplier Supplier, die zwischengespeichert werden soll
     * @return Supplier mit Rückgabewert
     */
    public static <V> Supplier<V> memoize(Supplier<V> supplier) {
        return supplier instanceof CacheUtils ? (CacheUtils) supplier : new CacheUtils<>(supplier);
    }

    @Override
    public V get() {
        return Objects.isNull(mSupplier) ? this.mValue : this.computeValueIfSupplier();
    }

    /**
     * @return Wert aus dem Supplier
     */
    private synchronized V computeValueIfSupplier() {
        Supplier<? extends V> supp = this.mSupplier;
        if (Objects.nonNull(supp)) {
            this.mValue = supp.get();
            this.mSupplier = null;
        }
        return this.mValue;
    }
}
