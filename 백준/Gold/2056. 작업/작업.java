import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] dp;
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) { // 작업
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			dp[i] = time;
			
			int cnt = Integer.parseInt(st.nextToken()); // 선행 관계에 있는 작업들의 개수
			for(int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken()); // 선행 관계에 있는 작업들의 번호
				dp[i] = Math.max(dp[i], dp[num] + time);
			}
			
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);
	}
}
