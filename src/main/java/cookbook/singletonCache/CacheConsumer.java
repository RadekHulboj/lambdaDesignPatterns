package cookbook.singletonCache;

import java.util.function.Function;

@FunctionalInterface
interface CacheConsumer {
    <T> void takeParams(Class<T> clazz, Function<T, T> function);
}
