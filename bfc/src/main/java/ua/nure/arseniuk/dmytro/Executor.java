package ua.nure.arseniuk.dmytro;


import ua.nure.arseniuk.dmytro.command.Command;

import java.util.List;

public interface Executor {

    public void execute(List<Command> commands);

    public String getOutput();
}
