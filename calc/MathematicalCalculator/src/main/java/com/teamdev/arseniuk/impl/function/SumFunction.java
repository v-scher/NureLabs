package com.teamdev.arseniuk.impl.function;

import com.teamdev.arseniuk.AbstractFunction;
import com.teamdev.arseniuk.CalculationException;

public class SumFunction extends AbstractFunction {
    public SumFunction(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public double calculate(Double... arguments) throws CalculationException {
        double result = 0;
        
        for (Double argument : arguments) {
            result += argument;
        }

        return result;
    }
}
