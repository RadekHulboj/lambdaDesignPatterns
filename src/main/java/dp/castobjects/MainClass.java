package dp.castobjects;

public class MainClass {
    static class ParenClass {

    }
    static class SubClass extends ParenClass{

    }
    public static void main (String... args) {
        ParenClass cast = SafeCast.castTo(ParenClass.class).cast(new SubClass());

    }
}
