import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[][] dp;
	static int div = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][2];
		
		dp[1][0] = 2;
		if(N > 1) {
			dp[2][0] = 7;
			dp[2][1] = 1;
		}
		
		for(int n = 3; n < N + 1; n++) {
			dp[n][1] = (dp[n - 3][0] + dp[n - 1][1]) % div;
			dp[n][0] = (dp[n - 1][0] * 2 + dp[n - 2][0] * 3 + 2 * dp[n][1]) % div;
		}
		
		System.out.println(dp[N][0]);
	}
}