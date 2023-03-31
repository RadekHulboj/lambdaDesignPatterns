package cookbook.singletonCache;

import java.util.function.Function;

public class ExampleValidator<T> implements Function<T, T> {
    @Override
    public T apply(T t) {
        return t;
    }
}
