package com.teamdev.arseniuk.impl.parser;

import com.teamdev.arseniuk.*;
import com.teamdev.arseniuk.impl.ExpressionReader;
import com.teamdev.arseniuk.impl.function.MaxFunction;
import com.teamdev.arseniuk.impl.function.MinFunction;
import com.teamdev.arseniuk.impl.function.SqrtFunction;
import com.teamdev.arseniuk.impl.function.SumFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.teamdev.arseniuk.Function.*;

public class FunctionParser implements CalculationParser {
    private final Logger logger = LoggerFactory.getLogger(FunctionParser.class);
    private final HashMap<Function, FunctionCreator> functions = new HashMap<Function, FunctionCreator>() {{
        put(SQRT, new FunctionCreator() {
            @Override
            public AbstractFunction getInstance(int parsingIndex) {
                return new SqrtFunction(parsingIndex);
            }
        });
        put(MIN, new FunctionCreator() {
            @Override
            public AbstractFunction getInstance(int parsingIndex) {
                return new MinFunction(parsingIndex);
            }
        });
        put(MAX, new FunctionCreator() {
            @Override
            public AbstractFunction getInstance(int parsingIndex) {
                return new MaxFunction(parsingIndex);
            }
        });
        put(SUM, new FunctionCreator() {
            @Override
            public AbstractFunction getInstance(int parsingIndex) {
                return new SumFunction(parsingIndex);
            }
        });
    }};

    @Override
    public Token parse(ExpressionReader reader) {
        logger.info("trying to parse function.");
        final String expression = reader.getRemainExpression();
        final Token token;
        for (String presentation : Function.getPresentations()) {
            if (expression.startsWith(presentation)) {
                token = functions.get(Function.get(presentation)).getInstance(reader.getIndex());
                reader.incrementIndex(presentation.length());
                return token;
            }
        }
        return null;
    }
}
