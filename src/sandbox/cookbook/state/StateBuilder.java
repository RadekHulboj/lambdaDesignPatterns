package sandbox.cookbook.state;

public interface StateBuilder {
    void register(Integer event, String state);
}
