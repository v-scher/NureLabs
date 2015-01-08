package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.BinaryOperation;
import com.teamdev.arseniuk.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Division extends BinaryOperation {
    private final Logger logger = LoggerFactory.getLogger(Division.class);

    public Division(int parsingIndex) {
        super(Operation.DIVISION, parsingIndex);
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {
        logger.info("calculate: ", leftOperand, rightOperand);
        return leftOperand / rightOperand;
    }
}
