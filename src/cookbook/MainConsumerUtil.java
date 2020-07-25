package cookbook;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class MainConsumerUtil {
    static class Ordering {
        void lock() {
        }

        void delete() throws SQLException {
        }
    }
    public static void main(String[] args) {
        Ordering ordering = new Ordering();
        RetryConsumerUtil.<Ordering, SQLException>newBuilder()
                .retryConsumer((rz) -> {
                    rz.lock();
                    rz.delete();
                    throw new SQLException("RaHu");
                })
                .noOfRetry(1)
                .delayInterval(1, TimeUnit.MILLISECONDS)
                .retryOn(SQLException.class)
                .build()
                .retryNoCatched(ordering);
    }
}
