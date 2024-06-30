package dp.chainofresponsibilities.oop;

import java.util.List;
import java.util.stream.Collectors;

public interface IRule<V> {
    Boolean isValid(V value);

    static <V> List<IRule> check(List<IRule> checkListRules, V value) {
        return checkListRules.stream()
                .filter(rule -> rule.isValid(value))
                .collect(Collectors.toList());
    }
}
