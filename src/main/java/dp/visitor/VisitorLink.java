package dp.visitor;

import java.util.function.Function;

@FunctionalInterface
public interface VisitorLink<R> {
	<T> void register(Class<T> type, Function<T, R> function);
}