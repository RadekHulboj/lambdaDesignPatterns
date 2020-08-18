package sandbox.cookbook.builder;

import eu.hulboj.model.IPerson;

import java.util.function.Supplier;

@FunctionalInterface
public interface BuilderSupplier<T extends IPerson> extends Supplier<T> {
    default T receive() {
        return get();
    }
}
