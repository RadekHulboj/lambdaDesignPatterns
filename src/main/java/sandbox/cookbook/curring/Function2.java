package sandbox.cookbook.curring;

import java.util.function.Supplier;

@FunctionalInterface
interface Function2<X, Y, O> {

    O apply(X x, Y y);

    static <X, Y, O> Function2<X, Y, O> of(Supplier<O> supplier) {
        return (x, y) -> supplier.get();
    }

    static <T1, T2, R> Function2<T1, T2, R> of(Function2<T1, T2, R> methodReference) {
        return methodReference;
    }

    default Function1<X, Function1<Y, O>> curried() {
        return x -> y -> apply(x, y);
    }

    static void main(String[] args) {

        Function2<Integer, Integer, String> of = Function2.of((o, o2) -> "dd");
        String apply = of.curried().apply(1).apply(3);

    }
}
