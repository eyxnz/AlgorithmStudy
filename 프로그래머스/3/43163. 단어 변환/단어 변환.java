import java.io.*;
import java.util.*;

class Info {
    String word;
    int count;
    
    public Info(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    int bfs(String begin, String target, String[] words) {
        Queue<Info> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        
        queue.offer(new Info(begin, 0));
        
        while(!queue.isEmpty()) {
            Info info = queue.poll();
            String word = info.word;
            int count = info.count;
            
            if(word.equals(target)) {
                return count;
            }
            
            for(int w = 0; w < words.length; w++) {
                String nextWord = words[w];
                
                if(!canChange(word, nextWord)) {
                    continue;
                }
                
                if(visited[w]) {
                    continue;
                }
                
                queue.offer(new Info(nextWord, count + 1));
                visited[w] = true;
            }
        }
        
        return 0; 
    }
    
    boolean canChange(String x, String y) {
        int count = 0;
        
        for(int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == y.charAt(i)) {
                continue;
            }
            
            count++;
            
            if(count > 1) {
                return false;
            }
        }
        
        return true;
    }
}