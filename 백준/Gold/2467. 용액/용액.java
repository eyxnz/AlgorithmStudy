import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] value;
	static int[] answer; // 용액1, 용액2
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		value = new int[N];
		answer = new int[] {1000000000, 1000000000};
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = N - 1;
		while(left < right) {
			int sum = value[left] + value[right];
			if(Math.abs(sum) < Math.abs(answer[0] + answer[1])) {
				answer[0] = value[left];
				answer[1] = value[right];
			}
			
			if(sum == 0) {
				break;
			}else if(sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}
}