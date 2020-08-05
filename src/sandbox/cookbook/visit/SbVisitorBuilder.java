package sandbox.cookbook.visit;

import java.util.function.Function;

public interface SbVisitorBuilder<R> {
    void register(Class<?> type, Function<Object, R> function);
}
