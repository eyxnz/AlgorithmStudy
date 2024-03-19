import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K, N;
	static int[] lans;
	static long answer = 1; // 랜선의 최대 길이
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		lans = new int[K];
		for(int i = 0; i < K; i++) {
			lans[i] = Integer.parseInt(br.readLine());
		}
		
		long left = 1, right = Integer.MAX_VALUE;
		while(left <= right) {
			long mid = (left + right) / 2;
			
			if(check(mid)) { // 해당 길이로 자를 수 있음
				answer = mid;
				left = mid + 1; // 더 길게 자르기
			} else {
				right = mid - 1; // 더 짧게 자르기
			}
		}
		
		System.out.println(answer);
	}

	private static boolean check(long len) {
		long cnt = 0;
		for(int i = 0; i < K; i++) {
			cnt += lans[i] / len;
		}
		
		return cnt >= N ? true : false;
	}
}