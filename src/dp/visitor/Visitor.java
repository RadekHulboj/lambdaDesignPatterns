package dp.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface Visitor<R> {

    R visit(Object o);

    static <R> Visitor<R> of(Consumer<VisitorBuilder<R>> consumer) {
        Map<Class<?>, Function<Object, R>> registry = new HashMap<>();
        VisitorBuilder<R> rVisitorBuilder = new VisitorBuilder<R>() {
            @Override
            public <T> void register(Class<T> type, Function<T, R> function) {
                    registry.put(type, function.compose(type::cast));
            }
        };
        consumer.accept(rVisitorBuilder);
        return o -> registry.get(o.getClass()).apply(o);
    }

    static <R> ConsumerVisitorBuilder<R> visit() {
        return rVisitorBuilder -> {};
    }

    @FunctionalInterface
    interface ConsumerVisitorBuilder<R> extends Consumer<VisitorBuilder<R>> {
        default <T> Helper<T, R> forType(Class<T> type) {
            return index -> index == 0 ? this : type;
        }

        default ConsumerVisitorBuilder<R> andThen(ConsumerVisitorBuilder<R> after) {
            return b -> {
                this.accept(b);
                after.accept(b);
            };
        }
    }

    @FunctionalInterface
    interface Helper<T, R> {
        Object get(int index);

        default Class<T> type() {
            return (Class<T>) get(1);
        }

        default ConsumerVisitorBuilder<R> previousConsumer() {
            return (ConsumerVisitorBuilder<R>) get(0);
        }

        default ConsumerVisitorBuilder<R> visitation(Function<T, R> function) {
            return previousConsumer().andThen(
                    rVisitorBuilder -> rVisitorBuilder.register(type(), function)
            );
        }
    }
}
