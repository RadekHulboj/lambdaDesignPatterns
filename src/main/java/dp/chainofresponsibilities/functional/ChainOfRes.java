package dp.chainofresponsibilities.functional;

import dp.chainofresponsibilities.oop.AbstractRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface ChainOfRes<R extends AbstractRule<V>, V> {
    List<R> checkChain(V value);

    static <R extends AbstractRule<V>, V> ChainOfRes<R, V> init() {
        return value -> new ArrayList<R>();
    }

    default ChainOfRes<R, V> addRule(Supplier<R> supplier) {
        return value -> {
            R rule = supplier.get();
            List<R> list = checkChain(value);
            Optional.of(rule.isValid(value))
                    .filter(isMatchedRule())
                    .ifPresent(ignore -> list.add(rule));
            return list;
        };
    }

    static Predicate<Boolean> isMatchedRule() {
        return b -> b;
    }
}
