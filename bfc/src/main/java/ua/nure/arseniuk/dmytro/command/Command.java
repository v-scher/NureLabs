package ua.nure.arseniuk.dmytro.command;


import ua.nure.arseniuk.dmytro.CommandVisitor;

/**
 * Element of visitor pattern.
 */
public interface Command {

    public void accept(CommandVisitor visitor);

    public Command newInstance();
}
