package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

import java.util.LinkedList;
import java.util.List;

public class Cycle implements Command {

    private List<Command> commands;

    public Cycle() {
        commands = new LinkedList<Command>();
    }

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Cycle newInstance() {
        return new Cycle();
    }


    public void add(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }


}
