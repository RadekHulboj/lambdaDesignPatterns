package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine {
    String event(Integer evNumber);

    static StateMachine build(Consumer<StateBuilder> consumer) {
        HashMap<Integer, String> map = new HashMap<>();
        consumer.accept((key, value) -> {
            map.put(key, value);
        });
        return evNumber -> map.get(evNumber);
    }

    static ConsumerStateBuilder init(Integer event, String state) {
        return stateBuilder -> stateBuilder.register(event, state);
    }

    interface ConsumerStateBuilder extends Consumer<StateBuilder> {
        default ConsumerStateBuilder transition(Integer event, String state) {
            return stateBuilder -> {
                this.accept((e, s) -> stateBuilder.register(e, s));
                stateBuilder.register(event, state);
            };
        }
    }
}
