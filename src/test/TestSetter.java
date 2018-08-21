package test;

import java.util.ArrayList;
import java.util.List;

// edit these imports according to your project
import algorithm.*;
import maze.MazeSearchable;
import server.CacheManager;
import server.ClientHandler;
import server.MyCacheManager;
import server.MyClientHandler;
import server.MyServer;
import server.MySolver;
import server.Server;
import server.Solver;

public class TestSetter {
	
	public static void setClasses(DesignTest dt){
		
		// set the server's Interface, e.g., "Server.class"
		// don't forget to import the correct package e.g., "import server.Server"
		dt.setServerInteface(Server.class);
		// now fill in the other types according to their names
		// server's implementation
		dt.setServerClass(MyServer.class);
		// client handler interface
		dt.setClientHandlerInterface(ClientHandler.class);
		// client handler class
		dt.setClientHandlerClass(MyClientHandler.class);
		// cache manager interface
		dt.setCacheManagerInterface(CacheManager.class);
		// cache manager class
		dt.setCacheManagerClass(MyCacheManager.class);
		// solver interface
		dt.setSolverInterface(Solver.class);
		// solver class
		dt.setSolverClass(MySolver.class);
		// searchable interface
		dt.setSearchableInterface(Searchable.class);
		// searcher interface
		dt.setSearcherInterface(Searcher.class);
		// your searchable pipe game class
		dt.setPipeGameClass(PipeGameSearchable.class);
		// your Best First Search implementation
		dt.setBestFSClass(BestFS.class);
	}
	
	// run your server here
	static Server s;
	public static void runServer(int port){
		s=new MyServer(port);
		s.start(new MyClientHandler());
	}
	// stop your server here
	public static void stopServer(){
		s.stop();
	}
	
	/* ------------- Best First Search Test --------------
	 * You are given a Maze
	 * Create a new Searchable from the Maze
	 * Solve the Maze
	 * and return a list of moves from {UP,DOWN,RIGHT,LEFT}
	 *  
	 */

	public static List<String> solveMaze(Maze m) {

		// Searchable implementation:
		Searchable<Grid> mazeSearchable = new Searchable<Grid>() {

			@Override
			public State<Grid> getInitialState() {
				return new State<Grid>(m.getEntrance());
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
		};


		// Searcher:
		Searcher<Grid> searcher = new BestFS<Grid>();
		Solution<Grid> solution = searcher.search(mazeSearchable);


		// From solution to instructions
		List<String> result = new ArrayList<>();

		for(int i = 0; i < solution.size()-1; i++) {
			if (solution.get(i).col < solution.get(i + 1).col)
				result.add("RIGHT");
			else if (solution.get(i).col > solution.get(i + 1).col)
				result.add("LEFT");
			else if (solution.get(i).row < solution.get(i + 1).row)
				result.add("DOWN");
			else if (solution.get(i).row > solution.get(i + 1).row)
				result.add("UP");
		}

		return result;
	}
}
