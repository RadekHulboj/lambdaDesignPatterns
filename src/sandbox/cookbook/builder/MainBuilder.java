package sandbox.cookbook.builder;

import eu.hulboj.model.Person;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainBuilder {
    public static void main(String[] args) {
        Person jola = Builder.build(Person::new)
                .setName(() -> "Jola")
                .setAge(() -> 18)
                .build();

        System.out.println(jola);

        Consumer<String> stringSupplier = MainBuilder::bbb;

    }

    public static String aaa () {
        return "";
    }


    public static void bbb (String d) {

    }
}
