package sandbox.cookbook.builder.stackoverflow;

import eu.hulboj.model.Person;

public class MainGeneric {
    public static void main(String[] args) {
        Person p = GenericBuilder.of(Person::new)
                .with(Person::setName, "Otto")
                .with(Person::setAge, 5)
                .build();
        System.out.println(p);
    }
}
