package test;

//import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainTrain {

	public static void main(String[] args) {
		//----------- Question 1 --------------
		// design test (40 points)
		DesignTest dt=new DesignTest();
		TestSetter.setClasses(dt);
		dt.testDesign();
		
		//----------- Question 2 --------------
		// execution test (40 points)
		Random r=new Random();
		int port=6000+r.nextInt(1000);
		TestSetter.runServer(port);
		try{
			TestServer.runClient(port);
		}finally{
			TestSetter.stopServer();
		}
		
		//----------- Question 3 --------------		
		// test Best first Search (20 points)
		byte[][] mazeData={
				{1,1,1,1,1},	
				{2,0,0,0,1},	
				{1,1,1,0,1},	
				{1,0,0,0,1},	
				{1,0,1,0,1},	
				{1,3,1,1,1},	
		};


		Maze m=new Maze(mazeData);
		List<String> actions = TestSetter.solveMaze(m);

		System.out.println(actions.toString());

		// the following is the solution for the maze above:
		// List<String> answer = Arrays.asList("RIGHT","RIGHT","RIGHT","DOWN","DOWN","LEFT","LEFT","DOWN","DOWN");
		//actions=answer;

		final Grid p=m.getEntrance();
		actions.forEach(s->{
			if(s.equals("UP")) p.row--;
			if(s.equals("DOWN")) p.row++;
			if(s.equals("RIGHT")) p.col++;
			if(s.equals("LEFT")) p.col--;
		});

		if(!p.equals(m.getExit()))
			System.out.println("the Maze is not solved (-20)");


		System.out.println("done");
	}

}
