package sandbox.cookbook.builder.solution;

import eu.hulboj.model.Person;

public class MainIBuilder {
    public static void main(String[] args) {
        IBuilder<Object> of = IBuilder.of(Person::new);
    }
}
