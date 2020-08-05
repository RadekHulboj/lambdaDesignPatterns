package sandbox.cookbook.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<E, S> {

    // TODO: RaHu
    // 1 krok
    //  S event(S currentState, E evNumber, Consumer<S> function);
    // 2 krok trzymanie stanu u clienta callera, czyli trzeba bedzie podawac za kazdym razem currentState
    // 3 krok jakos to przerzucic do maszyny stanow, nie mam pojecia jak.

    S event(E evNumber);

    static <E, S> StateMachine<E, S> build(Consumer<StateBuilder<E, S>> consumer) {
        HashMap<E, S> event2State = new HashMap<>();
        HashMap<E, Consumer<S>> event2Function = new HashMap<>();
        HashMap<S, List<E>> state2Events = new HashMap<>();
        consumer.accept((ss, e, ds, f) -> {
            event2State.put(e, ds);
            event2Function.put(e, f);
            if(state2Events.containsKey(ss)) {
                state2Events.get(ss).add(e);
            } else {
                state2Events.put(ss, new ArrayList<>());
            }
        });
        return evNumber -> {
            S s = event2State.get(evNumber);
            event2Function.get(evNumber).accept(s);
            return s;
        };
    }

    static <E, S> ConsumerStateBuilder<E, S> init(S srcState, E event, S dstState, Consumer<S> function) { // TODO: RaHu this function remove will be in the SAM method
        return stateBuilder -> stateBuilder.register(srcState, event, dstState, function);
    }

    interface ConsumerStateBuilder<E, S> extends Consumer<StateBuilder<E, S>> {
        default ConsumerStateBuilder<E, S> transition(S srcState, E event, S dstState, Consumer<S> function) { // TODO: RaHu this function remove will be in the SAM method
            return stateBuilder -> {
                this.accept((ss, e, ds, f) -> stateBuilder.register(ss, e, ds, f));
                stateBuilder.register(srcState, event, dstState, function);
            };
        }
    }
}
