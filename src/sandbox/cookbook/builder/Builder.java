package sandbox.cookbook.builder;

import eu.hulboj.model.Person;

import java.util.function.Supplier;

@FunctionalInterface
public interface Builder {

    BuilderSupplier chain();

    static Builder build(Supplier<Person> personSupplier) {
        return () -> personSupplier::get;
    }

    default Builder setName(Supplier<String> stringSupplier) {
        return () -> () -> {
            Person receive = chain().receive();
            receive.setName(stringSupplier.get());
            return receive;
        };
    }

    default Builder setAge(Supplier<Integer> integerSupplier) {
        return () -> () -> {
            Person receive = chain().receive();
            receive.setAge(integerSupplier.get());
            return receive;
        };
    }

    default Person build () {
        return chain().receive();
    }

}
