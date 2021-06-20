package sandbox.cookbook.switcher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface SwitcherCase<T, V> {
    Supplier<V> switcher(T t1, T t2);

    static <T,V> SwitcherCase<T, V> of(ConsumerConditioner<T, V> consumer) {
        Map<PredicateConsumer2<T>, Supplier<V>> map = new HashMap<>();
        consumer.accept(map::put);
        return (t1, t2) ->
            map.entrySet()
                    .stream()
                    .filter(p -> p.getKey().test(t1, t2))
                    .findFirst()
                    .get() // https://stackoverflow.com/questions/57310660/how-to-return-hashmap-from-java-8-orelse-orelseget-operator
                    .getValue();
    }

    interface ConsumerConditioner<T, V> extends Consumer<Conditioner<T, V>> {
        static <T,V> ConsumerConditioner<T, V> build() {
            return tvConditioner -> {};
        }
        default ConsumerConditioner<T, V> $case(PredicateConsumer2<T> p, Supplier<V> s) {
            return tvConditioner -> {
                accept(tvConditioner);
                tvConditioner.$case(p, s);
            };
        }
    }

    interface Conditioner<T, V> {
        void $case(PredicateConsumer2<T> p, Supplier<V> s);
    }

    interface PredicateConsumer2<T> {
        Boolean test(T t1, T t2);
    }
}
