package maze;

import algorithm.Searchable;
import algorithm.State;
import test.Grid;
import test.Maze;

import java.util.ArrayList;
import java.util.List;

public class MazeSearchable implements Searchable<Grid> {
    State<Grid> initialState;
    State<Grid> finalState;
    Maze m;

    public MazeSearchable(Maze m) {
        this.initialState = new State<Grid>(m.getEntrance());
        this.finalState = new State<Grid>(m.getExit());
        this.m = m;
    }

    @Override
    public State<Grid> getInitialState() {
        return initialState;
    }

    @Override
    public boolean isGoalState(State<Grid> state) {
        return state.getState().row == m.getExit().row && state.getState().col == m.getExit().col;
    }

    @Override
    public List<State<Grid>> getAllPossibleStates(State<Grid> s) {
        List<Grid> nextMoves = m.getPossibleMoves(s.getState());
        List<State<Grid>> nextPossibleState = new ArrayList<>();


        // If I'm not my father, go to for loop.
        if(!(s.getCameFrom() != null && s.getState().row == s.getCameFrom().getState().row && s.getState().col == s.getCameFrom().getState().col)) {
            for (int i = 0; i < nextMoves.size(); i++) {
                if (s.getState().col == nextMoves.get(i).col || s.getState().row == nextMoves.get(i).row) {
                    nextPossibleState.add(new State<Grid>(nextMoves.get(i), s, s.getCost() + 1));
                }
            }
        }

        return nextPossibleState;
    }
}
