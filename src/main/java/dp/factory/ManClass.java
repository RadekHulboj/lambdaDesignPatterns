package dp.factory;

public class ManClass {
    static class MyClass {

    }
    public static void main(String... args) {
        MyClass instance = FactorySupplier.create(MyClass::new).instance();
    }
}
