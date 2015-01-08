package com.teamdev.arseniuk;

import com.teamdev.arseniuk.impl.CalculationStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BinaryOperation extends Token implements Comparable<BinaryOperation> {
    private final Logger logger = LoggerFactory.getLogger(BinaryOperation.class);

    private final Operation operation;

    public BinaryOperation(Operation operation, int parsingIndex) {
        super(parsingIndex);
        this.operation = operation;
    }

    public abstract double calculate(double leftOperand, double rightOperand);

    public Operation getOperation() {
        return operation;
    }

    public int getPriority() {
        return operation.getPriority();
    }

    @Override
    public void execute(CalculationStack stack) {
        stack.pushOperation(this);
    }

    @Override
    public int compareTo(BinaryOperation other) {
        logger.info("Comparing operations", this, other);
        final int res = this.getPriority() - other.getPriority();
        if (res == 0) {
            return this.getOperation().getAssociativity().compareTo(other.getOperation().getAssociativity());
        } else
            return res;
    }
}
