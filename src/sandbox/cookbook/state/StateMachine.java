package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<E, S> {
    S event(E evNumber);

    static <E, S> StateMachine<E, S> build(Consumer<StateBuilder<E, S>> consumer) {
        HashMap<E, S> event2State = new HashMap<>();
        HashMap<E, Consumer<S>> event2Function = new HashMap<>();
        consumer.accept((e, s, f) -> {
            event2State.put(e, s);
            event2Function.put(e, f);
        });
        return evNumber -> {
            S s = event2State.get(evNumber);
            event2Function.get(evNumber).accept(s);
            return s;
        };
    }

    static <E, S> ConsumerStateBuilder<E, S> init(E event, S state, Consumer<S> function) {
        return stateBuilder -> stateBuilder.register(event, state, function);
    }

    interface ConsumerStateBuilder<E, S> extends Consumer<StateBuilder<E, S>> {
        default ConsumerStateBuilder<E, S> transition(E event, S state, Consumer<S> function) {
            return stateBuilder -> {
                this.accept((e, s, f) -> stateBuilder.register(e, s, f));
                stateBuilder.register(event, state, function);
            };
        }
    }
}
