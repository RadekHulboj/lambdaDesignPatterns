package sandbox.cookbook.switcher;

import sandbox.cookbook.switcher.Switcher.ConsumerConditioner;

import java.util.function.Supplier;

public class MainSwitcher {
    interface Per {
        Integer getAge();
    }

    public static void main(String... args) {

        Switcher<Per, Integer> of = Switcher.of(
                ConsumerConditioner.<Per, Integer>build()
                        .$case((t1, t2) -> t1.getAge() > t2.getAge(), () -> 1)
                        .$case((t1, t2) -> t1.getAge() < t2.getAge(), () -> 2)
        );

        Supplier<Integer> $case = of.$switch(() -> 3, () -> 2);
        System.out.println($case.get());

    }
}


