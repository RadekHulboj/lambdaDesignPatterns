package sandbox.cookbook.iterative.obietnica;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Logger;

@FunctionalInterface
public interface Promise {

    Logger logger = Logger.getLogger(Promise.class.getName());

    void execute();

    static Promise of(BiConsumer<Consumer<String>, Consumer<Boolean>> biConsumer) {
        return () -> biConsumer.accept(
                logger::info,
                err -> logger.info(err.toString())
        );
    }

    static void main(String[] args) {
        Promise.of((resolve, reject) ->
                Ec2.runInstances("params", (err, data) -> {
                    if (Boolean.TRUE.equals(err)) {
                        reject.accept(err);
                    } else {
                        resolve.accept(data);
                    }
                })
        ).execute();
    }
}
