import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] box;
	static Queue<int[]> queue;
	static int[][] visited;
	
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	static int answer;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	M = Integer.parseInt(st.nextToken()); // 가로
    	N = Integer.parseInt(st.nextToken()); // 세로
    	box = new int[N][M];
    	queue = new LinkedList<>();
    	visited = new int[N][M];
    	for(int i = 0; i < N; i++) {
    		Arrays.fill(visited[i], -1);
    	}
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		
    		for(int j = 0; j < M; j++) {
    			box[i][j] = Integer.parseInt(st.nextToken());
    			
    			if(box[i][j] == 1) {
    				queue.offer(new int[] {i, j});
    				visited[i][j] = 0;
    			}
    		}
    	}
    	
    	while(!queue.isEmpty()) {
    		int[] now = queue.poll();
    		int x = now[0], y = now[1];
    		
    		for(int d = 0; d < dir.length; d++) {
    			int nx = x + dir[d][0], ny = y + dir[d][1];
    			
    			if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
    				continue;
    			}
    			
    			if(box[nx][ny] != 0) {
    				continue;
    			}
    			
    			if(visited[nx][ny] != -1) {
    				continue;
    			}
    			
    			queue.offer(new int[] {nx, ny});
    			visited[nx][ny] = visited[x][y] + 1;
    		}
    	}
    	
    	find: for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(box[i][j] != 0) {
    				continue;
    			}
    			
    			if(visited[i][j] == -1) {
    				answer = -1;
    				break find;
    			}
    			
    			answer = answer > visited[i][j] ? answer : visited[i][j];
    		}
    	}
    	
    	System.out.println(answer);
    }
}
