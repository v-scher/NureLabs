package com.teamdev.arseniuk.impl;

import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.Token;

public class EndOfExpression extends Token {

    public EndOfExpression(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public void execute(CalculationStack stack) throws CalculationException {
        if (!stack.getParenthesisStack().isEmpty())
            throw new CalculationException("Right parenthesis missed at position ", getParsingIndex());
        stack.executeAllOperations();
    }
}
