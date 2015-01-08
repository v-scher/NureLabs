package com.teamdev.arseniuk.impl;

import com.teamdev.arseniuk.AbstractFunction;
import com.teamdev.arseniuk.BinaryOperation;
import com.teamdev.arseniuk.CalculationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class CalculationStack {

    private class FunctionArgument {
        private final int operationCount;
        private final int operandCount;
        private final int parenthesisCount;

        private FunctionArgument(int operationCount, int operandCount, int parenthesisCount) {
            this.operationCount = operationCount;
            this.operandCount = operandCount;
            this.parenthesisCount = parenthesisCount;
        }

        public int getOperationCount() {
            return operationCount;
        }

        public int getOperandCount() {
            return operandCount;
        }

        public int getParenthesisCount() {
            return parenthesisCount;
        }
    }

    private final Logger logger = LoggerFactory.getLogger(CalculationStack.class);

    private final Deque<Double> operandStack = new ArrayDeque<>();
    private final Deque<BinaryOperation> operationStack = new ArrayDeque<>();
    private final Deque<Integer> parenthesisStack = new ArrayDeque<>();
    private final Deque<FunctionArgument> functionArgumentStack = new ArrayDeque<>();
    private final Deque<AbstractFunction> functionStack = new ArrayDeque<>();

    public Deque<Double> getOperandStack() {
        return operandStack;
    }

    public Deque<Integer> getParenthesisStack() {
        return parenthesisStack;
    }

    public void pushOperand(double operand) {
        operandStack.push(operand);
    }

    public void executeTopOperation() {
        logger.info("Executing top operation");
        final Double rightOperand = operandStack.pop();
        final Double leftOperand = operandStack.pop();
        final BinaryOperation operation = operationStack.pop();
        final Double result = operation.calculate(leftOperand, rightOperand);
        operandStack.push(result);
    }

    public void executeAllOperations() {
        logger.info("Executing all operations");
        while (!operationStack.isEmpty()) {
            executeTopOperation();
        }
    }

    public void executeFunctionArgument() {
        logger.info("Executing function argument");
        final FunctionArgument argument = functionArgumentStack.peek();
        while (!operationStack.isEmpty() && argument != null && operationStack.size() > argument.getOperationCount()) {
            executeTopOperation();
        }
    }

    public void addLeftParenthesis() {
        logger.info("Pushing current count of operations to parenthesis stack.");
        parenthesisStack.push(operationStack.size());
    }

    public void addRightParenthesis() throws CalculationException {
        logger.info("Executing all operations till last left parenthesis.");
        if (isParenthesisBelongsFunction()) {
            executeFunction();
        } else {
            executeOperationsInParenthesis();
        }
    }

    private boolean isParenthesisBelongsFunction() {
        return (!functionArgumentStack.isEmpty())
                && functionArgumentStack.peek().getParenthesisCount() + 1 == parenthesisStack.size();
    }

    private void executeOperationsInParenthesis() {

        final Integer lastLeftParenthesis = parenthesisStack.pop();
        while (operationStack.size() > lastLeftParenthesis) {
            executeTopOperation();
        }
    }

    private void executeFunction() throws CalculationException {
        executeFunctionArgument();
        final FunctionArgument functionInfo = functionArgumentStack.pop();
        final ArrayList<Double> arguments = new ArrayList<>();
        while (operandStack.size() > functionInfo.getOperandCount()) {
            arguments.add(operandStack.pop());
        }
        final AbstractFunction function = functionStack.pop();
        final double result = function.calculate((Double[]) arguments.toArray(new Double[0]));
        operandStack.push(result);
        parenthesisStack.pop();
    }

    public void pushFunction(AbstractFunction function) {
        functionArgumentStack.push(
                new FunctionArgument(operationStack.size(), operandStack.size(), parenthesisStack.size())
        );
        functionStack.push(function);
    }

    public void pushOperation(BinaryOperation operation) {
        logger.info("pushing operation to operation stack.");
        if (parenthesisLastToken()) {
            logger.info("Adding operation without checking priority. Last token was left parenthesis.");
            operationStack.push(operation);
            return;
        }
        while (!operationStack.isEmpty() && operationStack.peek().compareTo(operation) > 0) {
            logger.info("Checking priority and associativity.");
            executeTopOperation();
        }
        operationStack.push(operation);
    }

    public boolean parenthesisLastToken() {
        return (!parenthesisStack.isEmpty() && operationStack.size() == parenthesisStack.peek());
    }

}
