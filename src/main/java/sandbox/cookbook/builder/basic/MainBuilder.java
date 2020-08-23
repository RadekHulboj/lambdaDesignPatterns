package sandbox.cookbook.builder.basic;

import eu.hulboj.model.IPerson;
import eu.hulboj.model.Person;

public class MainBuilder {
    public static void main(String[] args) {
        IPerson jola = Builder.build(Person::new)
                .setName(() -> "Jola")
                .setAge(() -> 18)
                .build();

        System.out.println(jola);
    }
}
