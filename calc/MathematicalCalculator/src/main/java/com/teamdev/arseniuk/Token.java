package com.teamdev.arseniuk;

import com.teamdev.arseniuk.impl.CalculationStack;

public abstract class Token {

    private final int parsingIndex;

    public Token(int parsingIndex) {
        this.parsingIndex = parsingIndex;
    }

    public int getParsingIndex() {
        return parsingIndex;
    }

    public abstract void execute(CalculationStack stack) throws CalculationException;
}
