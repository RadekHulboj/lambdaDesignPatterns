package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<I, S> {
    S event(I evNumber);

    static <I, S> StateMachine<I, S> build(Consumer<StateBuilder<I, S>> consumer) {
        HashMap<I, S> map = new HashMap<>();
        consumer.accept((e, s) -> {
            map.put(e, s);
        });
        return evNumber -> map.get(evNumber);
    }

    static <I, S> ConsumerStateBuilder<I, S> init(I event, S state) {
        return stateBuilder -> stateBuilder.register(event, state);
    }

    interface ConsumerStateBuilder<I, S> extends Consumer<StateBuilder<I, S>> {
        default ConsumerStateBuilder<I, S> transition(I event, S state) {
            return stateBuilder -> {
                this.accept((e, s) -> stateBuilder.register(e, s));
                stateBuilder.register(event, state);
            };
        }
    }
}
