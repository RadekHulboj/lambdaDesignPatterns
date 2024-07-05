package dp.command.paper;

public class MainPaper {
    enum CmdType {
        NEW,
        DELETE,
    }

    public static void main(String... args) {
        Command.ConsumerCommandConsumer<CmdType> register = Command.ConsumerCommandConsumer.<CmdType>init()
                .register(CmdType.NEW, () -> System.out.println("NEW"))
                .register(CmdType.DELETE, () -> System.out.println("DELETE"));

        Command<CmdType> command = Command.of(register);
        command.execute(CmdType.DELETE);
        command.execute(CmdType.NEW);

    }
}
