package dp.builder.nosetters;

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
    }
    Supplier<I> supplier();
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
    default C build() {
        return (C) Cat.build(supplier().get());
    }
}
