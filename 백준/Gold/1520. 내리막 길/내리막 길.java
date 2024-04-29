import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] arr;
	static int[][] dp;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[M][N];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			
			for(int n = 0; n < N; n++) {
				arr[m][n] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[M][N];
		for(int m = 0; m < M; m++) {
			Arrays.fill(dp[m], Integer.MAX_VALUE);
		}
		dp[0][0] = 1;
		
		System.out.println(sol(M - 1, N - 1));
	}

	private static int sol(int x, int y) {
		if(dp[x][y] != Integer.MAX_VALUE) { // 이미 계산된 칸
			return dp[x][y];
		}
		
		dp[x][y] = 0; // 방문 표시
		for(int d = 0; d < dir.length; d++) {
			int nx = x + dir[d][0], ny = y + dir[d][1]; // (nx, ny) -> (x, y)
			
			if(nx < 0 || nx >= M || ny < 0 || ny >= N) { // 범위 밖
				continue;
			}
			if(arr[nx][ny] <= arr[x][y]) { // 내리막길이 아님
				continue;
			}
			
			dp[x][y] += sol(nx, ny);
		}
		
		return dp[x][y];
	}
}