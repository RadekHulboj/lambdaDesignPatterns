package sandbox.cookbook.curring;

import java.util.function.Supplier;

@FunctionalInterface
interface Function1<X, O> {

    O apply(X x);

    static <X, O> Function1<X,O> of(Supplier<O> supplier) {
        return  (x) -> supplier.get();
    }

    static void main(String[] args) {
        Function1<Long, String> fun1 =  Function1.of(() -> "Polska");

    }
}
