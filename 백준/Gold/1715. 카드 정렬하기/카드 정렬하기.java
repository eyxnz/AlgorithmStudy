import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N; // 1 ≤ N ≤ 100,000
	static PriorityQueue<Integer> cards;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		cards = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			cards.offer(Integer.parseInt(br.readLine()));
		}
		
		while(cards.size() > 1) {
			int total = cards.poll() + cards.poll();
			answer += total;

			cards.offer(total);
		}
		
		System.out.println(answer);
	}
}