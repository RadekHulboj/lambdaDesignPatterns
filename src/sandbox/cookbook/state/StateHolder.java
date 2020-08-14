package sandbox.cookbook.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class StateHolder<S> implements IStateHolder<S> {
    private static Map<String, Object> map = new ConcurrentHashMap<>();
    private static String state = "state";

    @Override
    public void setState(S state) {
        map.put(StateHolder.state, state);
    }

    @Override
    public S getState() {
        return (S) map.get(state);
    }
}
