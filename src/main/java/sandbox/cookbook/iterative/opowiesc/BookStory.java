package sandbox.cookbook.iterative.opowiesc;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

class BookStory {
    private BookStory() {
    }

    static void story(BiConsumer<Consumer<Integer>, Supplier<String>> biConsumer) {
        Supplier<String> kara = () -> "malo pieniazkow przywiozla";
        Consumer<Integer> dobro = integer -> {
        };
        biConsumer.accept(dobro, kara);
    }
}
