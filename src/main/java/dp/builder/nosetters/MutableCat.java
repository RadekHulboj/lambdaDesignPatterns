package dp.builder.nosetters;

class MutableCat {
    private int age;
    private String name;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setName(String name1, String name2) {
        this.name = name1 + name2;
    }
}
