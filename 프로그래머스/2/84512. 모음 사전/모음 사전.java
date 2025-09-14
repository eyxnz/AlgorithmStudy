import java.io.*;
import java.util.*;

class Solution {
    static char[] ch = {'A', 'E', 'I', 'O', 'U'};
    static List<String> list = new LinkedList<>();
    
    public int solution(String word) {
        for(int i = 1; i < ch.length + 1; i++) {
            permutation(i, 0, "");
        }
        
        Collections.sort(list);

        return list.indexOf(word) + 1;
    }
    
    void permutation(int size, int depth, String result) {
        if(depth == size) {
            list.add(result);
            return;
        }
        
        for(int i = 0; i < ch.length; i++) {
            permutation(size, depth + 1, result + ch[i]);
        }
    }
}