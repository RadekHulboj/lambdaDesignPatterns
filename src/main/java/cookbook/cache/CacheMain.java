package cookbook.cache;

public class CacheMain {

    private static Cache<Integer> cache = Cache.of(CacheMain::supplier);

    static Integer supplier() {
        System.out.println("Execute only ones");
        return 2 * 2 * 2;
    }

    public static void main(String[] args) {
        cache.getValue();
        cache.getValue();
    }


}
