package com.teamdev.arseniuk.impl;

import com.teamdev.arseniuk.CalculationException;
import com.teamdev.arseniuk.StateAcceptor;
import com.teamdev.arseniuk.StateMachineContext;
import com.teamdev.arseniuk.TransitionMatrix;

public class CalculationContext implements StateMachineContext<State, CalculationContext, CalculationException> {
    private final CalculationMatrix matrix = new CalculationMatrix();
    private final CalculationAcceptor acceptor = new CalculationAcceptor();
    private final CalculationStack stack = new CalculationStack();
    private final ExpressionReader reader;


    public CalculationContext(String expression) {
        reader = new ExpressionReader(expression);
    }

    public CalculationStack getStack() {
        return stack;
    }

    @Override
    public TransitionMatrix<State> getTransitionMatrix() {
        return matrix;
    }

    @Override
    public StateAcceptor<State, CalculationContext, CalculationException> getAcceptor() {
        return acceptor;
    }

    public ExpressionReader getReader() {
        return reader;
    }
}
