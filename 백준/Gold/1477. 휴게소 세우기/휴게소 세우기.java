import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] point;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		point = new int[N + 2];
		point[0] = 0;
		point[1] = L;
		
		if(N > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 2; i < N + 2; i++) {
				point[i] = Integer.parseInt(st.nextToken());
			}
		}
		Arrays.sort(point);
		
		int left = 1, right = L - 1; // 휴게소가 없는 구간의 최솟값과 최댓값
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(check(mid)) {
				right = mid - 1;
				answer = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(answer);
	}

	private static boolean check(int len) {
		int cnt = 0; // 지을 수 있는 휴게소의 개수
		
		for(int i = 1; i < point.length; i++) {
			cnt += (point[i] - point[i - 1] - 1) / len;
		}
		
		return cnt <= M;
	}
}