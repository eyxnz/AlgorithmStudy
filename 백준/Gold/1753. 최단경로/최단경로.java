import java.io.*;
import java.util.*;

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
	static int V, E, K;
	static List<Edge>[] edges;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[V + 1];
		for(int i = 1; i < V + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[u].add(new Edge(v, w));
		}
		
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[K] = 0;
		
		dijkstra(K);
		
		for(int i = 1; i < V + 1; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.print(sb.toString());
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(dist[now.node] < now.cost) {
				continue;
			}
			
			for(int i = 0; i < edges[now.node].size(); i++) {
				Edge next = edges[now.node].get(i);
				int newCost = now.cost + next.cost;
				
				if(dist[next.node] <= newCost) {
					continue;
				}
				
				pq.offer(new Edge(next.node, newCost));
				dist[next.node] = newCost;
			}
		}
	}
}
