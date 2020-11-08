package dp.visitor;

import eu.hulboj.model.Saddle;
import eu.hulboj.model.Bicycle;
import eu.hulboj.model.Wheel;

import java.util.function.Consumer;

public class MainVisitor {

    public static void main(String[] args) {

        Consumer<VisitorLink<String>> visitorLinkConsumer = Visitor.<String>build()
                .forType(Wheel.class).visitation(e -> "Visiting wheel" + e)
                .forType(Saddle.class).visitation(b -> "Visiting saddle" + b)
                .forType(Bicycle.class).visitation(c -> "Visiting bicycle" + c);

        Visitor<String> visitor = Visitor.of(visitorLinkConsumer);

        Wheel wheel = new Wheel();
        Bicycle bicycle = new Bicycle();
        Saddle saddle = new Saddle();

        String strWheel = visitor.visit(wheel);
        System.out.println("Wheel: " + strWheel);

        String strBicycle = visitor.visit(bicycle);
        System.out.println("Bicycle: " + strBicycle);
        String strSaddle = visitor.visit(saddle);
        System.out.println("Saddle: " + strSaddle);

    }
}
