import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int node;
	int cost;
	
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(cost, o.cost);
	}
}

public class Main {
	static int N, M;
	static List<Edge>[] edges;
	static int S, T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra());
	}

	private static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Edge(S, 0));
		dist[S] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			int node = now.node;
			int cost = now.cost;
			
			if(visited[node]) {
				continue;
			}
			visited[node] = true;
			
			for(Edge adj : edges[node]) {
				int adjNode = adj.node;
				int newCost = cost + adj.cost;
				
				if(newCost >= dist[adjNode]) {
					continue;
				}
				
				pq.offer(new Edge(adjNode, newCost));
				dist[adjNode] = newCost;
			}
		}
		
		return dist[T];
	}
}