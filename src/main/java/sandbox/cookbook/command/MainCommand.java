package sandbox.cookbook.command;

import sandbox.cookbook.command.Command.CmdType;

import java.util.function.Consumer;

public class MainCommand {

    public static void main(String[] args) {
        Consumer<CommandRegister> register = Command
                .register()
                .commandType(CmdType.COPY, new MainCommand()::copyExecution)
                .commandType(CmdType.DELETE, MainCommand::deleteExecution)
                .commandType(CmdType.UPDATE, cmd -> System.out.println(cmd.toString()));

        Command command = Command.of(register);
        command.execute(CmdType.UPDATE);
        command.execute(CmdType.COPY);
    }

    private void copyExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }

    private static void deleteExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }
}
