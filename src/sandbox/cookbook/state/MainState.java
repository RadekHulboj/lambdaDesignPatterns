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
                .init(1L, "init")
                .transition(2L, "Run")
                .transition(3L, "Stop");

        StateMachine<Long, String> sm = StateMachine.build(transition);

        Arrays.asList(1L, 2L, 3L).forEach(i -> System.out.println(sm.event(i)));
    }

    private static void analyzeTry() {
        Consumer<StateBuilder<Integer, String>> consumer = stateBuilder -> stateBuilder.register(1, "one");
        HashMap<Integer, String> map = new HashMap<>();
        StateBuilder<Integer, String> stateBuilder = (event, state) -> map.put(event, state);
        consumer.accept(stateBuilder);
    }

    private static void firstTry() {
        // state interface, maybe will be used later
        State<String> closures = State.init("Ala");
        closures.setState("state1")
                .setState("state2");
    }
}
