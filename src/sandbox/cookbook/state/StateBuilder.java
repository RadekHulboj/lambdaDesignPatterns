package sandbox.cookbook.state;

import java.util.function.Consumer;

public interface StateBuilder<I, S> {
    void register(I event, S state, Consumer<S> function);
}
