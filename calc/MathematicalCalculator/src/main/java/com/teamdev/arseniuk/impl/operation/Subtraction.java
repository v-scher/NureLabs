package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.BinaryOperation;
import com.teamdev.arseniuk.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subtraction extends BinaryOperation {

    private final Logger logger = LoggerFactory.getLogger(Subtraction.class);

    public Subtraction(int parsingIndex) {
        super(Operation.SUBTRACTION, parsingIndex);
    }

    @Override
    public double calculate(double leftOperand, double rightOperand) {

        logger.info("calculate: ", leftOperand, rightOperand);
        return leftOperand - rightOperand;
    }

}
