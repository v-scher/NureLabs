package ua.nure.arseniuk.dmytro;

import ua.nure.arseniuk.dmytro.command.*;

import java.util.*;

public class BrainfuckCompiler implements CommandVisitor, Compiler {

    private final HashMap<Character, Command> mapCommand;
    private final List<Command> commands;
    private final Deque<Cycle> cycleCommands;

    public BrainfuckCompiler() {
        commands = new LinkedList<Command>();
        cycleCommands = new ArrayDeque<Cycle>();
        mapCommand = new HashMap<Character, Command>();
        mapCommand.put('>', new MoveRight());
        mapCommand.put('<', new MoveLeft());
        mapCommand.put('+', new Increment());
        mapCommand.put('-', new Decrement());
        mapCommand.put('.', new Print());
        mapCommand.put('[', new Cycle());
        mapCommand.put(']', new EndCycle());
    }

    @Override
    public List<Command> compile(String input) {
        Command command;
        for (char c : input.toCharArray()) {
            command = mapCommand.get(c);
            if (command != null) {
                command = command.newInstance();
                command.accept(this);
            }
        }
        return commands;
    }

    private void saveCommand(Command command) {
        if (!cycleCommands.isEmpty()) {
            cycleCommands.peek().add(command);
        } else {
            commands.add(command);
        }
    }

    @Override
    public void visit(Increment increment) {
        saveCommand(increment);
    }

    @Override
    public void visit(Decrement decrement) {
        saveCommand(decrement);
    }

    @Override
    public void visit(MoveLeft moveLeft) {
        saveCommand(moveLeft);
    }

    @Override
    public void visit(MoveRight moveRight) {
        saveCommand(moveRight);
    }

    @Override
    public void visit(Print print) {
        saveCommand(print);
    }

    @Override
    public void visit(Cycle cycle) {
        cycleCommands.push(cycle);
    }

    @Override
    public void visit(EndCycle endCycle) {
        saveCommand(cycleCommands.pop());
    }
}
