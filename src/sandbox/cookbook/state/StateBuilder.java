package sandbox.cookbook.state;

import java.util.function.Consumer;

public interface StateBuilder<I, S> {
    void register(S srcState, I event, S dstState, Consumer<S> function);
}
