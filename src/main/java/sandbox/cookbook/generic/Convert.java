package sandbox.cookbook.generic;

import java.util.function.Function;

public class Convert<T> {
    T value;

    private Convert(T value) {
        this.value = value;
    }

    public <U> Convert<U> convert(Function<T, U> function) {
        return new Convert<>(function.apply(value));
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) {
        Function<Integer, String> i2s = integer -> "";
        Function<String, Long> s2l = s -> 1L;
        Convert<Integer> s = new Convert<>(1);
        Long value = s.convert(i2s).convert(s2l).getValue();
        System.out.println(value);
    }
}
