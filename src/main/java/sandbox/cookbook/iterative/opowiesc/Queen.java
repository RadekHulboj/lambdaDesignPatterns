package sandbox.cookbook.iterative.opowiesc;

import java.util.Date;
import java.util.function.BiConsumer;

class Queen {
    private Queen() {
      throw new UnsupportedOperationException();
    }
    static void arrived(Date date, BiConsumer<Integer, Boolean> biConsumer) {
        biConsumer.accept(1, false);
    }
}
