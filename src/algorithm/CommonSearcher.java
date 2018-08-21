package algorithm;

import algorithm.Searchable;
import algorithm.Searcher;
import algorithm.Solution;
import algorithm.State;

import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
    protected PriorityQueue<State<T>> openList;

    private int evaluatedNodes;
    public CommonSearcher() {
        openList = new PriorityQueue<State<T>>();
        evaluatedNodes = 0;
    }
    protected State<T> popOpenList() {
        evaluatedNodes++;
        return openList.poll();
    }
    @Override
    public abstract Solution search(Searchable<T> s);
    public Solution<T> backTrace(State<T> goalState, State<T> initialState) {
        ArrayList<T> list = new ArrayList<T>();
        list.add(goalState.getState());
        State<T> currentState = goalState;
        while(currentState.getCameFrom() != null){
            list.add(currentState.getCameFrom().getState());
            currentState = currentState.getCameFrom();
        }
        Solution<T> solution = new Solution<T>();
        solution.setSolution(list);
        return solution;
    }

}
