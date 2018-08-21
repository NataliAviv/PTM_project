package algorithm;

import server.Board;
import server.PipeGameGrid;

import java.util.*;
public class PipeGameSearchable implements Searchable<PipeGameGrid> {
    State<PipeGameGrid> initialState;
    State<PipeGameGrid> goalState;
    ArrayList<State<PipeGameGrid>> possibleStates = new ArrayList();
    private Map<String, State<PipeGameGrid>> states = new HashMap<String, State<PipeGameGrid>>();
    Board<Character> board;
    //define the board using map
    String[] toDirection(String str){
        switch(str){
            case "s":
                return new String[] { "down", "right", "left", "up" };
            case "J":
                return new String[] { "down", "right" };
            case "L":
                return new String[] { "left", "down" };
            case "7":
                return new String[] { "right", "up" };
            case "F":
                return new String[] { "up", "left" };
            case "|":
                return new String[] { "up", "down" };
            case "-":
                return new String[] { "left", "right" };
            case "g":
                return new String[] { "down", "right", "left", "up" };
            default:
                return null;
        }
    }
//the number(cost) of rotate each character
Integer costOfRotate(String str){
    switch(str){
        case "7,J":
            return 1;
        case "7,L":
            return 2;
        case "7,F":
            return 3;
        case "-,|":
            return 1;
        case "|,-":
            return 1;
        case "J,L":
            return 1;
        case "J,F":
            return 2;
        case "J,7":
            return 3;
        case "L,F":
            return 1;
        case "L,7":
            return 2;
        case "L,J":
            return 3;
        case "F,7":
            return 1;
        case "F,J":
            return 2;
        case "F,L":
            return 3;
        default:
            return null;
    }
}

char[] TheOptionsOfRotate(String str){
    {
        switch(str){
            //the '-' rotate options
            case "up,-":
                return  new char[] { '|' };
            case "down,-":
                return  new char[] { '|' };
            case "left,-":
                return new char[] { '-' };
            case "right,-":
                return new char[] { '-' };
            //the '|' rotate options
            case "up,|":
                return new char[] { '|' };
            case "down,|":
                return new char[] { '|' };
            case "left,|":
                return new char[] { '-' };
            case "right,|":
                return new char[] { '-' };
            //the 'L' rotate options
            case "up,L":
                return new char[] { 'L', 'J' };
            case "down,L":
                return new char[] { '7', 'F' };
            case "left,L":
                return new char[] { 'J', '7' };
            case "right,L":
                return new char[] { 'L', 'F' };
            //the 'F' rotate options
            case "up,F":
                return new char[] { 'L', 'J' };
            case "down,F":
                return new char[] { '7', 'F' };
            case "left,F":
                return new char[] { 'J', '7' };
            case "right,F":
                return new char[] { 'L', 'F' };
            //the '7' rotate options
            case "up,7":
                return new char[] { 'L', 'J' };
            case "down,7":
                return new char[] { '7', 'F' };
            case "left,7":
                return new char[] { 'J', '7' };
            case "right,7":
                return new char[] { 'L', 'F' };
            //the 'J' rotate options
            case "up,J":
                return new char[] { 'L', 'J'};
            case "down,J":
                return new char[] { '7', 'F' };
            case "left,J":
                return new char[] { 'J', '7' };
            case "right,J":
                return new char[] { 'L', 'F' };
            //the 's' start
            case "up,s":
                return new char[] {};
            case "down,s":
                return new char[] {};
            case "right,s":
                return new char[] {};
            case "left,s":
                return new char[] {};
            //the 'g' end
            case "up,g":
                return new char[] { 'g' };
            case "down,g":
                return new char[] { 'g' };
            case "left,g":
                return new char[] { 'g' };
            case "right,g":
                return new char[] { 'g' };
            default:
                return null;
        }
    }
}
    @Override
    public State<PipeGameGrid> getInitialState() {
        return initialState;
    }
    @Override
    public boolean isGoalState(State<PipeGameGrid> state) {
        return state.equals(goalState);
    }
    public void setGoalState(State<PipeGameGrid> goal) {
        this.goalState = goal;
    }
    public void setInitialState(State<PipeGameGrid> initialState) {
        this.initialState = initialState;
    }
    public PipeGameSearchable(Board<Character> board) {
        this.board = board;
    }
    public boolean ifCameFrom(Integer X, Integer Y, State<PipeGameGrid> cameFrom) {
        if(cameFrom != null && cameFrom.getState() != null) {
            PipeGameGrid cameFromState = cameFrom.getState();
            if(cameFromState.getX() == X && cameFromState.getY() == Y)
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public ArrayList<State<PipeGameGrid>> getAllPossibleStates(State<PipeGameGrid> s) {
        possibleStates = new ArrayList();
        Integer x = s.getState().getX();
        Integer y = s.getState().getY();
        char sourceChar = s.getState().getValue();
        String[] allowedDirections = toDirection(Character.toString(sourceChar));
        boolean canGoUp = false, canGoDown = false, canGoLeft = false, canGoRight = false;
        for (int i = 0; i < allowedDirections.length; i++) {
            if (allowedDirections[i] == "left") {
                canGoLeft = true;
            }
            if (allowedDirections[i] == "right") {
                canGoRight = true;
            }
            if (allowedDirections[i] == "up") {
                canGoUp = true;
            }
            if (allowedDirections[i] == "down") {
                canGoDown = true;
            }
        }

        if (x >= 0 && x <  board.getW() - 1 && canGoLeft) {
            if (board.getHieghtWidth(x + 1, y) != ' ' && !ifCameFrom(x+1, y, s.getCameFrom())) {
                char targetChar = board.getHieghtWidth(x + 1, y);
                char[] changeTo = TheOptionsOfRotate("left," + targetChar);

                for (int i = 0; i < changeTo.length; i++) {
                    Integer cost = costOfRotate(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
                    possibleStates.add(getState(x + 1, y, changeTo[i], cost));
                }
            }
        }
        if (x != 0 && x <= board.getW() - 1 && canGoRight) {
            if (board.getHieghtWidth(x-1, y)!= ' ' && !ifCameFrom(x-1, y, s.getCameFrom())) {
                char targetChar = board.getHieghtWidth(x-1, y);
                char[] changeTo = TheOptionsOfRotate("right," + targetChar);

                for (int i = 0; i < changeTo.length; i++) {
                    Integer cost = costOfRotate(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
                    possibleStates.add(getState(x - 1, y, changeTo[i], cost));
                }
            }
        }
        if (y >= 0 && y < board.getH() - 1 && canGoUp) {
            if (board.getHieghtWidth(x, y+1) != ' ' && !ifCameFrom(x, y + 1, s.getCameFrom())) {
                char targetChar = board.getHieghtWidth(x, y + 1);
                char[] changeTo = TheOptionsOfRotate("up," + targetChar);
                for (int i = 0; i < changeTo.length; i++) {
                    Integer cost = costOfRotate(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
                    possibleStates.add(getState(x, y + 1, changeTo[i], cost));
                }
            }
        }
        if (y != 0 && y <= board.getH() - 1 && canGoDown) {
            if (board.getHieghtWidth(x, y-1) != ' ' && !ifCameFrom(x, y - 1, s.getCameFrom())) {
                char targetChar = board.getHieghtWidth(x, y-1);
                char[] changeTo = TheOptionsOfRotate("down," + targetChar);
                for (int i = 0; i < changeTo.length; i++) {
                    Integer cost = costOfRotate(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
                    possibleStates.add(getState(x, y - 1, changeTo[i], cost));
                }
            }
        }
        return possibleStates;
    }

    public State<PipeGameGrid> getState(Integer x, Integer y, char value, Integer cost) {
        String key = y.toString() + "," + x.toString() + " , " + Character.toString(value);
        if (cost == null) {
            cost = 0; }
        if (states.containsKey(key)) {
            return states.get(key);
        } else {
            PipeGameGrid st = new PipeGameGrid(x, y, value, cost);
            State<PipeGameGrid> state1 = new State<PipeGameGrid>();
            state1.setState(st);
            state1.setCost(cost);
            states.put(key, state1);
            return state1;
        }
    }
}
