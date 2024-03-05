import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int MAX = 30;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		dp = new int[MAX][MAX];
		for(int i = 1; i < MAX; i++) { // 첫 번째 왼쪽 사이트는 모든 오른쪽 사이트로 갈 수 있음
			dp[1][i] = 1;
		}
		
		for(int i = 2; i < MAX; i++) { // 왼쪽 사이트
			for(int j = i; j < MAX; j++) { // 오른쪽 사이트
				for(int k = 0; k < j; k++) {
					dp[i][j] += dp[i - 1][k];
				}
			}
		}
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int total = 0;
			for(int i = 1; i < M + 1; i++) {
				total += dp[N][i];
			}
			sb.append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
}