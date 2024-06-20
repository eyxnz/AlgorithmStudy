import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int i;
	int j;
	int cost;
	
	public Edge(int i, int j, int cost) {
		this.i = i;
		this.j = j;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(cost, o.cost);
	}
}

public class Main {
	static int N;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	static long answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				
				if(i >= j) {
					continue;
				}
				
				pq.offer(new Edge(i, j, cost));
			}
		}
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int i = now.i, j = now.j, cost = now.cost;
			
			if(!unionParent(i, j)) {
				continue;
			}
			
			answer += cost;
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
		if(parent[x] == x) {
			return parent[x];
		}
		
		return parent[x] = findParent(parent[x]);
	}
}
