import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int node;
	int cost;
	
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(cost, o.cost);
	}
}

public class Main {
	static int N; // 도시
	static int M; // 버스
	static int S, E;
	
	static List<Node>[] edges;
	
	static PriorityQueue<Node> pq;
	static int[] dist;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edges[start].add(new Node(end, cost));
		}
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Node(S, 0));
		dist[S] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int node = now.node;
			int cost = now.cost;
			
			if(cost > dist[node]) {
				continue;
			}
			
			for(Node adj : edges[node]) {
				int adjNode = adj.node;
				int adjCost = cost + adj.cost;
				
				if(adjCost >= dist[adjNode]) {
					continue;
				}
				
				pq.offer(new Node(adjNode, adjCost));
				dist[adjNode] = adjCost;
			}
		}
		
		answer = dist[E];
		System.out.println(answer);
	}
}
