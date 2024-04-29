import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int a;
	int b;
	int c;
	
	public Edge(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(c, o.c); // 두 컴퓨터를 연결하는데 비용이 작은 순
	}
}

public class Main {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		pq = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(a, b, c));
		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int a = edge.a;
			int b = edge.b;
			int c = edge.c;
			
			if(!unionParent(a, b)) {
				continue;
			}
			
			answer += c;
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
			return parent[x];
		}
		return parent[x] = findParent(parent[x]);
	}
}
