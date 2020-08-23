package dp.state;

import java.util.function.Consumer;

public class MainState {

    enum EnumState {INIT, READY, RUN}

    enum EnumEvent {EV_INIT, EV_RUN, EV_READY1, EV_READY2}

    public static void main(String[] args) {
        Consumer<StateBuilder<EnumEvent, EnumState>> transitions = StateMachine
                .init(EnumState.INIT, EnumEvent.EV_READY1, EnumState.READY, MainState::onEventReady1)
                .transition(EnumState.READY, EnumEvent.EV_RUN, EnumState.RUN, MainState::onEventRun)
                .transition(EnumState.INIT, EnumEvent.EV_READY2, EnumState.READY, MainState::onEventReady2)
                .transition(EnumState.RUN, EnumEvent.EV_INIT, EnumState.INIT, MainState::onEventInit);

        StateMachine<EnumEvent, EnumState> sm = StateMachine.build(EnumState.INIT, transitions);

        sm.event(EnumEvent.EV_READY2);
        sm.event(EnumEvent.EV_RUN);
        sm.event(EnumEvent.EV_INIT);
    }

    static String text = "event function on state:";

    private static void onEventReady1(EnumState destinationState) {
        System.out.println(text + destinationState);
    }

    private static void onEventReady2(EnumState destinationState) {
        System.out.println(text + destinationState);
    }

    private static void onEventRun(EnumState destinationState) {
        System.out.println(text + destinationState);
    }

    private static void onEventInit(EnumState s) {
        System.out.println(text + s);
    }
}
