package dp.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command1<T, V> {

    void execute(T type, V value);

    static <T, V> Command1<T, V> of(ConsumerCommandRegister<T, V> consumerCommandRegister) {
        Map<T, ExtConsumer<V>> map = new HashMap<>();
        consumerCommandRegister.accept(map::put);
        return (type, value) -> map.get(type).execute(value);
    }

    static <T, V> ConsumerCommandRegister<T, V> createRegister() {
        return commandRegister -> {
        };
    }

    @FunctionalInterface
    interface ConsumerCommandRegister<T, V> extends Consumer<CommandRegister<T, V>> {
        default ConsumerCommandRegister<T, V> register(T type, ExtConsumer<V> command) {
            return commandRegister -> {
                this.accept(commandRegister);
                commandRegister.takes(type, command);
            };
        }
    }

    @FunctionalInterface
    interface CommandRegister<T, V> {
        void takes(T type, ExtConsumer<V> command);
    }

    @FunctionalInterface
    interface ExtConsumer<V> {
        void execute(V value);
    }
}
