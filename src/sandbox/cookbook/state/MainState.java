package sandbox.cookbook.state;

import java.util.function.Consumer;

public class MainState {

    public static void main(String[] args) {
        firstTry();

        Consumer<StateBuilder> transition = StateMachine
                .init(1, "init")
                .transition(2, "Stop")
                .transition(3, "Nadzieja");

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
