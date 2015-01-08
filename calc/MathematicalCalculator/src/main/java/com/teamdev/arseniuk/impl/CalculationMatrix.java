package com.teamdev.arseniuk.impl;

import com.teamdev.arseniuk.TransitionMatrix;

import java.util.HashMap;
import java.util.Set;

import static com.teamdev.arseniuk.impl.State.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;

public class CalculationMatrix implements TransitionMatrix<State> {
    private final HashMap<State, Set<State>> matrix = new HashMap<State, Set<State>>() {{
        put(START, of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        put(NUMBER, of(BINARY_OPERATION, RIGHT_PARENTHESIS, ARGUMENT_SEPARATOR, FINISH));
        put(BINARY_OPERATION, of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        put(LEFT_PARENTHESIS, of(LEFT_PARENTHESIS, NUMBER));
        put(RIGHT_PARENTHESIS, of(BINARY_OPERATION, RIGHT_PARENTHESIS, ARGUMENT_SEPARATOR, FINISH));
        put(FUNCTION, of(LEFT_PARENTHESIS));
        put(ARGUMENT_SEPARATOR, of(NUMBER, LEFT_PARENTHESIS, FUNCTION));
        put(FINISH, noneOf(State.class));
    }};

    @Override
    public State getStartState() {
        return START;
    }

    @Override
    public State getFinishState() {
        return FINISH;
    }

    @Override
    public Set<State> getPossibleStates(State state) {
        return matrix.get(state);
    }
}
