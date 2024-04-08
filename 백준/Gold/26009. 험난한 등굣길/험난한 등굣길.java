import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static boolean[][] visited;
	static int answer;
	
	static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 상 하 우 좌
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		
		K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i <= d; i++) {
				if(r + i < N && c + (d - i) < M) {
					visited[r + i][c + (d - i)] = true;
				}
				
				if(r + i < N && c - (d - i) >= 0) {
					visited[r + i][c - (d - i)] = true;
				}
				
				if(r - i >= 0 && c + (d - i) < M) {
					visited[r - i][c + (d - i)] = true;
				}
				
				if(r - i >= 0 && c - (d - i) >= 0) {
					visited[r - i][c - (d - i)] = true;
				}
			}
		}
		
		answer = bfs();
		if(answer == -1) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			System.out.println(answer);
		}
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {0, 0, 0});
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], z = now[2];
			
			if(x == N - 1 && y == M - 1) {
				return z;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(!checkArea(nx, ny)) {
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

	private static boolean checkArea(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= M);
	}
}

