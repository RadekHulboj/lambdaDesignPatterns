package dp.builder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface IBuilder<I> {

    Supplier<I> supplier();

    static <I> IBuilder<I> of(Supplier<I> instance) {
        return () -> instance;
    }

    default <V> IBuilder<I> with(BiConsumer<I, V> method, V v) {
        return () -> () -> {
            I person = supplier().get();
            method.accept(person, v);
            return person;
        };
    }

    default I build() {
        return supplier().get();
    }
}
