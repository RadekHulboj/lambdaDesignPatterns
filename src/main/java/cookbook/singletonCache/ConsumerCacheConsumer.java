package cookbook.singletonCache;

import java.util.function.Consumer;

import static cookbook.singletonCache.NodeLinker.Link.HOST;

@FunctionalInterface
public interface ConsumerCacheConsumer extends Consumer<CacheConsumer> {
    default <T> NodeLinker<T> forClass(Class<T> clazz) {
        return link -> HOST == link ? this : clazz;
    }

    default ConsumerCacheConsumer andThen(ConsumerCacheConsumer after) {
        return cacheConsumer -> {
            this.accept(cacheConsumer);
            after.accept(cacheConsumer);
        };
    }
}
