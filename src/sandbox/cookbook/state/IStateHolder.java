package sandbox.cookbook.state;

public interface IStateHolder<S> {
    void setState(S state);

    S getState();
}
