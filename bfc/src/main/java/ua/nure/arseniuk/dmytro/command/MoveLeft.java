package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

public class MoveLeft extends OptimizedCommand {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MoveLeft newInstance() {
        return new MoveLeft();
    }

}
