import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	
	static int count;
	static int maxArea;
	
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	arr = new int[N][M];
    	visited = new boolean[N][M];
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		
    		for(int j = 0; j < M; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(arr[i][j] == 0) {
    				continue;
    			}
    			
    			if(visited[i][j]) {
    				continue;
    			}
    			
    			bfs(i, j);
    		}
    	}
    	
    	System.out.println(count);
    	System.out.println(maxArea);
    }

	private static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		int area = 0;
		
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		area++;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}
				
				if(arr[nx][ny] == 0) {
					continue;
				}
				
				if(visited[nx][ny]) {
					continue;
				}
				
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
				area++;
			}
		}
		
		count++;
		maxArea = maxArea > area ? maxArea : area;
	}
}
