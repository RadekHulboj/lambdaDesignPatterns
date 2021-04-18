package dp.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@FunctionalInterface
public interface Singleton<T> {

    T instance();

    final class MapSingleton<T> {
        private MapSingleton() {
        }
        private static Map<Class<?>, Object> map = new HashMap<>();
    }

    static <T> Singleton<T> of(Supplier<T> supplier) {
        Map<Class<?>, Object> map = MapSingleton.map;
        T val = supplier.get();
        if (Objects.isNull(map.get(val.getClass()))) {
            map.put(val.getClass(), val);
        }
        return () -> (T) map.get(supplier.get().getClass());
    }
}
