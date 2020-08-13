package sandbox.cookbook.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<E, S> {

    StateHolder STATE_HOLDER =  new StateHolder();

    S event(E evNumber);

    static <E, S> StateMachine<E, S> build(S initState, Consumer<StateBuilder<E, S>> consumer) {
        HashMap<E, S> event2State = new HashMap<>();
        HashMap<E, Consumer<S>> event2Function = new HashMap<>();
        HashMap<S, List<E>> state2Events = new HashMap<>();

        STATE_HOLDER.setState(initState);

        consumer.accept((ss, e, ds, f) -> {
            event2State.put(e, ds);
            event2Function.put(e, f);
            if (!state2Events.containsKey(ss)) {
                state2Events.put(ss, new ArrayList<>());
            }
            state2Events.get(ss).add(e);
        });
        return (evNumber) -> {
            S currentState = (S)STATE_HOLDER.getState();
            if(state2Events.get(currentState).contains(evNumber)) {
                S newState = event2State.get(evNumber);
                STATE_HOLDER.setState(newState);
                event2Function.get(evNumber).accept(newState);
                return newState;
            } else {
                throw new RuntimeException("State machine fails");
            }
        };
    }

    static <E, S> ConsumerStateBuilder<E, S> init(S srcState, E event, S dstState, Consumer<S> function) {
        return stateBuilder -> stateBuilder.register(srcState, event, dstState, function);
    }

    interface ConsumerStateBuilder<E, S> extends Consumer<StateBuilder<E, S>> {
        default ConsumerStateBuilder<E, S> transition(S srcState, E event, S dstState, Consumer<S> function) {
            return stateBuilder -> {
                this.accept((ss, e, ds, f) -> stateBuilder.register(ss, e, ds, f));
                stateBuilder.register(srcState, event, dstState, function);
            };
        }
    }
}
