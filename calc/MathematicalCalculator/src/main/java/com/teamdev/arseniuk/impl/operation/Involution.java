package com.teamdev.arseniuk.impl.operation;

import com.teamdev.arseniuk.BinaryOperation;
import com.teamdev.arseniuk.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Involution extends BinaryOperation {

    private final Logger logger = LoggerFactory.getLogger(Involution.class);

    public Involution(int parsingIndex) {
        super(Operation.INVOLUTION, parsingIndex);
    }


    @Override
    public double calculate(double leftOperand, double rightOperand) {
        logger.info("calculate ", leftOperand, rightOperand);
        return Math.pow(leftOperand, rightOperand);
    }
}
