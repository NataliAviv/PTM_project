package algorithm;

import java.util.List;

public interface Searchable<T>{
    State<T> getInitialState();
    boolean isGoalState(State<T> state);
    List<State<T>> getAllPossibleStates(State<T> s);
}
