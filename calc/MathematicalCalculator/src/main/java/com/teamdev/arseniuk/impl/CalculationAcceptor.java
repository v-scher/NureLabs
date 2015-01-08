package com.teamdev.arseniuk.impl;

import com.google.common.base.Preconditions;
import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.CalculationParser;
import com.teamdev.arseniuk.StateAcceptor;
import com.teamdev.arseniuk.Token;
import com.teamdev.arseniuk.impl.parser.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.teamdev.arseniuk.impl.State.*;

public class CalculationAcceptor implements StateAcceptor<State, CalculationContext, CalculationException> {
    private final Logger logger = LoggerFactory.getLogger(CalculationAcceptor.class);

    private final HashMap<State, CalculationParser> parsers = new HashMap<State, CalculationParser>() {{
        put(NUMBER, new NumberParser());
        put(BINARY_OPERATION, new OperationParser());
        put(LEFT_PARENTHESIS, new LeftParenthesisParser());
        put(RIGHT_PARENTHESIS, new RightParenthesisParser());
        put(FUNCTION, new FunctionParser());
        put(ARGUMENT_SEPARATOR, new ArgumentSeparatorParser());
        put(FINISH, new EndOfExpressionParser());
    }};

    @Override
    public boolean isAcceptableState(CalculationContext context, State state) throws CalculationException {
        logger.info("Getting possible parser for state.");
        final CalculationParser parser = parsers.get(state);
        Preconditions.checkArgument(parser != null, "Parser not found for state: ", state);
        final Token token = parser.parse(context.getReader());
        if (token == null) {
            return false;
        }
        logger.info("Executing token.");
        token.execute(context.getStack());
        return true;
    }
}
