import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
		return Integer.compare(c, o.c);
	}
}

public class Main {
	static int V, E;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V + 1];
		for(int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(a, b, c));
		}
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if(!unionParent(edge.a, edge.b)) {
				continue;
			}
			
			answer += edge.c;
		}
		
		System.out.println(answer);
	}
	
	private static int findParent(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		return parent[x] = findParent(parent[x]);
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
}
