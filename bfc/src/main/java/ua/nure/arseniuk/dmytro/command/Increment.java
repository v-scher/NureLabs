package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

public class Increment extends OptimizedCommand {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Increment newInstance() {
        return new Increment();
    }

}
