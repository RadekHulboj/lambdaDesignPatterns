package dp.builder.nosetters;

import dp.builder.IBuilder;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@FunctionalInterface
interface ICatBuilder<I extends ICatBuilder.CatBuilder, C extends Cat> {
    class CatBuilder {
        private int age;
        private String name;
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setName(String name1, String name2) {
            this.name = name1 + name2;
        }
    }
    Supplier<I> supplier();
    @FunctionalInterface
    interface TriConsumer<I extends ICatBuilder.CatBuilder, P1, P2> {
        void accept (I inst, P1 param1, P2 param2);
    }
    static <S extends CatBuilder, K extends Cat> ICatBuilder<S, K> of() {
        return () -> (Supplier<S>) ICatBuilder.of(CatBuilder::new).supplier();
    }
    static <S extends CatBuilder, C extends Cat> ICatBuilder<S, C> of(Supplier<S> instance) {
        return () -> instance;
    }
    default <V> ICatBuilder<I, C> with(BiConsumer<I, V> biConsumer, V v) {
        return () -> () -> {
            I inst = supplier().get();
            biConsumer.accept(inst, v);
            return inst;
        };
    }
    default <V1, V2> ICatBuilder<I, C> with(IBuilder.TriConsumer<I, V1, V2> triConsumer, V1 v1, V2 v2) {
        return () -> () -> {
            I inst = supplier().get();
            triConsumer.accept(inst, v1, v2);
            return inst;
        };
    }
    default C build() {
        return (C) Cat.build(supplier().get());
    }
}
