package com.teamdev.arseniuk;

import com.teamdev.arseniuk.impl.CalculationStack;

public abstract class AbstractFunction extends Token {

    public AbstractFunction(int parsingIndex) {
        super(parsingIndex);
    }

    public abstract double calculate(Double... arguments) throws CalculationException;

    @Override
    public void execute(CalculationStack stack) throws CalculationException {
        stack.pushFunction(this);
    }
}
