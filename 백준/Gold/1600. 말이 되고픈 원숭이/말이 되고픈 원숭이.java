import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int W; // 가로
	static int H; // 세로
	static int[][] arr; // 격자판 (0 : 아무것도 없는 평지, 1 : 장애물)
	
	static int[][] horse = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
	static int[][] monkey = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
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
		
		System.out.println(bfs(0, 0)); // 원숭이의 동작수의 최솟값
	}

	private static int bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		int[][][] visited = new int[H][W][K + 1];
		for(int h = 0; h < H; h++) {
			for(int w = 0; w < W; w++) {
				Arrays.fill(visited[h][w], -1);
			}
		}
		
		queue.offer(new int[] {i, j, 0});
		visited[i][j][0] = 0;
		
		while(!queue.isEmpty()) {
			int[] q = queue.poll();
			int x = q[0], y = q[1], k = q[2];
			
			if(x == H - 1 && y == W - 1) {
				return visited[x][y][k];
			}
			
			if(k < K) { // 말의 이동방법으로 갈 수 있는 횟수가 남았다면
				for(int d = 0; d < 8; d++) {
					int nx = x + horse[d][0], ny = y + horse[d][1];
					
					if(!checkArea(nx, ny)) { // 범위 밖
						continue;
					}
					if(arr[nx][ny] == 1) { // 장애물
						continue;
					}
					if(visited[nx][ny][k + 1] != -1) { // 이미 방문
						continue;
					}
					
					queue.offer(new int[] {nx, ny, k + 1});
					visited[nx][ny][k + 1] = visited[x][y][k] + 1;
				}
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = x + monkey[d][0], ny = y + monkey[d][1];
				
				if(!checkArea(nx, ny)) { // 범위 밖
					continue;
				}
				if(arr[nx][ny] == 1) { // 장애물
					continue;
				}
				if(visited[nx][ny][k] != -1) { // 이미 방문
					continue;
				}
				
				queue.offer(new int[] {nx, ny, k});
				visited[nx][ny][k] = visited[x][y][k] + 1;
			}
		}
		
		return -1;
	}

	private static boolean checkArea(int x, int y) {
		if(x < 0 || x >= H || y < 0 || y >= W) {
			return false;
		}
		return true;
	}
}