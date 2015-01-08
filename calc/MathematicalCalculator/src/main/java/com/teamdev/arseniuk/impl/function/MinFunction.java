package com.teamdev.arseniuk.impl.function;

import com.teamdev.arseniuk.AbstractFunction;
import com.teamdev.arseniuk.CalculationException;

import java.util.Arrays;
import java.util.Collections;

public class MinFunction extends AbstractFunction {

    public MinFunction(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public double calculate(Double... arguments) throws CalculationException {
        return Collections.min(Arrays.asList(arguments));
    }
}
