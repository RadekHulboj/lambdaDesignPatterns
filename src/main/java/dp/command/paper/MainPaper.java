package dp.command.paper;

public class MainPaper {
    enum CmdType {
        NEW,
        DELETE,
    }

    public static void main (String... args) {
        Command.ConsumerCommandConsumer<CmdType> register = Command.ConsumerCommandConsumer.<CmdType>init()
                .register(CmdType.NEW, cmdType -> System.out.println(cmdType.toString()))
                .register(CmdType.DELETE, cmdType -> System.out.println(cmdType.toString()));

        Command<CmdType> command = Command.of(register);
        command.execute(CmdType.DELETE);
        command.execute(CmdType.NEW);

    }
}
