import java.io.*;
import java.util.*;

class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                continue;
            }
            
            answer++;
            bfs(i, n, computers);
        }
        
        return answer;
    }
    
    void bfs(int x, int n, int[][] computers) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(x);
        visited[x] = true;
        
        while(!queue.isEmpty()) {
            x = queue.poll();
            
            for(int y = 0; y < n; y++) {
                if(computers[x][y] == 0) { // 연결 X
                    continue;
                }
                
                if(visited[y]) { // 이미 체크
                    continue;
                }
                
                queue.offer(y);
                visited[y] = true;
            }
        }
    }
}