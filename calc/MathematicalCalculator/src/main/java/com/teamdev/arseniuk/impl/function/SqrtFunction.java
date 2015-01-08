package com.teamdev.arseniuk.impl.function;

import com.teamdev.arseniuk.AbstractFunction;
import com.teamdev.arseniuk.CalculationException;

public class SqrtFunction extends AbstractFunction {

    public SqrtFunction(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public double calculate(Double... arguments) throws CalculationException {
        if (arguments.length != 1) {
            throw new CalculationException("Required one argument in sqrt function", getParsingIndex());
        }
        return Math.sqrt(arguments[0]);
    }
}
