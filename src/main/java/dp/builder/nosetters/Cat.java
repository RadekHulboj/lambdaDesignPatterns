package dp.builder.nosetters;

import dp.builder.nosetters.ICatBuilder.CatBuilder;

/*the case when we want have to have final variables*/
public final class Cat {
    private final int age;
    private final String name;
    private Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public static Cat build(CatBuilder catBuilder ) {
        return new Cat(catBuilder.getAge(), catBuilder.getName());
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
}
