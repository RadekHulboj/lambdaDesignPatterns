package dp.builder.nosetters;

public class MainCatBuilder {
    public static void main (String... args) {
        Cat cat = ICatBuilder.of(CatBuilder::new)
                .with(CatBuilder::setAge, 1)
                .with(CatBuilder::setName, "ra")
                .build();
    }
}
