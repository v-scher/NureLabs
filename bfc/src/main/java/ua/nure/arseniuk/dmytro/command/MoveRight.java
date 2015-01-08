package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

public class MoveRight extends OptimizedCommand {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public MoveRight newInstance() {
        return new MoveRight();
    }

}
