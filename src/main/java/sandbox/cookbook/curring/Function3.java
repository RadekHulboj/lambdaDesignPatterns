package sandbox.cookbook.curring;

import java.util.function.Supplier;

@FunctionalInterface
interface Function3<X,Y, Z, O> {

    O apply(X x, Y y, Z z);

    static <X,Y,Z, O> Function3<X,Y, Z, O> of(Supplier<O> supplier) {
        return  (x, y, z) -> supplier.get();
    }

    static void main(String[] args) {
        Function3<Integer, Integer, Integer, String> fun3 =  Function3.of(() -> "Polska");
    }
}
