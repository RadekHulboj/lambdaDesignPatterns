package sandbox.cookbook.state;

public interface StateBuilder<I, S> {
    void register(I event, S state);
}
