package dp.visitor;

import eu.hulboj.model.Saddle;
import eu.hulboj.model.Bicycle;
import eu.hulboj.model.Wheel;

import java.util.function.Consumer;

public class MainVisitor {

    public static void main(String[] args) {

        Bicycle bicycle = new Bicycle();
        Wheel wheel = new Wheel();
        Saddle saddle = new Saddle();

        Consumer<VisitorBuilder<String>> builderConsumer = Visitor.<String>build()
                .forType(Wheel.class).visitation(e -> "Visiting engine" + e)
                .forType(Saddle.class).visitation(b -> "Visiting body" + b)
                .forType(Bicycle.class).visitation(c -> "Visiting car" + c);

        Visitor<String> visitor = Visitor.of(builderConsumer);

        String strWheel = visitor.visit(wheel);
        System.out.println("Wheel: " + strWheel);

        String strBicycle = visitor.visit(bicycle);
        System.out.println("Bicycle: " + strBicycle);
        String strSaddle = visitor.visit(saddle);
        System.out.println("Saddle: " + strSaddle);

    }
}