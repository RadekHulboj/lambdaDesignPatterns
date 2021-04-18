package sandbox.cookbook.casing;

public class SwitchMatch<T> {

    T value;

    private SwitchMatch(T value) {
        this.value = value;
    }

    public static <T> SwitchMatch<T> SwitchMatch(T value) {
        return new SwitchMatch(value);
    }



    public static void main (String[] args) {
        SwitchMatch(1);
    }
}
