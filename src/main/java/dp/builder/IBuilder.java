package dp.builder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface IBuilder<I> {
    Supplier<I> supplier();
    @FunctionalInterface
    interface TriConsumer<I, P1, P2> {
        void accept (I inst, P1 param1, P2 param2);
    }
    static <I> IBuilder<I> of(Supplier<I> instance) {
        return () -> instance;
    }
    default <V> IBuilder<I> with(BiConsumer<I, V> biConsumer, V v) {
        return () -> () -> {
            I inst = supplier().get();
            biConsumer.accept(inst, v);
            return inst;
        };
    }
    default <V1, V2> IBuilder<I> with(TriConsumer<I, V1, V2> triConsumer, V1 v1, V2 v2) {
        return () -> () -> {
            I inst = supplier().get();
            triConsumer.accept(inst, v1, v2);
            return inst;
        };
    }
    default I build() {
        return supplier().get();
    }
}

