package sandbox.cookbook.state;

import common.exception.StateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

@FunctionalInterface
public interface StateMachine<E, S> {

    S event(E evNumber);

    static <E, S> StateMachine<E, S> build(S initState, Consumer<StateBuilder<E, S>> consumer) {
        ReentrantLock reentrantLock = new ReentrantLock();
        final String stateValue = "theStateOfTheStateMachine";
        HashMap<String, S> stateHolder = new HashMap<>();
        HashMap<String, S> event2State = new HashMap<>();
        HashMap<E, Consumer<S>> event2Function = new HashMap<>();
        HashMap<S, List<E>> state2Events = new HashMap<>();
        stateHolder.put(stateValue, initState);
        consumer.accept((ss, e, ds, f) -> {
            event2State.put(buildUniqueKeyFromEventState(ss, e), ds);
            event2Function.put(e, f);
            if (!state2Events.containsKey(ss)) {
                state2Events.put(ss, new ArrayList<>());
            }
            state2Events.get(ss).add(e);
        });
        return evNumber -> {
            reentrantLock.lock();
            S currentState = stateHolder.get(stateValue);
            if (state2Events.get(currentState).contains(evNumber)) {
                S newState = event2State.get(buildUniqueKeyFromEventState(currentState, evNumber));
                stateHolder.put(stateValue, newState);
                event2Function.get(evNumber).accept(newState);
                reentrantLock.unlock();
                return newState;
            } else {
                reentrantLock.unlock();
                throw new StateException("State machine fails");
            }

        };
    }

    static <E, S> String buildUniqueKeyFromEventState(S ss, E e) {
        return e.toString() + ss.toString();
    }

    static <E, S> ConsumerStateBuilder<E, S> init(S srcState, E event, S dstState, Consumer<S> function) {
        return stateBuilder -> stateBuilder.register(srcState, event, dstState, function);
    }

    interface ConsumerStateBuilder<E, S> extends Consumer<StateBuilder<E, S>> {
        default ConsumerStateBuilder<E, S> transition(S srcState, E event, S dstState, Consumer<S> function) {
            return stateBuilder -> {
                accept(stateBuilder);
                stateBuilder.register(srcState, event, dstState, function);
            };
        }
    }
}

