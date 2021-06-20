package sandbox.cookbook.casing.test;

import java.util.Optional;

import static sandbox.cookbook.casing.test.Case.$;
import static sandbox.cookbook.casing.test.Switcher.Match;

public class MainMatch {

    static class A {
    }

    static class B extends A {
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        A AA = new A();
        B BB = new B();
        A searchValue = BB;
        Optional<Object> of = Switcher.Match(searchValue).of(
                $(AA, MainMatch::b),
                $(BB, () -> "BB")
        );
        System.out.println(of.get());
    }

    private static void test1() {
        String DWA = "2";
        String searchValue = "1";
        Optional<Object> of = Match(searchValue).of(
                $(DWA, MainMatch::b),
                $("1", () -> 2)
        );
        System.out.println(of.get());
    }

    static String b() {
        return "-";
    }
}
