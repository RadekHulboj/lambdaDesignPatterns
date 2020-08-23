package sandbox.cookbook.builder.solution;

import eu.hulboj.model.Person;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@FunctionalInterface
public interface IBuilder<U> {

    IBuilder<U> with(BiConsumer<Person, U> setter, U value);

    static <U> IBuilder<U> of(Supplier<Person> instance) {
        return (setter, value) -> null;
    }


}
