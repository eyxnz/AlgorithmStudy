import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int idx = 0; idx < commands.length; idx++) {
            int i = commands[idx][0], j = commands[idx][1], k = commands[idx][2];
            
            int[] newArray = new int[j - i + 1];
            for(int cut = 0; cut < j - i + 1; cut++) {
                newArray[cut] = array[i - 1 + cut];
            }
            
            Arrays.sort(newArray);
            
            answer[idx] = newArray[k - 1];
        }
        
        return answer;
    }
}