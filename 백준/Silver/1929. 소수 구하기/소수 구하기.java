import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M, N; // (1 ≤ M ≤ N ≤ 1,000,000)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		for(int num = M; num < N + 1; num++) {
			if(!isPrime(num)) {
				continue;
			}
			
			sb.append(num).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static boolean isPrime(int num) {
		if(num == 1) {
			return false;
		}
		if(num == 2) {
			return true;
		}
		
		for(int i = 2; i < Math.pow(num, 0.5) + 1; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
}