import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int I;
	static int sx, sy, ex, ey;
	
	static int[][] dir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			I = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(sx, sy, ex, ey)).append("\n");
		}
		
		System.out.print(sb.toString());
	}

	private static int bfs(int sx, int sy, int ex, int ey) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[I][I];
		
		queue.offer(new int[] {sx, sy, 0});
		visited[sx][sy] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], z = now[2];
			
			if(x == ex && y == ey) {
				return z;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= I || ny < 0 || ny >= I) {
					continue;
				}
				
				if(visited[nx][ny]) {
					continue;
				}
				
				queue.offer(new int[] {nx, ny, z + 1});
				visited[nx][ny] = true;
			}
		}
		
		return -1;
	}
}
