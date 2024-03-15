import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int a;
	int b;
	double dist;
	
	public Edge(int a, int b, double dist) {
		this.a = a;
		this.b = b;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(dist, o.dist);
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N; // 우주신들의 개수
	static int M; // 이미 연결된 신들과의 통로의 개수
	static Point[] points; // 우주신들의 좌표
	static int[] parent; // 부모 배열
	static PriorityQueue<Edge> pq;
	static double answer; // 만들어야 할 최소의 통로 길이
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		points = new Point[N + 1];
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points[i] = new Point(x, y);
		}
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			unionParent(a, b);
		}
		
		pq = new PriorityQueue<>();
		for(int a = 1; a < N; a++) {
			for(int b = a + 1; b < N + 1; b++) {
				if(findParent(a) == findParent(b)) { // 이미 연결된 우주신들
					continue;
				}
				
				long dx = (long)Math.pow(points[a].x - points[b].x, 2);
				long dy = (long)Math.pow(points[a].y - points[b].y, 2);
				double dist = Math.sqrt(dx + dy);
				
				pq.offer(new Edge(a, b, dist));
			}
		}
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int a = edge.a;
			int b = edge.b;
			double dist = edge.dist;
			
			if(!unionParent(a, b)) {
				continue;
			}
			
			answer += dist;
		}
		
		System.out.printf("%.2f", answer);
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