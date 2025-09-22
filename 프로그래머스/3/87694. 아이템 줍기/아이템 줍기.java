import java.io.*;
import java.util.*;

class Solution {
    static int MAX = 50 * 2;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[MAX + 1][MAX + 1];
        
        // 사각형 내부 0, 테두리 1로 설정
        for(int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2, y1 = rectangle[i][1] * 2, x2 = rectangle[i][2] * 2, y2 = rectangle[i][3] * 2;
            
            for(int x = x1; x < x2 + 1; x++) {
                for(int y = y1; y < y2 + 1; y++) {
                    if(x > x1 && x < x2 && y > y1 && y < y2) { // 내부
                        map[x][y] = 2;
                    } else { // 외부
                        if(map[x][y] != 2) { // 다른 사각형의 내부가 아님
                            map[x][y] = 1;
                        }
                    }
                }
            }
        }
        
        return find(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
    
    int find(int characterX, int characterY, int itemX, int itemY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[MAX + 1][MAX + 1];
        
        queue.offer(new int[] {characterX, characterY, 0});
        visited[characterX][characterY] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], z = now[2];
            
            if(x == itemX && y == itemY) {
                return z / 2;
            }
            
            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1], nz = z + 1;
                
                if(nx <= 0 || nx > MAX || ny <= 0 || ny > MAX) {
                    continue;
                }
                
                if(map[nx][ny] != 1) {
                    continue;
                }
                
                if(visited[nx][ny]) {
                    continue;
                }
                
                queue.offer(new int[] {nx, ny, nz});
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
}