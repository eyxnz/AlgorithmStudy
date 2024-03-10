import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long A, B;
	static int[] isPrime;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		int size = (int)Math.sqrt(B); // 가능한 소수 범위
		isPrime = new int[size + 1]; // 0 ~ size
		for(int i = 2; i < size + 1; i++) {
			isPrime[i] = i;
		}
		for(int i = 2; i < Math.sqrt(size) + 1; i++) {
			if(isPrime[i] == 0) {
				continue;
			}
			
			for(int j = i + i; j < size + 1; j += i) { // 배수 삭제
				isPrime[j] = 0;
			}
		}
		
		for(int num = 2; num < size + 1; num++) { // 가능한 소수 범위
			if(isPrime[num] == 0) {
				continue;
			}
			
			long temp = num;
			while((double)num <= B / (double)temp) {
				if((double)num >= A / (double)temp) {
					answer++;
				}
				
				temp *= num;
			}
		}
		
		System.out.println(answer);
	}
}