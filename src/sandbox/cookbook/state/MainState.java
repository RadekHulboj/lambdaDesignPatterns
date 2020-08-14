package sandbox.cookbook.state;

import java.util.HashMap;
import java.util.function.Consumer;

public class MainState {

    enum EnumState {INIT, READY, RUN}

    enum EnumEvent {EV_INIT, EV_RUN, EV_READY1, EV_READY2}

    public static void main(String[] args) {
        mainTry();
    }

    private static void mainTry() {

        Consumer<StateBuilder<EnumEvent, EnumState>> transition = StateMachine
                .init(EnumState.INIT, EnumEvent.EV_READY1, EnumState.READY, MainState::accept)
                .transition(EnumState.READY, EnumEvent.EV_RUN, EnumState.RUN, MainState::accept)
                .transition(EnumState.INIT, EnumEvent.EV_READY2, EnumState.READY, MainState::accept)
                .transition(EnumState.RUN, EnumEvent.EV_INIT, EnumState.INIT, MainState::accept);

        StateMachine<EnumEvent, EnumState> sm = StateMachine.build(EnumState.INIT, transition);

        sm.event(EnumEvent.EV_READY2);
        sm.event(EnumEvent.EV_RUN);
        sm.event(EnumEvent.EV_INIT);
    }

    private static void accept(EnumState s) {
        // Here we have current state -> s <-
        System.out.println("event function on state:" + s);
    }
}
