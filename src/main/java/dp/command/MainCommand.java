package dp.command;

public class MainCommand {

    enum CmdType {
        COPY,
        UNDO
    }

    public static void main(String[] args) {

        Command.ConsumerCommandRegister<CmdType> register =
                Command.<CmdType>createRegister()
                .register(CmdType.COPY, new MainCommand()::copyExecution)
                .register(CmdType.UNDO, MainCommand::deleteExecution);

        Command<CmdType> command = Command.of(register);

        command.execute(CmdType.COPY);
        command.execute(CmdType.UNDO);
    }

    private void copyExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }

    private static void deleteExecution(CmdType cmd) {
        System.out.println(cmd.toString());
    }
}
