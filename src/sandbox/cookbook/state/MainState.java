package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

public class MainState {

    public static void main(String[] args) {
        firstTry();
        mainTry();
        analyzeTry();

    }

    private static void analyzeTry() {
        Consumer<StateBuilder> consumer = stateBuilder -> stateBuilder.register(1, "one");
        HashMap<Integer, String> map = new HashMap<>();
        StateBuilder stateBuilder = (event, state) -> map.put(event, state);
        consumer.accept(stateBuilder);
    }

    private static void mainTry() {
        Consumer<StateBuilder> transition = StateMachine
                .init(1, "init")
                .transition(2, "Run")
                .transition(3, "Stop");

        StateMachine sm = StateMachine.build(transition);

        String event = sm.event(3);
        System.out.println(event);
    }

    private static void firstTry() {
        // state interface, maybe will be used later
        State<String> closures = State.init("Ala");
        closures.setState("state1")
                .setState("state2");
    }
}
