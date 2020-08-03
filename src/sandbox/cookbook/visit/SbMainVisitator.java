package sandbox.cookbook.visit;

import java.util.function.Consumer;

public class ManSbVisitator {
    public static void main(String[] args) {

        Consumer<SbVisitatorBuilder> consumer = SbVisitator.forType(Car.class).execution(o-> "Car=" + o);
        SbVisitator v = SbVistator.of(consumer);
        String visitation = v.visit();
    }
}
