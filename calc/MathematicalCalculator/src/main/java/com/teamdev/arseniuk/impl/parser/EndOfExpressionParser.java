package com.teamdev.arseniuk.impl.parser;

import com.teamdev.arseniuk.CalculationParser;
import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.EndOfExpression;
import com.teamdev.arseniuk.impl.ExpressionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndOfExpressionParser implements CalculationParser {
    private final Logger logger = LoggerFactory.getLogger(EndOfExpression.class);

    @Override
    public Token parse(ExpressionReader reader) {
        logger.info("Trying to parse end of expression");
        if (!reader.isEnd()) {
            return null;
        }
        return new EndOfExpression(reader.getIndex());
    }
}
