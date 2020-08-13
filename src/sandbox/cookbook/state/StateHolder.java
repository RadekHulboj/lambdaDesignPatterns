package sandbox.cookbook.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StateHolder<S> {
    private static Map<String, Object > map =  new ConcurrentHashMap<>();
    private static String STATE = "state";
    public void setState(S state) {
        map.put(STATE, state);
    }
    public S getState() {
        return (S)map.get(STATE);
    }
 }
