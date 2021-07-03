package sandbox.cookbook.presentation;

import eu.hulboj.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {
    Integer objectFieldInteger;
    public static void main(String... args) {
        //imperative style
        Map<Person, String> peoples = new HashMap<>();
        Map<Person, String> youngs1 = new HashMap<>();
        for (Person p : peoples.keySet()) {
            if (p.getAge() > 20) {
                youngs1.put(p, "young");
            }
        }
        // declarative style
        Map<Person, String> youngs2 = peoples.entrySet()
                .stream()
                .filter(p -> p.getKey().getAge() > 20)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue)
                );




    }

    public void nonStaticMethod() {
        final List<String> strings = new ArrayList<>();
        Sam<Boolean> sam = consumer -> {
            this.objectFieldInteger = 6;
            return () -> strings.add("this points to host, the context were lambda is created");
        };
    }
}
