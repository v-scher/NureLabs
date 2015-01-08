package com.teamdev.arseniuk;

public interface StateMachineContext<State extends Enum,
        Context extends StateMachineContext<State, Context, ExecutionError>,
        ExecutionError extends Exception> {

    public TransitionMatrix<State> getTransitionMatrix();

    public StateAcceptor<State, Context, ExecutionError> getAcceptor();

}
