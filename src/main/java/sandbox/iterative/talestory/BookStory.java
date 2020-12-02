package sandbox.iterative.talestory;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BookStory {
    private BookStory() {
    }

    static void story(BiConsumer<Consumer<Integer>, Supplier<String>> biConsumer) {
        Supplier<String> punishment = () -> "not enough money";
        Consumer<Integer> goodness = integer -> { };
        biConsumer.accept(goodness, punishment);
    }
}
