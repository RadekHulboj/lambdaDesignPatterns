package dp.chainofresponsibilities.oop;

import java.util.List;
import java.util.stream.Collectors;

public interface AbstractRule<V> {
    Boolean isValid(V value);

    static <V> List<AbstractRule> check(List<AbstractRule> checkListRules, V value) {
        return checkListRules.stream()
                .filter(rule -> rule.isValid(value))
                .collect(Collectors.toList());
    }
}
