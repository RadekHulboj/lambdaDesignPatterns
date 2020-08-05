package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<E, S> {
    S event(E evNumber);

    static <E, S> StateMachine<E, S> build(Consumer<StateBuilder<E, S>> consumer) {
        HashMap<E, S> map = new HashMap<>();
        consumer.accept((e, s) -> {
            map.put(e, s);
        });
        return evNumber -> map.get(evNumber);
    }

    static <E, S> ConsumerStateBuilder<E, S> init(E event, S state) {
        return stateBuilder -> stateBuilder.register(event, state);
    }

    interface ConsumerStateBuilder<E, S> extends Consumer<StateBuilder<E, S>> {
        default ConsumerStateBuilder<E, S> transition(E event, S state) {
            return stateBuilder -> {
                this.accept((e, s) -> stateBuilder.register(e, s));
                stateBuilder.register(event, state);
            };
        }
    }
}
