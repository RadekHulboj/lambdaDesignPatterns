package dp.builder.nosetters;

public class MainBuilder {
    public static void main(String... args) {
        Cat cat1 = IBuilder.<CatBuilder, Cat>of(CatBuilder::new)
                .with(CatBuilder::setAge, 1)
                .with(CatBuilder::setName, "ra")
                .with(CatBuilder::setName, "ra", "dek")
                .buildWithReflection(Cat.class, CatBuilder.class);
        Cat cat2 = IBuilder.<CatBuilder, Cat>of(CatBuilder::new)
                .with(CatBuilder::setAge, 1)
                .with(CatBuilder::setName, "ra")
                .with(CatBuilder::setName, "ra", "dek")
                .build(Cat::build);
    }
}
