package sandbox.cookbook.singleton;

import common.ISneakyThrower;

import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
public interface Singleton<T> {

    T instance();

    final class Alone {
        static Map<Class<?>, Object> alone;

        private Alone() {
        }

        static Map<Class<?>, Object> getInstance() {
            if (alone == null) {
                alone = new HashMap<>();
            }
            return alone;
        }
    }

    static <T> Singleton<T> of(Class<?> objCls)  {


        Map<Class<?>, Object> map = Alone.getInstance();

        Object value = null;
        try {
            value = objCls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            ISneakyThrower.sneakyThrow(e);
        }
        map.put(objCls, value);

        return () -> (T)map.get(objCls);
    }
}
