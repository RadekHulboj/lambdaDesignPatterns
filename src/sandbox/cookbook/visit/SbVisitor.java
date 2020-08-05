package sandbox.cookbook.visit;

import eu.hulboj.model.Bicycle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public interface SbVisitor<R> {

    R visit(Bicycle bicycle);

    static <R> SbVisitor<R> of(Consumer<SbVisitorBuilder<R>> consumer) {
        Map<Class<?>, Function<Object, R>> map = new HashMap<>();
        consumer.accept((key, value) -> map.put(key, value));
        return obj -> map.get(obj.getClass()).apply(obj);

    }

    static <R> ConsumerBuilder<SbVisitorBuilder<R>> build() {
        return b -> { };
    }

    @FunctionalInterface
    interface ConsumerBuilder<B> extends Consumer<B> {

        default ConsumerBuilder<B> forType(Class<Bicycle> objClass) {
            return b -> {};
        }

        default ConsumerBuilder<B> execution(Function<Object, String> function) {
            return b -> { };
        }
    }
}
