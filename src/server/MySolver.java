package server;
import algorithm.*;

import java.util.ArrayList;

public class MySolver implements Solver {
    ArrayList<State<PipeGameGrid>> boardState = new ArrayList<State<PipeGameGrid>>();
    public String solve(String game) {
        String[] list = game.split("\n");
        char[][] board = new char[list.length][];
        State<PipeGameGrid> startPoint = null;
        State<PipeGameGrid> endPoint = null;
        for (int y = 0; y < list.length; y++) {
            for (int x = 0; x < list[y].length(); x++) {
                State<PipeGameGrid> currentState = new State<PipeGameGrid>();
                PipeGameGrid pipelineState = new PipeGameGrid(x, y, list[y].charAt(x), 0);
                currentState.setState(pipelineState);
                boardState.add(currentState);
                if (board[y] == null) {
                    board[y] = new char[list[y].length()];
                }
                board[y][x] = list[y].charAt(x);
                if (list[y].charAt(x) == 's') {
                    startPoint = currentState;
                } else if (list[y].charAt(x) == 'g') {
                    endPoint = currentState;
                }
            }
        }

        PipeGameSearchable searchable = new PipeGameSearchable(new PipeGameBoard(board));
        searchable.setInitialState(startPoint);
        searchable.setGoalState(endPoint);


        // choose whiche algorithm is the best:
        Searcher<PipeGameGrid> searcher;

        // if..else...
        searcher = new BestFS<>();
        //searcher = new BFS<>();
        //searcher = new DFS<>();
        //searcher = new HillClimbing<>();


        Solution<PipeGameGrid> solution = searcher.search(searchable);


        if (solution != null) {
            String moves = "";
            for (int i = 1; i < solution.getSolution().size() - 1; i++) {
                PipeGameGrid currentState = solution.getSolution().get(i);
                moves += currentState.getY() + "," + currentState.getX() + "," + String.valueOf(currentState.getRotations());
                if (i < solution.getSolution().size()-2) {
                    moves += "\n";
                }
            }
            return moves;
        } else {
            return "Can't solve!";
        }
    }
    //main train private
    public static void main(String[] args) {
        MySolver mySolver = new MySolver();
        String level =  "s|g";

        System.out.println(mySolver.solve(level));

    }
}
