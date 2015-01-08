package com.teamdev.arseniuk;

import java.util.Set;

public interface TransitionMatrix<State extends Enum> {

    public State getStartState();

    public State getFinishState();

    public Set<State> getPossibleStates(State state);
}
