package sandbox.cookbook.casing.test;

import java.util.Optional;

import static sandbox.cookbook.casing.test.Case.$;
import static sandbox.cookbook.casing.test.Switcher.Match;

public class MainMatch {

    static class A { }
    static class B extends A{}
    static class C extends A{}


    public static void main(String[] args) {
        String DWA = "2";
        String searchValue = "2";
        Optional<Object> of = Match(searchValue).of(
                $(DWA, MainMatch::b),
                $("1", () -> "dwa")
                );
        System.out.println(of.get());

    }

    static String b () {
        return "";
    }
}
