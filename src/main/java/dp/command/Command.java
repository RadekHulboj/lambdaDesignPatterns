package dp.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command<T> {

    void execute(T type);

    static <T> Command<T> of(ConsumerCommandRegister<T> consumerCommandRegister) {
        Map<T, Command<T>> map = new HashMap<>();
        consumerCommandRegister.accept(map::put);
        return type -> map.get(type).execute(type);
    }

    static <T> ConsumerCommandRegister<T> createRegister() {
        return commandRegister -> {
        };
    }

    @FunctionalInterface
    interface ConsumerCommandRegister<T> extends Consumer<CommandRegister<T>> {
        default ConsumerCommandRegister<T> register(T type, Command<T> command) {
            return commandRegister -> {
                this.accept(commandRegister);
                commandRegister.takes(type, command);
            };
        }
    }

    @FunctionalInterface
    interface CommandRegister<T> {
        void takes(T type, Command<T> command);
    }
}
