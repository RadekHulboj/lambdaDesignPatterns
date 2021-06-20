package sandbox.cookbook.switcher;

import java.util.function.Supplier;

public class MainSwitcher {
    interface Per {
        Integer getAge();
    }

    public static void main(String... args) {

        SwitcherCase<Per, Integer> of = SwitcherCase.of(
                SwitcherCase.ConsumerConditioner.<Per, Integer>build()
                        .$case((t1, t2) -> t1.getAge() > t2.getAge(), () -> 1)
                        .$case((t1, t2) -> t1.getAge() < t2.getAge(), () -> 2)
        );

        Supplier<Integer> switcher = of.switcher(() -> 3, () -> 2);
        System.out.println(switcher.get());

    }
}


