import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static PriorityQueue<Integer> minus; // 음수 저장 -> 최소 힙
	static PriorityQueue<Integer> plus; // 양수 저장 -> 최대 힙
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		minus = new PriorityQueue<>();
		plus = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num <= 0) {
				minus.offer(num);
			} else {
				plus.offer(num);
			}
		}
		
		while(!minus.isEmpty()) {
			int a = minus.poll();
			if(!minus.isEmpty()) {
				int b = minus.poll();
				answer += a * b;
			} else {
				answer += a;
			}
		}
		
		while(!plus.isEmpty()) {
			int a = plus.poll();
			if(!plus.isEmpty()) {
				int b = plus.poll();
				
				if(a == 1 || b == 1) { // a 가 1이라면 b도 1
					answer += a + b;
				} else {
					answer += a * b;
				}
			} else {
				answer += a;
			}
		}
		
		System.out.println(answer);
	}
}