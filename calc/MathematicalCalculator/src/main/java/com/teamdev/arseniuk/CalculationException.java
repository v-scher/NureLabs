package com.teamdev.arseniuk;

public class CalculationException extends Exception {

    private int cursorPosition;

    public CalculationException(String message, int cursorPosition) {
        super(message);
        this.cursorPosition = cursorPosition;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + cursorPosition;
    }
}
