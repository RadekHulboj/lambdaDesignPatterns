package dp.singleton;

import common.ISneakyThrower;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@FunctionalInterface
public interface Singleton<T> {

    T instance();

    final class MapSingleton {
        private MapSingleton() {
        }

        private static Map<Class<?>, Object> map = new HashMap<>();
    }

    static <T> Singleton<T> of(Class<?> objCls) {
        Map<Class<?>, Object> map = MapSingleton.map;
        if (Objects.isNull(map.get(objCls))) {
            Object value = null;
            try {
                value = objCls.getConstructor().newInstance();
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                ISneakyThrower.sneakyThrow(e);
            }
            map.put(objCls, value);
        }

        return () -> (T) map.get(objCls);
    }
}