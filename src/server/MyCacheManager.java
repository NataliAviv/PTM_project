package server;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyCacheManager implements CacheManager {
    private static HashMap<Integer,ArrayList<String>> cache;
    //private static FileClientHandler f;
    public MyCacheManager()
    {
        cache = new HashMap<>();
    }
    /*public static FileClientHandler getInstance() {
        if(f == null)
        {
            f = new FileClientHandler();
            cache =new HashMap<Integer ,ArrayList<String>>();
        }
        return f;
    }
    */
    public void save(Integer key,ArrayList<String> solution) {
        cache.put(key,solution);
        File filesolution = new File(key+".txt");
        try {
            PrintWriter write = new PrintWriter(filesolution);
            for(int i =0;i<solution.size();i++)
            {
                write.println(solution.get(i));

            }
            write.print("done");
            write.flush();
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> load(Integer key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        File filesolution = new File(key+".txt");
        if(filesolution.exists())
        {
            BufferedReader buff = null;
            try {
                buff = new BufferedReader(new FileReader(filesolution));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String streader = null;
            ArrayList<String> solution = new ArrayList<>();
            try {
                streader = buff.readLine();
                while (!(streader.equals("done")))
                {
                    solution.add(streader);
                    streader = buff.readLine();
                }
                solution.add("done");
                return  solution;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
