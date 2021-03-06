package dp.state;

import java.util.function.Consumer;

@FunctionalInterface
public interface StateBuilder<E, S> {
    void register(S sourceState, E event, S destinationState, Consumer<S> evFunction);
}
