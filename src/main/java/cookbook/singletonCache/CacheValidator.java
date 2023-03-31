package cookbook.singletonCache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@FunctionalInterface
public interface CacheValidator {
    Function<Object, Object> objectsInstance(Class<?> clazz);

    default <T> Function<T, T> instance(Class<T> clazz) {
        return functionCastToGenerics(clazz, objectsInstance(clazz));
    }

    static CacheValidator of(ConsumerCacheConsumer consumerCacheConsumer) {
        Map<Class<?>, Function<Object, Object>> map = new HashMap();
        CacheConsumer cacheConsumer = new CacheConsumer() {
            @Override
            public <T> void takeParams(Class<T> clazz, Function<T, T> function) {
                map.putIfAbsent(clazz, functionCastToObjects(clazz, function));
            }
        };
        consumerCacheConsumer.accept(cacheConsumer);
        return map::get;
    }

    static ConsumerCacheConsumer initCOnsumer() {
        return cacheConsumer -> {
        };
    }

    static <T> Function<Object, Object> functionCastToObjects(Class<T> clazz, Function<T, T> genericFunction) {
        return genericFunction.compose(clazz::cast)::apply;
    }

    static <T> Function<T, T> functionCastToGenerics(Class<T> clazz, Function<Object, Object> function) {
        return obj -> clazz.cast(function.apply(obj));
    }

}
