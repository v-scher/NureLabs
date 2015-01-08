package com.teamdev.arseniuk;

public enum Parenthesis {

    LEFT_PARENTHESIS('('),
    RIGHT_PARENTHESIS(')');

    private char symbol;

    Parenthesis(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

}
