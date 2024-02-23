package dp.builder.nosetters;

import dp.builder.IBuilder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
@FunctionalInterface
interface ICatBuilder<I extends CatBuilder, C extends Cat> {
    Supplier<I> supplier();
    static <S extends CatBuilder, C extends Cat> ICatBuilder<S, C> of(Supplier<S> instance) {
        return () -> instance;
    }
    default <V> ICatBuilder<I, C> with(BiConsumer<I, V> biConsumer, V v) {
        return () -> () -> {
            I inst = supplier().get();
            biConsumer.accept(inst, v);
            return inst;
        };
    }
    default C build() {
        return (C) Cat.build(supplier().get());
    }

}
