package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.BinaryOperation;
import com.teamdev.arseniuk.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Addition extends BinaryOperation {
    private final Logger logger = LoggerFactory.getLogger(Addition.class);

    public Addition(int parsingIndex) {
        super(Operation.ADDITION, parsingIndex);
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {

        logger.info("calculate ", leftOperand, rightOperand);
        return leftOperand + rightOperand;
    }
}
