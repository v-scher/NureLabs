package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

public class Decrement extends OptimizedCommand {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Decrement newInstance() {
        return new Decrement();
    }
}
