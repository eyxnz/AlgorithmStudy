import java.util.*;

class Solution {
    static boolean[] visited;
    static int answer;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                continue;
            }    
            
            bfs(i, n, computers);
            answer++;
        }
        
        return answer;
    }
    
    public void bfs(int start, int n, int[][] computers) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int x = queue.poll();
            
            for(int y = 0; y < n; y++) {
                if(computers[x][y] == 0) {
                    continue;
                }
                if(visited[y]) {
                    continue;
                }
                
                queue.offer(y);
                visited[y] = true;
            }
        }
    }
}