import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], cost = now[2];
            
            if(x == N - 1 && y == M - 1) {
                return cost;
            }
            
            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if(maps[nx][ny] == 0) {
                    continue;
                }
                if(visited[nx][ny]) {
                    continue;
                }
                
                queue.offer(new int[] {nx, ny, cost + 1});
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
}