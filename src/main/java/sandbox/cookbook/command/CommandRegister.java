package sandbox.cookbook.command;

@FunctionalInterface
public interface CommandRegister {
    void register(Command.CmdType cmdTyoe, Command command);
}
