import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static int[] degree;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		degree = new int[V + 1];
		parent = new int[V + 1];
		for(int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			degree[a]++;
			degree[b]++;
			
			unionParent(a, b);
		}
		
		for(int i = 1; i < V; i++) {
			if(findParent(i) != findParent(i + 1)) {
				System.out.println("NO");
				return;
			}
		}

		// 오일러 경로
		int even = 0, odd = 0; // 짝, 홀
		for(int i = 1; i < V + 1; i++) {
			if(degree[i] % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		if(even == V || odd == 2) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a == b) {
			return;
		}
		
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	private static int findParent(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = findParent(parent[x]);
	}
}
