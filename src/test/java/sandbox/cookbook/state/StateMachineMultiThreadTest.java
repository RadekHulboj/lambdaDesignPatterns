package sandbox.cookbook.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@Execution(ExecutionMode.CONCURRENT)
class StateMachineMultiThreadTest {

    StateMachine.ConsumerStateBuilder<Integer, String> transitions = StateMachine
            .init("A", 1, "B", this::evFunctionMultiThreading)
            .transition("B", 1, "C", this::evFunctionMultiThreading)
            .transition("C", 1, "A", this::evFunctionMultiThreading);

    StateMachine<Integer, String> sm = StateMachine.build("C", transitions);
    @DisplayName("Check success flow for multi-threading StateMachine")
    @Test
    public void checkFlowForMultiTreading() {

        // when TODO: RaHu something went wrong all the time state is A
        sm.event(1);
        sm.event(1);
        sm.event(1);
    }
    // then
    void evFunctionMultiThreading(String s) {
        List<String> collect = Stream.of(new String[]{"A", "B", "C"}).collect(Collectors.toList());
        assertThat(collect, hasItem(s));
        System.out.println(s);
    }
}