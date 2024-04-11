import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int cost;
	
	public Point(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Point o) {
		return Integer.compare(cost, o.cost);
	}
}

public class Main {
	static int T = 1;
	static int N;
	static int[][] arr;
	
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) {
				break;
			}
			
			arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("Problem ").append(T).append(": ");
			sb.append(dijkstra(0, 0));
			sb.append("\n");
			
			T++;
		}
		
		System.out.print(sb.toString());
	}

	private static int dijkstra(int x, int y) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int[][] dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		pq.offer(new Point(x, y, arr[x][y]));
		dist[x][y] = arr[x][y];
		
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			x = p.x;
			y = p.y;
			int cost = p.cost;
			
			if(cost > dist[x][y]) {
				continue;
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				
				int ncost = cost + arr[nx][ny];
				if(ncost >= dist[nx][ny]) {
					continue;
				}
				
				pq.offer(new Point(nx, ny, ncost));
				dist[nx][ny] = ncost;
			}
		}
		
		return dist[N - 1][N - 1];
	}
}