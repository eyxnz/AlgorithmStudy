import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] W;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new long[N][1 << N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1); // 방문하지 않음 표시
		}
		
		System.out.println(tsp(0, 1)); // 0번 도시에서 출발
	}

	private static long tsp(int cur, int visited) {
		if(visited == (1 << N) - 1) { // 모든 도시 방문 -> 출발 지점으로 되돌아갈 수 있나?
			return W[cur][0] > 0 ? W[cur][0] : Integer.MAX_VALUE;
		}
		
		if(dp[cur][visited] != -1) { // 이미 방문한 적이 있다면
			return dp[cur][visited];
		}
		
		dp[cur][visited] = Integer.MAX_VALUE; // 방문 처리
		
		for(int i = 0; i < N; i++) { // cur -> i
			if(W[cur][i] == 0) { // 경로 없음
				continue;
			}
			if((visited & (1 << i)) != 0) { // 이미 방문한 적 있는 도시
				continue;
			}
			
			dp[cur][visited] = Math.min(dp[cur][visited], tsp(i, visited | (1 << i)) + W[cur][i]);
		}
		
		return dp[cur][visited];
	}
}