package ua.nure.arseniuk.dmytro.command;

public abstract class OptimizedCommand implements Command {
    private int count = 1;

    public int getCount() {
        return count;
    }

}
