package sandbox.cookbook.state;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

@Execution(ExecutionMode.CONCURRENT)
class StateMachineMultiThreadTest {
    List<String> result = new LinkedList<>();
    StateMachine.ConsumerStateBuilder<Integer, String> transitions = StateMachine
            .init("A", 1, "B", this::evFunctionMultiThreading)
            .transition("B", 1, "C", this::evFunctionMultiThreading)
            .transition("C", 1, "A", this::evFunctionMultiThreading);

    StateMachine<Integer, String> sm = StateMachine.build("C", transitions);

    @DisplayName("Check success flow for multi-threading StateMachine")
    @Test
    public void checkFlowForMultiTreading() throws InterruptedException {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<String>> callableTasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            callableTasks.add(() -> {
                Stream.iterate(0, n -> n + 1)
                        .limit(10)
                        .forEach(x -> sm.event(1));
                return "success";
            });
        }
        //when
        executorService.invokeAll(callableTasks);
        //then
        String[] strings = result.toArray(new String[]{});
        int sumOfTheRepeats = IntStream.range(0, strings.length)
                .map(idx -> {
                    if (idx < strings.length - 1) {
                        return strings[idx].contains(strings[idx + 1]) ? 1 : 0;
                    }
                    return 0;
                })
                .sum();
        int noConcurrentRepeats = 0;
        Assertions.assertEquals(noConcurrentRepeats, sumOfTheRepeats);
    }

    // then
    void evFunctionMultiThreading(String s) {
        List<String> collect = Stream.of(new String[]{"A", "B", "C"}).collect(Collectors.toList());
        assertThat(collect, hasItem(s));
        System.out.println(s);
        result.add(s);
    }
}