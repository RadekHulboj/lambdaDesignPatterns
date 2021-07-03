package sandbox.cookbook.presentation;

import java.util.function.Consumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface Sam<T> {
    Supplier<T> functionName(Consumer<T> consumer);
}
