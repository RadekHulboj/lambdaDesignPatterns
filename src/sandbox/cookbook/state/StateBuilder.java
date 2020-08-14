package sandbox.cookbook.state;

import java.util.function.Consumer;

public interface StateBuilder<E, S> {
    void register(S sourceState, E event, S destinationState, Consumer<S> evFunction);
}
