import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N; // 2 ≤ N ≤ 1,000
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][3];
		for(int i = 0; i < 3; i++) { // 첫 번째 집 칠하기
			dp[0][i] = cost[0][i];
		}
		
		for(int i = 1; i < N; i++) {
			// i 번째 집을 빨간색으로 칠할 경우
			dp[i][0] = dp[i - 1][1] > dp[i - 1][2] ? dp[i - 1][2] : dp[i - 1][1]; // 둘 중 최소값 선택
			dp[i][0] += cost[i][0];
			
			// i 번째 집을 초록색으로 칠할 경우
			dp[i][1] = dp[i - 1][0] > dp[i - 1][2] ? dp[i - 1][2] : dp[i - 1][0]; // 둘 중 최소값 선택
			dp[i][1] += cost[i][1];
			
			// i 번째 집을 파란색으로 칠할 경우
			dp[i][2] = dp[i - 1][0] > dp[i - 1][1] ? dp[i - 1][1] : dp[i - 1][0]; // 둘 중 최소값 선택
			dp[i][2] += cost[i][2];
		}
		
		int answer = dp[N - 1][0] > dp[N - 1][1] ? dp[N - 1][1] : dp[N - 1][0];
		answer = answer > dp[N - 1][2] ? dp[N - 1][2] : answer;
		System.out.println(answer);
	}
}