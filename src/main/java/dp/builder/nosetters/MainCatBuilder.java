package dp.builder.nosetters;

import dp.builder.nosetters.ICatBuilder.CatBuilder;
public class MainCatBuilder {
    public static void main (String... args) {
        Cat cat = ICatBuilder.of()
                .with(CatBuilder::setAge, 1)
                .with(CatBuilder::setName, "ra")
                .with(CatBuilder::setName, "ra", "dek")
                .build();
    }
}
