package algorithm;

import java.util.*;


public class DFS<T> extends CommonSearcher<T> {
    Stack<State<T>> stack = new Stack<>();
    @Override
    public Solution search(Searchable<T> s) {
        stack.add(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<State<T>>();
        while (stack.size() > 0) {
            State<T> n = stack.pop();
            closedSet.add(n);
            if (s.isGoalState(n)) {
                return backTrace(n, s.getInitialState());
            }
            List<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !stack.contains(state)) {
                    state.setCameFrom(n);
                    stack.add(state);
                }
            }
        }
        return null;
    }
}
