package cookbook.retry;

import common.ISneakyThrower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RetryConsumerUtil<T, G extends Throwable> {

    private RetryConsumer<T, G> retryConsumer;
    private int noOfRetry;
    private int delayInterval;
    private TimeUnit timeUnit;
    private List<Class<? extends Throwable>> exceptionList;

    public interface RetryConsumer<T, G extends Throwable> {
        void evaluate(T param) throws G;

        default Consumer<T> uncheckedEvaluate() {
            return t -> {
                try {
                    this.evaluate(t);
                } catch (Throwable g) {
                    ISneakyThrower.sneakyThrow(g);
                }
            };
        }
    }

    public static <T, G extends Throwable> RetryBuilder<T, G> newBuilder() {
        return new RetryBuilder<>();
    }

    public static class RetryBuilder<T, G extends Throwable> {
        private RetryConsumer<T, G> retryConsumer;
        private int noOfRetry;
        private int delayInterval;
        private TimeUnit timeUnit;
        private Class<? extends Throwable>[] exceptionClasses;

        private RetryBuilder() {
        }

        public RetryConsumerUtil.RetryBuilder<T, G> retryConsumer(final RetryConsumer<T, G> pRetryConsumer) {
            this.retryConsumer = pRetryConsumer;
            return this;
        }

        public RetryConsumerUtil.RetryBuilder<T, G> noOfRetry(final int pNoOfRetry) {
            this.noOfRetry = pNoOfRetry;
            return this;
        }

        public RetryConsumerUtil.RetryBuilder<T, G> delayInterval(final int pDelayInterval, final TimeUnit pTimeUnit) {
            this.delayInterval = pDelayInterval;
            this.timeUnit = pTimeUnit;
            return this;
        }

        @SafeVarargs
        public final RetryConsumerUtil.RetryBuilder<T, G> retryOn(final Class<? extends Throwable>... pExceptionClasses) {
            this.exceptionClasses = pExceptionClasses;
            return this;
        }

        public RetryConsumerUtil<T, G> build() {
            if (Objects.isNull(retryConsumer)) {
                throw new NullPointerException("RetryBuilder:retryConsumer not set");
            }
            List<Class<? extends Throwable>> exceptionList = new ArrayList<>();
            if (Objects.nonNull(exceptionClasses) && exceptionClasses.length > 0) {
                exceptionList = Arrays.asList(exceptionClasses);
            }
            timeUnit = Objects.isNull(timeUnit) ? TimeUnit.MILLISECONDS : timeUnit;
            return new RetryConsumerUtil<>(retryConsumer, noOfRetry, delayInterval, timeUnit, exceptionList);
        }
    }

    private RetryConsumerUtil(RetryConsumer<T, G> retryConsumer, int noOfRetry, int delayInterval, TimeUnit timeUnit,
                              List<Class<? extends Throwable>> exceptionList) {
        this.retryConsumer = retryConsumer;
        this.noOfRetry = noOfRetry;
        this.delayInterval = delayInterval;
        this.timeUnit = timeUnit;
        this.exceptionList = exceptionList;
    }

    public void retry(T param) throws G {
        int retries = 0;
        while (retries < noOfRetry) {
            try {
                retryConsumer.evaluate(param);
                break;
            } catch (Throwable e) {
                retries = handleException(retries, (G) e);
            }
        }
    }

    public void retryNoCatched(T param)  {
        int retries = 0;
        while (retries < noOfRetry) {
            try {
                retryConsumer.uncheckedEvaluate().accept(param);
                break;
            } catch (Throwable e) {
                retries = handleException(retries, (G) e);
            }
        }
    }

    private int handleException(int retries, G e)  {
        boolean anyMatch = exceptionList.stream().map(Class::getCanonicalName).anyMatch(s -> s.contains(e.getClass().getGenericSuperclass().getTypeName()));
        boolean isEmpty = exceptionList.isEmpty();
        int res = 0;
        if (anyMatch || isEmpty) {
            res = increaseRetryCountAndSleep(retries);
            if (res == noOfRetry) {
                ISneakyThrower.sneakyThrow(e);
            }
        } else {
            ISneakyThrower.sneakyThrow(e);
        }
        return res;
    }

    private int increaseRetryCountAndSleep(int retries) {
        int res  = retries + 1;
        if (res < noOfRetry && delayInterval > 0) {
            try {
                timeUnit.sleep(delayInterval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
        return res;
    }
}
