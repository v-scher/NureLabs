package com.teamdev.arseniuk.impl.parser;

import com.teamdev.arseniuk.*;
import com.teamdev.arseniuk.impl.ExpressionReader;
import com.teamdev.arseniuk.impl.operation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.teamdev.arseniuk.Operation.*;

public class OperationParser implements CalculationParser {

    private final Logger logger = LoggerFactory.getLogger(Subtraction.class);

    private final HashMap<Operation, CommandCreator> operations = new HashMap<Operation, CommandCreator>() {{
        put(ADDITION, new CommandCreator() {
            @Override
            public BinaryOperation getInstance(int parsingIndex) {
                return new Addition(parsingIndex);
            }
        });
        put(SUBTRACTION, new CommandCreator() {
            @Override
            public BinaryOperation getInstance(int parsingIndex) {
                return new Subtraction(parsingIndex);
            }
        });
        put(MULTIPLICATION, new CommandCreator() {
            @Override
            public BinaryOperation getInstance(int parsingIndex) {
                return new Multiplication(parsingIndex);
            }
        });
        put(DIVISION, new CommandCreator() {
            @Override
            public BinaryOperation getInstance(int parsingIndex) {
                return new Division(parsingIndex);
            }
        });
        put(INVOLUTION, new CommandCreator() {
            @Override
            public BinaryOperation getInstance(int parsingIndex) {
                return new Involution(parsingIndex);
            }
        });
    }};

    @Override
    public Token parse(ExpressionReader reader) {
        logger.info("trying to parse operation.");
        final String expression = reader.getRemainExpression();
        final Token token;
        for (String presentation : Operation.getPresentations()) {
            if (expression.startsWith(presentation)) {
                token = operations.get(Operation.get(presentation)).getInstance(reader.getIndex());
                reader.incrementIndex(presentation.length());
                return token;
            }
        }
        return null;
    }
}