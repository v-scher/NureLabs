package com.teamdev.arseniuk.impl.function;

import com.teamdev.arseniuk.AbstractFunction;
import com.teamdev.arseniuk.CalculationException;

import java.util.Arrays;
import java.util.Collections;

public class MaxFunction extends AbstractFunction {

    public MaxFunction(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public double calculate(Double... arguments) throws CalculationException {
        return Collections.max(Arrays.asList(arguments));
    }
}
