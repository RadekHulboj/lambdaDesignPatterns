package sandbox.cookbook.visit;

import eu.hulboj.model.Bicycle;

import java.util.function.Consumer;

public class SbMainVisitator {
    public static void main(String[] args) {

        Bicycle bicycle = new Bicycle();

        Consumer<SbVisitorBuilder<String>> builderConsumer = SbVisitor.<String>build()
                .forType(Bicycle.class).execution(o-> "Bicycle=" + o);

        SbVisitor<String> v = SbVisitor.of(builderConsumer);

        String visit = v.visit(bicycle);




    }
}
