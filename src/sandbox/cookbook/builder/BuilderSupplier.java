package sandbox.cookbook.builder;

import eu.hulboj.model.Person;

import java.util.function.Supplier;

@FunctionalInterface
public interface BuilderSupplier extends Supplier<Person> {
    default Person receive() {
        return get();
    }
}
