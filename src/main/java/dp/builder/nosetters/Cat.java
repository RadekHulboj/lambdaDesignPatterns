package dp.builder.nosetters;
/*the case when we want have to have final variables*/
public class Cat {
    private final int age;
    private final String name;
    private Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public static Cat build(CatBuilder catBuilder ) {
        return new Cat(catBuilder.getAge(), catBuilder.getName());
    }
}
