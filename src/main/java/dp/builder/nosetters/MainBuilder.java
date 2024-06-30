package dp.builder.nosetters;

public class MainBuilder {
    public static void main(String... args) {
        Cat cat1 = IBuilder.<MutableCat, Cat>of(MutableCat::new)
                .with(MutableCat::setAge, 1)
                .with(MutableCat::setName, "ra")
                .with(MutableCat::setName, "ra", "dek")
                .buildWithReflection(Cat.class, MutableCat.class);
        Cat cat2 = IBuilder.<MutableCat, Cat>of(MutableCat::new)
                .with(MutableCat::setAge, 1)
                .with(MutableCat::setName, "ra")
                .with(MutableCat::setName, "ra", "dek")
                .build(Cat::build);
    }
}
