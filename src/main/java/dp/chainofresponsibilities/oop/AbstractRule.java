package dp.chainofresponsibilities.oop;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRule<V> {
    abstract public Boolean isValid(V value);

    public static <V> List<AbstractRule> check(List<AbstractRule> checkListRules, V value) {
        return checkListRules.stream()
                .filter(rule -> rule.isValid(value))
                .collect(Collectors.toList());
    }
}
