package cookbook.singletonCache;

import java.util.function.Function;

@FunctionalInterface
public interface NodeLinker<T> {
    Object getReference(Link link);

    enum Link {
        HOST,
        PARAM
    }

    default ConsumerCacheConsumer validator(Function<T, T> function) {
        return getPreviousConsumerHost().andThen(
                cacheConsumer -> cacheConsumer.takeParams(getParam(), function)
        );
    }

    default ConsumerCacheConsumer getPreviousConsumerHost() {
        return (ConsumerCacheConsumer) getReference(Link.HOST);
    }

    @SuppressWarnings("unchecked")
    default Class<T> getParam() {
        return (Class<T>) getReference(Link.PARAM);
    }
}
