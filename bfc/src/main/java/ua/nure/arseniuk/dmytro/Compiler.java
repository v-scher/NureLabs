package ua.nure.arseniuk.dmytro;

import ua.nure.arseniuk.dmytro.command.Command;

import java.util.List;

public interface Compiler {

    public List<Command> compile(String input);

}
