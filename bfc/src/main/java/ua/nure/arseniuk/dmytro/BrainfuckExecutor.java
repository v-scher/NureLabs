package ua.nure.arseniuk.dmytro;

import ua.nure.arseniuk.dmytro.command.*;

import java.util.List;

public class BrainfuckExecutor implements CommandVisitor, Executor {

    private final BrainfuckVM brainfuckVM;

    public BrainfuckExecutor() {
        brainfuckVM = new BrainfuckVM();
    }

    @Override
    public void execute(List<Command> commands) {
        for (Command command : commands) {
            command.accept(this);
        }
    }

    @Override
    public String getOutput() {
        return brainfuckVM.getBuilderStrings();
    }

    @Override
    public void visit(Increment increment) {
        brainfuckVM.increaseCurrentCell(increment.getCount());
    }

    @Override
    public void visit(Decrement decrement) {
        brainfuckVM.decreaseCurrentCell(decrement.getCount());
    }

    @Override
    public void visit(MoveLeft moveLeft) {
        brainfuckVM.decreaseIndex(moveLeft.getCount());
    }

    @Override
    public void visit(MoveRight moveRight) {
        brainfuckVM.increaseIndex(moveRight.getCount());
    }

    @Override
    public void visit(Print print) {
        brainfuckVM.printInBuilder((char) brainfuckVM.getCurrentCell());
        System.out.print((char) brainfuckVM.getCurrentCell());
    }

    @Override
    public void visit(EndCycle endCycle) {

    }

    @Override
    public void visit(Cycle cycle) {
        while (brainfuckVM.getCurrentCell() != 0) {
            for (Command command : cycle.getCommands()) {
                command.accept(this);
            }
        }
    }
}
