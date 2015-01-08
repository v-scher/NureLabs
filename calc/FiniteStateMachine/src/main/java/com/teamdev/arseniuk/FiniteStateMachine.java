package com.teamdev.arseniuk;


public abstract class FiniteStateMachine<State extends Enum,
        Result,
        Context extends StateMachineContext<State, Context, ExecutionError>, ExecutionError extends Exception> {

    public Result run(Context context) throws ExecutionError {

        final TransitionMatrix<State> matrix = context.getTransitionMatrix();
        State currentState = matrix.getStartState();

        while (currentState != matrix.getFinishState()) {

            final State nextState = moveForward(context, currentState);
            if (nextState == null) {
                deadlock(context, currentState);
            }
            currentState = nextState;
        }

        return finish(context);
    }

    private State moveForward(Context context, State currentState) throws ExecutionError {

        final StateAcceptor<State, Context, ExecutionError> stateAcceptor = context.getAcceptor();
        final TransitionMatrix<State> matrix = context.getTransitionMatrix();

        for (State possibleState : matrix.getPossibleStates(currentState)) {
            if (stateAcceptor.isAcceptableState(context, possibleState)) {
                return possibleState;
            }
        }
        return null;
    }

    protected abstract Result finish(Context context);

    protected abstract void deadlock(Context context, State currentState) throws ExecutionError;


}
