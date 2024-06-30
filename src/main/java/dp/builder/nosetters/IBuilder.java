package dp.builder.nosetters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
interface IBuilder<M, I> {

    Supplier<M> instance(); 

    interface IResult {
        static <I, B> I build(Class<I> clazzI, Class<B> clazzB, B builder) {
            try {
                Constructor<I> declaredConstructor = clazzI.getDeclaredConstructor(clazzB);
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(builder);
            } catch (InstantiationException
                     | IllegalAccessException
                     | InvocationTargetException
                     | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FunctionalInterface
    interface TriConsumer<I, P1, P2> {
        void accept(I inst, P1 param1, P2 param2);
    }

    static <S, C> IBuilder<S, C> of(Supplier<S> instance) {
        return () -> instance;
    }

    default <V> IBuilder<M, I> with(BiConsumer<M, V> biConsumer, V v) {
        return () -> () -> {
            M inst = instance().get();
            biConsumer.accept(inst, v);
            return inst;
        };
    }

    default <V1, V2> IBuilder<M, I> with(dp.builder.IBuilder.TriConsumer<M, V1, V2> triConsumer, V1 v1, V2 v2) {
        return () -> () -> {
            M inst = instance().get();
            triConsumer.accept(inst, v1, v2);
            return inst;
        };
    }

    default I buildWithReflection(Class<I> targetCls, Class<M> builderCls) {
        return IResult.build(targetCls, builderCls, instance().get());
    }

    default I build(Function<M, I> function) {
        return function.apply(instance().get());
    }
}
