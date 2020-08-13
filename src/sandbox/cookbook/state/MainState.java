package sandbox.cookbook.state;

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

        sm.event(() -> () -> "ZERO", 3L);
        sm.event(() -> () -> "INIT", 2L);
        sm.event(() -> () -> "RUN", 4L);
    }

    private static void analyzeTry() {
        Consumer<StateBuilder<Integer, String>> consumer = stateBuilder -> stateBuilder.register("zero", 1, "one", s -> System.out.println("ss"));
        HashMap<Integer, String> map = new HashMap<>();
        StateBuilder<Integer, String> stateBuilder = (sstate, event, state, f) -> map.put(event, state);
        consumer.accept(stateBuilder);
    }

    private static void firstTry() {
        // state interface, maybe will be used later
        State<String> closures = State.init("INIT");
        String s = closures.setState("RUN")
                .setState("END").getState().get();
        System.out.println(s);
    }

    private static void accept(String s) {
        // Here we have current state -> s <-
        System.out.println("event function on state:" + s);
    }
}
