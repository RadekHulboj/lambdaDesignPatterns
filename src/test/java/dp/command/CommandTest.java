package dp.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static dp.command.ExecuteCommand.CommandType.Executor;
import static dp.command.ExecuteCommand.CommandType.Victim;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class CommandTest {

    @Test
    @DisplayName("The command object run 3 times mocked method execute")
    void commandExecutedThreeTimes() {
        // given
        ExecuteCommand mock = Mockito.mock(ExecuteCommand.class);
        Command<ExecuteCommand.CommandType> of = Command.of(Command
                .<ExecuteCommand.CommandType>createRegister()
                .register(Executor, mock::execute)
                .register(Victim, mock::execute));
        // when
        of.execute(Executor);
        of.execute(Executor);
        of.execute(Victim);
        // then
        verify(mock, times(2)).execute(Executor);
        verify(mock).execute(Victim);
    }
}