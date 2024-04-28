package dp.builder.nosetters;

/*the case when we want have to have final variables*/
public final class Cat {
    private final int age;
    private final String name;
    private Cat(CatBuilder catBuilder) {
        this.age = catBuilder.getAge();
        this.name = catBuilder.getName();
    }
    public static Cat build(CatBuilder catBuilder ) {
        return new Cat(catBuilder);
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}
