package sandbox.cookbook.builder;

import eu.hulboj.model.Person;

public class MainBuilder {
    public static void main(String[] args) {
        Person jola = Builder.build(Person::new)
                .setName(() -> "Jola")
                .setAge(() -> 18)
                .build();

        System.out.println(jola);
    }
}
