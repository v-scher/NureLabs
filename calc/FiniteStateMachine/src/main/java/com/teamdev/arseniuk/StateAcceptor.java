package com.teamdev.arseniuk;

public interface StateAcceptor<State extends Enum,
        Context extends StateMachineContext<State, Context, ExecutionError>,
        ExecutionError extends Exception> {
    public boolean isAcceptableState(Context context, State state) throws ExecutionError;
}
