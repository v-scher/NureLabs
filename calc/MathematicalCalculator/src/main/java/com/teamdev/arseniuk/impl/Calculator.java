package com.teamdev.arseniuk.impl;

import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.FiniteStateMachine;
import com.teamdev.arseniuk.MathCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Calculator extends FiniteStateMachine<State, Double, CalculationContext, CalculationException> implements MathCalculator {

    private final Logger logger = LoggerFactory.getLogger(Calculator.class);

    @Override
    protected Double finish(CalculationContext context) {
        logger.info("calculation finished.");
        return context.getStack().getOperandStack().pop();
    }

    @Override
    protected void deadlock(CalculationContext context, State currentState) throws CalculationException {
        throw new CalculationException("Deadlock in state " + currentState + " at position ", context.getReader().getIndex());
    }

    @Override
    public List<Double> calculate(String expression) throws CalculationException {
        logger.info("Calculation starts");
        final String[] expressions = expression.split(";");
        List<Double> results = new ArrayList<>();
        for (String singleExpression : expressions) {
            Double result = run(new CalculationContext(singleExpression.replace(",", ", ")));
            results.add(result);
        }


        return results;

    }
}
