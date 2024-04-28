package dp.builder.nosetters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@FunctionalInterface
interface IBuilder<I, C> {

    Supplier<I> supplier();

    interface IResult {
        static <I, B> I build(Class<I> clazzI, Class<B> clazzB, B builder ) {
            try {
                Constructor<I> declaredConstructor = clazzI.getDeclaredConstructor(clazzB);
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(builder);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
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

    default <V> IBuilder<I, C> with(BiConsumer<I, V> biConsumer, V v) {
        return () -> () -> {
            I inst = supplier().get();
            biConsumer.accept(inst, v);
            return inst;
        };
    }

    default <V1, V2> IBuilder<I, C> with(dp.builder.IBuilder.TriConsumer<I, V1, V2> triConsumer, V1 v1, V2 v2) {
        return () -> () -> {
            I inst = supplier().get();
            triConsumer.accept(inst, v1, v2);
            return inst;
        };
    }

    default C build(Class<C> targetCls, Class<I> builderCls) {
        return IResult.build(targetCls, builderCls, supplier().get());
    }
}
