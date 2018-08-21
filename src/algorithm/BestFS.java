package algorithm;
import java.util.*;
public class BestFS<T> extends CommonSearcher<T> {

    @Override
    public Solution<T> search(Searchable<T> s) {
        openList.add(s.getInitialState());
        HashSet<State<T>> closedSet = new HashSet<State<T>>();
        while (openList.size() > 0) {
            State<T> n = popOpenList();
            closedSet.add(n);
            if (s.isGoalState(n)){
                return backTrace(n, s.getInitialState());
            }
            List<State<T>> successors = s.getAllPossibleStates(n);
            for (State<T> state : successors) {
                if (!closedSet.contains(state) && !openList.contains(state)) {
                    state.setCameFrom(n);
                    openList.add(state);

                }
            }
        }
        return null;
    }
}
