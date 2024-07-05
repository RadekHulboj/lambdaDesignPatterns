package dp.command.paper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command<T> {

    void execute(T cmd);

    static <T> Command<T> of(ConsumerCommandConsumer<T> consumer) {
        Map<T, Consumer<T>> map = new HashMap<>();
        consumer.accept(map::put);
        return cmd -> map.get(cmd).accept(cmd);
    }

    @FunctionalInterface
    interface CommandRegister<T> extends BiConsumer<T, Consumer<T>>  {
        default void takeParams(T cmd, Consumer<T> consumer) {
            accept(cmd, consumer);
        }
    }
    @FunctionalInterface
    interface ConsumerCommandConsumer<T> extends Consumer<CommandRegister<T>> {
        default ConsumerCommandConsumer<T> register(T cmd, Consumer<T> consumer) {
            return cmdRegister -> {
                accept(cmdRegister);
                cmdRegister.takeParams(cmd, consumer);
            };
        }

        static <T> ConsumerCommandConsumer<T> init() {
            return consumer -> {};
        }
    }
}
