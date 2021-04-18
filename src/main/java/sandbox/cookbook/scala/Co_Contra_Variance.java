package sandbox.cookbook.scala;

import java.util.function.Function;

public class Co_Contra_Variance {

    public static class A{}
    public static class B extends A{}
    public static class C extends B{}

    public static void main (String[] args) {
        Function<A, B> gen = integer -> null;
        Function<B, C> spe = number -> null;
        Function<C, B> spe1 = number -> null;
        fun(gen);
        fun(spe);
        fun(spe1);
    }

    public static void fun(Function<? super C, ? extends A> gen) {

    }
}
