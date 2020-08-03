package sandbox.cookbook.visit;

import java.util.function.Function;

public interface SbVisitatorBuilder {
    void register(Class<?> type, Function<Object, String> function);
}
