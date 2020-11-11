package dp.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command<K> {

    void execute(K cmdType);

    static <K> Command<K> of(Consumer<CommandRegister<K>> consumerCommandRegister) {
        Map<K, Command<K>> map = new HashMap<>();
        consumerCommandRegister.accept(map::put);
        return type -> map.get(type).execute(type);
    }

    static <K> ConsumerCommandRegister<K> createRegister() {
        return commandRegister -> {};
    }

    @FunctionalInterface
    interface ConsumerCommandRegister<K> extends Consumer<CommandRegister<K>> {
        default ConsumerCommandRegister<K> register(K type, Command<K> command) {
            return commandRegister -> {
               this.accept(commandRegister);
                commandRegister.register(type, command);
            };
        }
    }

    @FunctionalInterface
    interface CommandRegister<K> {
        void register(K cmdType, Command<K> command);
    }
}
