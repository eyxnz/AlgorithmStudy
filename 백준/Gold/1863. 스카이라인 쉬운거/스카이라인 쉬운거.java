import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Stack<Integer> stack;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while(!stack.isEmpty() && stack.peek() > y) { // 현재 높이보다 높은 건물은 건물 하나로 취급
				stack.pop();
				answer++;
			}
			
			if(!stack.isEmpty() && stack.peek() == y) {
				continue;
			}
			
			stack.push(y);
		}
		
		while(!stack.isEmpty()) { // 스택이 비지 않았다면
			int height = stack.pop();
			if(height == 0) {
				continue;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
}