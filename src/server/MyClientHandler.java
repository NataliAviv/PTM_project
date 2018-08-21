package server;
import java.io.*;
import java.util.ArrayList;
public class MyClientHandler implements ClientHandler {

    Solver solver;
    CacheManager solutionfile;

    public MyClientHandler() {
        this.solver = new MySolver();
        this.solutionfile = new MyCacheManager();
    }

    public void handle(InputStream in, OutputStream out) {
        BufferedReader buff = new BufferedReader(new InputStreamReader((in)));
        PrintWriter solution = new PrintWriter(out);
        Integer key;
        ArrayList<String> gameBoard = new ArrayList<>();
        String line = "";
        try {
            gameBoard.add(buff.readLine());
            while (!(line.equals("done"))) {
                line = buff.readLine();
                gameBoard.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        key = gameBoard.toString().hashCode();
        ArrayList<String> sol;
        if(solutionfile.load(key)==null)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < gameBoard.size()-1; i++) {
                stringBuilder.append(gameBoard.get(i)+"\n");
            }
            String[] result = solver.solve(stringBuilder.toString()).split("\n");
            ArrayList<String> resultArr = new ArrayList<>();
            for (int i = 0; i < result.length; i++) {
                resultArr.add(result[i]);
            }
            solutionfile.save(key,resultArr);
        }
        sol = solutionfile.load(key);
        for (int i =0;i<sol.size();i++)
        {
            solution.write(sol.get(i)+"\n");
        }
        solution.write("done");
        solution.flush();
        solution.close();
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}