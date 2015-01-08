package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.CalculationStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RightParenthesis extends Token {
    private final Logger logger = LoggerFactory.getLogger(RightParenthesis.class);

    public RightParenthesis(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public void execute(CalculationStack stack) throws CalculationException {
        if (stack.getParenthesisStack().isEmpty()) {
            throw new CalculationException("Left parenthesis missed at position ", getParsingIndex());
        }
        logger.info("Adding right parenthesis");
        stack.addRightParenthesis();
    }
}
