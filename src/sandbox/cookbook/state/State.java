package sandbox.cookbook.state;

import java.util.function.Supplier;

public interface State<T> {
    Supplier<T> getState();

    static <T >State<T> init(T s) {
        return () -> () -> s;
    }

    default State<T> setState(T s) {
        return () -> () -> s;
    }
}
