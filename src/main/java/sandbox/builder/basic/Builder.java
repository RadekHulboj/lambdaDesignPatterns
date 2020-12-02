package sandbox.builder.basic;

import eu.hulboj.model.IPerson;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

@FunctionalInterface
public interface Builder<T extends IPerson> {

    BuilderSupplier<T> supplier();

    static <T extends IPerson> Builder build(BuilderSupplier<T> personSupplier) {
        return () -> personSupplier::receive;
    }

    default Builder<T> setName(Supplier<String> stringSupplier) {
        return () -> () -> {
            T p = supplier().receive();
            p.setName(stringSupplier.get());
            return p;
        };
    }

    default Builder<T> setAge(IntSupplier integerSupplier) {
        return () -> () -> {
            T p = supplier().receive();
            p.setAge(integerSupplier.getAsInt());
            return p;
        };
    }

    default T build() {
        return supplier().receive();
    }
}
