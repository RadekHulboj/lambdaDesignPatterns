package sandbox.cookbook.command;

import java.util.EnumMap;
import java.util.function.Consumer;

@FunctionalInterface
public interface Command {

    void execute(CmdType cmdTyoe);

    enum CmdType {
        COPY,
        DELETE,
        UPDATE
    }
    static Command of(Consumer<CommandRegister> consumerCommandRegister) {
        EnumMap<CmdType, Command> map = new EnumMap<>(CmdType.class);
        consumerCommandRegister.accept(map::put);
        return type -> map.get(type).execute(type);
    }

    static ConsumerCommandRegister register() {
        return commandRegister -> {};
    }

    interface ConsumerCommandRegister extends Consumer<CommandRegister> {
        default ConsumerCommandRegister commandType(CmdType type, Command command) {
            return commandRegister -> {
                this.accept(commandRegister);
                commandRegister.register(type, command);
            };
        }
    }
}
