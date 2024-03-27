import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] people;
	static int[][] sum; // 누적합
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		people = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < M; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		sum = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sum[i][j] = people[i][j];
				
				if(i - 1 >= 0) {
					sum[i][j] += sum[i - 1][j];
				}
				if(j - 1 >= 0) {
					sum[i][j] += sum[i][j - 1];
				}
				
				if(i - 1 >= 0 && j - 1 >= 0) {
					sum[i][j] -= sum[i - 1][j - 1];
				}
			}
		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			
			int answer = sum[x2][y2];
			if(x1 - 1 >= 0) {
				answer -= sum[x1 - 1][y2];
			}
			if(y1 - 1 >= 0) {
				answer -= sum[x2][y1 - 1];
			}
			if(x1 - 1 >= 0 && y1 - 1 >= 0) {
				answer += sum[x1 - 1][y1 - 1];
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}