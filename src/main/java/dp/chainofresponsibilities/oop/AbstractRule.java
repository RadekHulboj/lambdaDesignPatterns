package dp.chainofresponsibilities.oop;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRule {
    abstract protected Boolean isValid(Long value);
    public static List<AbstractRule> check(List<AbstractRule> checkListRules, Long value) {
        return checkListRules.stream()
                .filter(rule -> rule.isValid(value))
                .collect(Collectors.toList());
    }
}
