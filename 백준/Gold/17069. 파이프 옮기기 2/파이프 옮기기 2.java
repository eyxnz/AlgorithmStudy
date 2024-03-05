import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static long[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new long[N][N][3];
		dp[0][1][0] = 1; // 초기 위치
		
		for(int i = 0; i < N; i++) {
			for(int j = 2; j < N; j++) {
				if(arr[i][j] != 0) {
					continue;
				}
				
				// 가로
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				
				// 세로
				if(i - 1 < 0) {
					continue;
				}
				
				dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
				
				// 대각선
				if(arr[i][j - 1] != 0 || arr[i - 1][j] != 0) {
					continue;
				}
				
				dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}
		
		long answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(answer);
	}
}
