package sandbox.cookbook.builder;

import eu.hulboj.model.Person;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

@FunctionalInterface
public interface Builder {

    BuilderSupplier supplier();

    static Builder build(Supplier<Person> personSupplier) {
        return () -> personSupplier::get;
    }

    default Builder setName(Supplier<String> stringSupplier) {
        return () -> () -> {
            Person p = supplier().receive();
            p.setName(stringSupplier.get());
            return p;
        };
    }

    default Builder setAge(IntSupplier integerSupplier) {
        return () -> () -> {
            Person p = supplier().receive();
            p.setAge(integerSupplier.getAsInt());
            return p;
        };
    }

    default Person build () {
        return supplier().receive();
    }

}
