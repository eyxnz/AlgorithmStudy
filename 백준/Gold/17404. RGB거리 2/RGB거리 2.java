import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] cost;
	static int[][] dp;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		dp = new int[N][3];
		answer = Integer.MAX_VALUE;
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int c = 0; c < 3; c++) {
				cost[n][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 0; k < 3; k++) { // 첫 번째 집을 k색으로 칠한 경우
			for(int c = 0; c < 3; c++) {
				if(c == k) {
					dp[0][c] = cost[0][c];
					continue;
				}
				
				dp[0][c] = Integer.MAX_VALUE;
			}
			
			for(int n = 1; n < N; n++) {
				dp[n][0] = Math.min(dp[n - 1][1], dp[n - 1][2]);
				if(dp[n][0] != Integer.MAX_VALUE) {
					dp[n][0] += cost[n][0];
				}
				
				dp[n][1] = Math.min(dp[n - 1][0], dp[n - 1][2]);
				if(dp[n][1] != Integer.MAX_VALUE) {
					dp[n][1] += cost[n][1];
				}
				
				dp[n][2] = Math.min(dp[n - 1][0], dp[n - 1][1]);
				if(dp[n][2] != Integer.MAX_VALUE) {
					dp[n][2] += cost[n][2];
				}
			}
			
			for(int c = 0; c < 3; c++) {
				if(c == k) {
					continue;
				}
				
				answer = Math.min(answer, dp[N - 1][c]);
			}
		}

		System.out.println(answer);
	}
}
