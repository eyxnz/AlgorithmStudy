import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if(!unionParent(a, b)) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	private static boolean unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a == b) {
			return false;
		}
		
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
		
		return true;
	}

	private static int findParent(int x) {
		if(x == parent[x]) {
			return x;
		}
		
		return parent[x] = findParent(parent[x]);
	}
}
