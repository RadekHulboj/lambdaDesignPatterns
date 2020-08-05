package sandbox.cookbook.state;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class MainState {

    public static void main(String[] args) {
        mainTry();

        // only for analyze
        firstTry();
        analyzeTry();
    }

    private static void mainTry() {

        Consumer<StateBuilder<Long, String>> transition = StateMachine
                .init("ZERO",1L, "INIT", MainState::accept)
                .transition("INIT",2L, "RUN", MainState::accept)
                .transition("ZERO",3L, "INIT", MainState::accept)
                .transition("RUN",4L, "ZERO", MainState::accept);

        StateMachine<Long, String> sm = StateMachine.build(transition);

        // Object[] state = new Object[];
        sm.event(1L);  // TODO: RaHu wolanie bedzie wygladala w 2 kroku tak -> sm.event("ZERO",1L, newState-> { state[0] = newState }
        sm.event(2L);
        sm.event(1L);
    }

    private static void analyzeTry() {
        Consumer<StateBuilder<Integer, String>> consumer = stateBuilder -> stateBuilder.register("zero", 1, "one", s -> System.out.println("ss"));
        HashMap<Integer, String> map = new HashMap<>();
        StateBuilder<Integer, String> stateBuilder = (sstate, event, state, f) -> map.put(event, state);
        consumer.accept(stateBuilder);
    }

    private static void firstTry() {
        // state interface, maybe will be used later
        State<String> closures = State.init("Ala");
        closures.setState("state1")
                .setState("state2");
    }

    private static void accept(String s) {
        System.out.println("event function on state:" + s);
    }
}
