package dp.chainofresponsibilities.functional.paper;


import dp.chainofresponsibilities.oop.IRule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@FunctionalInterface
public interface ChainOfRes<R extends IRule<V>, V> {
    Supplier<List<R>> supplierList();

    static <R extends IRule<V>, V> ChainOfRes<R, V> init() {
        return () -> ArrayList::new;
    }

    default ChainOfRes<R, V> addRule(Supplier<R> ruleSupplier) {
        return () -> () -> {
            List<R> list = supplierList().get();
            list.add(ruleSupplier.get());
            return list;
        };
    }

    default List<R> checkWithValue(V value) {
        return supplierList().get()
                .stream()
                .filter(r -> r.isValid(value))
                .collect(Collectors.toList());
    }
}
