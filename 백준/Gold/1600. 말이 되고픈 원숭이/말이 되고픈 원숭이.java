import java.io.*;
import java.util.*;

public class Main {
	static int K, W, H;
	static int[][] arr;
	
	static int[][] monkey = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] horse = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[H][W][K + 1];
		
		queue.offer(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], k = now[2], cnt = now[3];
			
			if(x == H - 1 && y == W - 1) {
				return cnt;
			}
			
			if(k < K) { // 말의 움직임 가능
				for(int d = 0; d < horse.length; d++) {
					int nx = x + horse[d][0], ny = y + horse[d][1];
					
					if(nx < 0 || nx >= H || ny < 0 || ny >= W) { // 범위 밖
						continue;
					}
					if(arr[nx][ny] == 1) { // 장애물
						continue;
					}
					if(visited[nx][ny][k + 1]) {
						continue;
					}
					
					queue.offer(new int[] {nx, ny, k + 1, cnt + 1});
					visited[nx][ny][k + 1] = true;
				}
			}
			
			for(int d = 0; d < monkey.length; d++) {
				int nx = x + monkey[d][0], ny = y + monkey[d][1];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) { // 범위 밖
					continue;
				}
				if(arr[nx][ny] == 1) { // 장애물
					continue;
				}
				if(visited[nx][ny][k]) {
					continue;
				}
				
				queue.offer(new int[] {nx, ny, k, cnt + 1});
				visited[nx][ny][k] = true;
			}
		}
		
		return -1;
	}
}