package sandbox.cookbook.casing;

import static io.vavr.API.*;

public class Switching {
    public static void main(String[] args) {
        Object fff = Match(1).of(
                Case($(1), "fff"),
                Case($(), 2)
        );
        System.out.println(fff.toString());
    }
}
