package sandbox.cookbook.sand;

public class Cast {
    static class A {
    }

    static class B extends A {
    }

    public static void main(String[] args) {
        A b = new A();
        Class<? extends A> aClass = b.getClass();
        b = null;
        if (b instanceof B) {
            System.out.println("A");
        }
    }
}
