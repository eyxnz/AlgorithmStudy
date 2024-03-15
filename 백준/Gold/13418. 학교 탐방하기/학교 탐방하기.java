import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
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
		return Integer.compare(o.c, c); // 내리막길
	}
}

public class Main {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> bestPQ;
	static PriorityQueue<Edge> worstPQ;
	static int best;
	static int worst;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		bestPQ = new PriorityQueue<>();
		worstPQ = new PriorityQueue<>(Collections.reverseOrder());
		
		// 입구와 1번 도로 간의 연결 관계
		st = new StringTokenizer(br.readLine(), " ");
		int SA = Integer.parseInt(st.nextToken());
		int SB = Integer.parseInt(st.nextToken());
		int SC = Integer.parseInt(st.nextToken());
		
		// 도로 정보
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			bestPQ.offer(new Edge(a, b, c));
			worstPQ.offer(new Edge(a, b, c));
		}
		
		// 최적의 경로 간 피로도 구하기
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		unionParent(SA, SB);
		if(SC == 0) { // 오르막길
			best++;
		}
		
		while(!bestPQ.isEmpty()) {
			Edge edge = bestPQ.poll();
			int a = edge.a;
			int b = edge.b;
			int c = edge.c;
			
			if(!unionParent(a, b)) {
				continue;
			}
			
			if(c == 0) {
				best++;
			}
		}
		
		best *= best;
		
		// 최악의 경로 간 피로도 구하기
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
				
		unionParent(SA, SB);
		if(SC == 0) { // 오르막길
			worst++;
		}
				
		while(!worstPQ.isEmpty()) {
			Edge edge = worstPQ.poll();
			int a = edge.a;
			int b = edge.b;
			int c = edge.c;
					
			if(!unionParent(a, b)) {
				continue;
			}
					
			if(c == 0) {
				worst++;
			}
		}
				
		worst *= worst;
		
		System.out.println(worst - best);
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