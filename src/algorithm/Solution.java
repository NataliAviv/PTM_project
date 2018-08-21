package algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Solution<T> extends ArrayList<T>{

    public ArrayList<T> getSolution() {
        return this;
    }
    public String toString() {
        String str = "[";
        for(int i = 0; i < this.size(); i++) {
            str += this.get(i).toString();
        }
        str += "]";
        return str;
    }
    public void setSolution(ArrayList<T> solution) {
        Collections.reverse(solution);
        this.addAll(solution);
    }

}
