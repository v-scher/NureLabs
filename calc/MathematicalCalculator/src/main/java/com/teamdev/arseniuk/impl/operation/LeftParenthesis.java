package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.CalculationStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftParenthesis extends Token {

    private final Logger logger = LoggerFactory.getLogger(LeftParenthesis.class);


    public LeftParenthesis(int parsingIndex) {
        super(parsingIndex);
    }

    @Override
    public void execute(CalculationStack stack) {
        logger.info("Adding left parenthesis to parenthesis stack.");
        stack.addLeftParenthesis();
    }
}
