package dp.command;

public class MainCommand {

    enum CmdType {
        COPY,
        DELETE,
        UPDATE,
        UNDO
    }

    public static void main(String[] args) {

        Command<CmdType> command = Command.of(Command
                .<CmdType>createRegister()
                .register(CmdType.COPY, new MainCommand()::copyExecution)
                .register(CmdType.UNDO, MainCommand::deleteExecution)
                .register(CmdType.DELETE, cmd -> System.out.println(cmd.toString()))
                .register(CmdType.UPDATE, cmd -> System.out.println(cmd.toString()))
        );

        command.execute(CmdType.COPY);
        command.execute(CmdType.DELETE);
        command.execute(CmdType.UPDATE);
        command.execute(CmdType.UNDO);
    }

    private void copyExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }

    private static void deleteExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }
}
