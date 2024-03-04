import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int W, H;
	static int[][] arr;
	
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
		
		System.out.println(bfs(0, 0));
	}

	private static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		int[][][] visited = new int[H][W][K + 1];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				Arrays.fill(visited[i][j], -1);
			}
		}
		
		queue.offer(new int[] {x, y, 0});
		visited[x][y][0] = 0;
		
		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			x = info[0];
			y = info[1];
			int z = info[2];
			
			if(x == H - 1 && y == W - 1) {
				return visited[x][y][z];
			}
			
			if(z < K) { // 말의 움직임으로 이동 가능
				for(int d = 0; d < horse.length; d++) {
					int nx = x + horse[d][0], ny = y + horse[d][1];
					
					if(!checkArea(nx, ny)) {
						continue;
					}
					if(visited[nx][ny][z + 1] != -1) {
						continue;
					}
					if(arr[nx][ny] != 0) {
						continue;
					}
					
					queue.offer(new int[] {nx, ny, z + 1});
					visited[nx][ny][z + 1] = visited[x][y][z] + 1;
				}
			}
			
			for(int d = 0; d < monkey.length; d++) {
				int nx = x + monkey[d][0], ny = y + monkey[d][1];
					
				if(!checkArea(nx, ny)) {
					continue;
				}
				if(visited[nx][ny][z] != -1) {
					continue;
				}
				if(arr[nx][ny] != 0) {
					continue;
				}
					
				queue.offer(new int[] {nx, ny, z});
				visited[nx][ny][z] = visited[x][y][z] + 1;
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
