import java.io.*;
import java.util.*;

public class Main {
	static int T, H, W, O, F, SX, SY, EX, EY;
	static int[][] maze;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	T = Integer.parseInt(br.readLine());
    	for(int t = 0; t < T; t++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		H = Integer.parseInt(st.nextToken());
    		W = Integer.parseInt(st.nextToken());
    		O = Integer.parseInt(st.nextToken());
    		F = Integer.parseInt(st.nextToken());
    		SX = Integer.parseInt(st.nextToken()) - 1;
    		SY = Integer.parseInt(st.nextToken()) - 1;
    		EX = Integer.parseInt(st.nextToken()) - 1;
    		EY = Integer.parseInt(st.nextToken()) - 1;
    		maze = new int[H][W];
    		
    		for(int i = 0; i < O; i++) {
    			st = new StringTokenizer(br.readLine(), " ");
    			int x = Integer.parseInt(st.nextToken()) - 1;
    			int y = Integer.parseInt(st.nextToken()) - 1;
    			int l = Integer.parseInt(st.nextToken());
    			
    			maze[x][y] = l;
    		}
    		
    		if(bfs()) {
    			sb.append("잘했어!!").append("\n");
    		} else {
    			sb.append("인성 문제있어??").append("\n");
    		}
    	}
    	
    	System.out.print(sb.toString());
    }

	private static boolean bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		
		queue.offer(new int[] {SX, SY, F});
		visited[SX][SY] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], f = now[2];
			
			if(x == EX && y == EY) {
				return true;
			}
			
			if(f == 0) {
				continue;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) {
					continue;
				}
				
				if(visited[nx][ny]) {
					continue;
				}
				
				if(maze[nx][ny] - maze[x][y] > f) {
					continue;
				}
				
				queue.offer(new int[] {nx, ny, f - 1});
				visited[nx][ny] = true;
			}
		}
		
		return false;
	}
}
