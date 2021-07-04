package dp.command;

public class MainCommand1 {

    enum CmdType {
        COPY,
        UNDO
    }

    public static void main(String[] args) {

        Command1.ConsumerCommandRegister<CmdType, String> register =
                Command1.<CmdType, String>createRegister()
                        .register(CmdType.COPY, new MainCommand1()::copyExecution)
                        .register(CmdType.UNDO, MainCommand1::deleteExecution);

        Command1<CmdType, String> command = Command1.of(register);

        command.execute(CmdType.COPY,"1");
        command.execute(CmdType.UNDO,"3");
    }

    private void copyExecution(String a) {
        System.out.println(a);
    }

    private static void deleteExecution(String a) {
        System.out.println(a);
    }
}
