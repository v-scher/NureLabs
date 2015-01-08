package com.teamdev.arseniuk.impl.parser;

import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.CalculationParser;
import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.CalculationStack;
import com.teamdev.arseniuk.impl.ExpressionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentSeparatorParser implements CalculationParser {

    private final Logger logger = LoggerFactory.getLogger(ArgumentSeparatorParser.class);

    @Override
    public Token parse(ExpressionReader reader) {
        logger.info("Trying to parse argument separator.");
        final String expression = reader.getRemainExpression();
        if (reader.isEnd()) {
            return null;
        }

        if (expression.charAt(0) == ',') {
            reader.incrementIndex(1);
            return new Token(reader.getIndex() - 1) {
                @Override
                public void execute(CalculationStack stack) throws CalculationException {
                    stack.executeFunctionArgument();
                }
            };
        }
        return null;
    }
}
