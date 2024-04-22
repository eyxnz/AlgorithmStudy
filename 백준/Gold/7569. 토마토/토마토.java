import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int[][][] arr;
	static boolean[][][] visited; // 방문 여부
	static Queue<int[]> tomato; // 익은 토마토
	static int one; // 익지 않은 토마초 개수
	
	static int answer;
	static int[][] dir = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, 0, 1}, {0, -1, 0}, {0, 0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M];
		visited = new boolean[H][N][M];
		tomato = new LinkedList<>();
		
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int m = 0; m < M; m++) {
					int num = Integer.parseInt(st.nextToken());
					arr[h][n][m] = num;
					
					if(num == 1) {
						tomato.offer(new int[] {h, n, m, 0});
						visited[h][n][m] = true;
					} else if(num == 0) {
						one++;
					}
				}
			}
		}
		
		if(one > 0) { // 익지 않은 토마토가 있는 경우에만 탐색 시작
			answer = bfs();
		}
		
		System.out.println(answer);
	}

	private static int bfs() {
		int max = 0;
		
		while(!tomato.isEmpty()) {
			int[] now = tomato.poll();
			int h = now[0], n = now[1], m = now[2], day = now[3];
			max = max > day ? max : day;
			
			for(int d = 0; d < dir.length; d++) {
				int nh = h + dir[d][0], nn = n + dir[d][1], nm = m + dir[d][2];
				
				if(nh < 0 || nh >= H || nn < 0 || nn >= N || nm < 0 || nm >= M) { // 범위 밖
					continue;
				}
				if(arr[nh][nn][nm] == -1) {
					continue;
				}
				if(visited[nh][nn][nm]) {
					continue;
				}
				
				tomato.offer(new int[] {nh, nn, nm, day + 1});
				visited[nh][nn][nm] = true;
				one--;
			}
		}
		
		if(one == 0) {
			return max;
		}
		return -1;
	}
}
