package com.teamdev.arseniuk.impl.parser;

import com.teamdev.arseniuk.CalculationParser;
import com.teamdev.arseniuk.Parenthesis;
import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.ExpressionReader;
import com.teamdev.arseniuk.impl.operation.LeftParenthesis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeftParenthesisParser implements CalculationParser {
    private final Logger logger = LoggerFactory.getLogger(LeftParenthesis.class);

    @Override
    public Token parse(ExpressionReader reader) {
        logger.info("Trying to parse left parenthesis");
        final String expression = reader.getRemainExpression();
        final Token token;
        if (expression.charAt(0) == Parenthesis.LEFT_PARENTHESIS.getSymbol()) {
            token = new LeftParenthesis(reader.getIndex());
            reader.incrementIndex(1);
            return token;
        }
        return null;
    }
}
