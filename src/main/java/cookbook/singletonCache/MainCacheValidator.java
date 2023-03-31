package cookbook.singletonCache;

import java.util.function.Function;

public final class MainCacheValidator {
    private MainCacheValidator() {

    }

    public static void main(String[] args) {
        ConsumerCacheConsumer consumerCacheConsumer = CacheValidator.initCOnsumer()
                .forClass(Boolean.class).validator(new ExampleValidator<>())
                .forClass(String.class).validator(new ExampleValidator<>());

        CacheValidator cacheValidator = CacheValidator.of(consumerCacheConsumer);
        Function<Boolean, Boolean> instance1 = cacheValidator.instance(Boolean.class);
        Function<String, String> instance2 = cacheValidator.instance(String.class);
    }

}
