import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) { // 연산
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(num == 0) { // 합집합
				unionParent(a, b);
			} else {
				if(findParent(a) == findParent(b)) { // 같은 집합
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		
		System.out.print(sb.toString());
	}

	private static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		
		if(x == y) { // 이미 같은 집합
			return;
		}
		
		if(x < y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}

	private static int findParent(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		return parent[x] = findParent(parent[x]);
	}
}