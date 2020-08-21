package sandbox.cookbook.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

class StateMachineTest {
    @DisplayName("Check success flow for StateMachine")
    @Test
    public void successFlow() {
        // given
        StateMachine.ConsumerStateBuilder<Integer, String> transitions = StateMachine
                .init("Init", 1, "Run", this::evFunction)
                .transition("Run", 2, "Stop", this::evFunction)
                .transition("Stop", 3, "Init", this::evFunction);

        StateMachine<Integer, String> sm = StateMachine.build("Init", transitions);
        // when
        sm.event(1);
        sm.event(2);
        sm.event(3);
    }
    // then
    void evFunction(String s) {
        List<String> collect = Stream.of(new String[]{"Init", "Run", "Stop"}).collect(Collectors.toList());
        assertThat(collect, hasItem(s));
        System.out.println(s);
    }
}