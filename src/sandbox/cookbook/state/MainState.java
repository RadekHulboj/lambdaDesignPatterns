package sandbox.cookbook.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MainState {

    public static void main(String[] args) {
        mainTry();

        // only for analyze
        firstTry();
        analyzeTry();
    }

    private static void mainTry() {
        Consumer<StateBuilder> transition = StateMachine
                .init(1, "init")
                .transition(2, "Run")
                .transition(3, "Stop");

        StateMachine sm = StateMachine.build(transition);

        Arrays.asList(1,2,3).forEach(i -> System.out.println(sm.event(i)));
    }

    private static void analyzeTry() {
        Consumer<StateBuilder> consumer = stateBuilder -> stateBuilder.register(1, "one");
        HashMap<Integer, String> map = new HashMap<>();
        StateBuilder stateBuilder = (event, state) -> map.put(event, state);
        consumer.accept(stateBuilder);
    }

    private static void firstTry() {
        // state interface, maybe will be used later
        State<String> closures = State.init("Ala");
        closures.setState("state1")
                .setState("state2");
    }
}
