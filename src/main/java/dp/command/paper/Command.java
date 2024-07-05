package dp.command.paper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command<T> {

    void execute(T cmd);

    static <T> Command<T> of(ConsumerCommandConsumer<T> consumer) {
        Map<T, Executor> map = new HashMap<>();
        consumer.accept(map::put);
        return cmd -> map.get(cmd).proceed();
    }

    @FunctionalInterface
    interface Executor {
        void proceed();
    }

    @FunctionalInterface
    interface CommandRegister<T> extends BiConsumer<T, Executor>  {
        default void takeParams(T cmd, Executor executor) {
            accept(cmd, executor);
        }
    }
    @FunctionalInterface
    interface ConsumerCommandConsumer<T> extends Consumer<CommandRegister<T>> {
        default ConsumerCommandConsumer<T> register(T cmd, Executor executor) {
            return cmdRegister -> {
                accept(cmdRegister);
                cmdRegister.takeParams(cmd, executor);
            };
        }

        static <T> ConsumerCommandConsumer<T> init() {
            return consumer -> {};
        }
    }
}
