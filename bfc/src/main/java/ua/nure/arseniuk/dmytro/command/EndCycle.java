package ua.nure.arseniuk.dmytro.command;

import ua.nure.arseniuk.dmytro.CommandVisitor;

public class EndCycle implements Command {

    @Override
    public void accept(CommandVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public EndCycle newInstance() {
        return new EndCycle();
    }
}
