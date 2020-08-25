package dp.builder;

import eu.hulboj.model.Heart;
import eu.hulboj.model.Person;

public class MainBuilder {
    public static void main(String[] args) {
        buildBob();
        buildAnna();
    }

    public static void buildAnna() {
        Person anna = IBuilder.of(Person::new)
                .with(Person::setAll, "Anna", 24)
                .with(Person::setHeart,
                        IBuilder.of(Heart::new)
                                .with(Heart::setHealth, "super good")
                                .build())
                .build();
        System.out.println(anna);
    }

    public static void buildBob() {
        Person rob = IBuilder.of(Person::new)
                .with(Person::setAge, 23)
                .with(Person::setName, "Rob")
                .with(Person::setHeart,
                        IBuilder.of(Heart::new)
                                .with(Heart::setHealth, "good")
                                .build())
                .build();
        System.out.println(rob);
    }
}
