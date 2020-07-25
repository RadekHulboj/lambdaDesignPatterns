package cookbook;

public interface ISneakyThrower {
    static <T extends Throwable> void sneakyThrow(Throwable e) throws T {
        throw (T) e;
    }
}
