package dp.singleton;

import common.ISneakyThrower;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface Singleton<T> {

    T instance();

    final class MapSingleton {
        private MapSingleton() {
        }

        private static Map<Class<?>, Object> map = new HashMap<>();
    }

    static <T> Singleton<T> of(Supplier<T> supplier) {
        Map<Class<?>, Object> map = MapSingleton.map;
        if (Objects.isNull(map.get(supplier.get().getClass()))) {
            Object value ;
                value = supplier.get();
                map.put(supplier.get().getClass(), value);

        }

        return () -> (T) map.get(supplier.get().getClass());
    }
}
