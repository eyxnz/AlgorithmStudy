import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int x;
	int y;
	int z; // 잃은 생명
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int compareTo(Point o) {
		return Integer.compare(z, o.z);
	}
}

public class Main {
	static int N; // 위험한 구역의 수
	static int M; // 죽음의 구역의 수
	static int SIZE = 500; // 크기 (0 ~ 500)
	static int[][] arr; // 전체 구역
	static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 이동 방향
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		arr = new int[SIZE + 1][SIZE + 1];
		
		N = Integer.parseInt(br.readLine()); // 위험한 구역 -> 1로 세팅
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int minX = x1 > x2 ? x2 : x1;
			int maxX = x1 > x2 ? x1 : x2;
			int minY = y1 > y2 ? y2 : y1;
			int maxY = y1 > y2 ? y1 : y2;
			
			for(int x = minX; x < maxX + 1; x++) {
				for(int y = minY; y < maxY + 1; y++) {
					arr[x][y] = 1;
				}
			}
		}
		
		M = Integer.parseInt(br.readLine()); // 죽음의 구역 -> 2로 세팅
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int minX = x1 > x2 ? x2 : x1;
			int maxX = x1 > x2 ? x1 : x2;
			int minY = y1 > y2 ? y2 : y1;
			int maxY = y1 > y2 ? y1 : y2;
			
			for(int x = minX; x < maxX + 1; x++) {
				for(int y = minY; y < maxY + 1; y++) {
					arr[x][y] = 2;
				}
			}
		}
		
		System.out.println(dijkstra(0, 0));
	}

	private static int dijkstra(int x, int y) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int[][] dist = new int[SIZE + 1][SIZE + 1];
		for(int i = 0; i < SIZE + 1; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		boolean[][] visited = new boolean[SIZE + 1][SIZE + 1];
		
		pq.offer(new Point(x, y, 0));
		dist[x][y] = 0;
		
		while(!pq.isEmpty()) {
			Point p = pq.poll();
			x = p.x;
			y = p.y;
			int z = p.z;
			
			if(visited[x][y]) {
				continue;
			}
			visited[x][y] = true;
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1], nz = z;
				
				if(nx < 0 || nx > SIZE || ny < 0 || ny > SIZE) { // 범위 밖
					continue;
				}
				if(arr[nx][ny] == 2) {
					continue;
				}
				
				if(arr[nx][ny] == 1) {
					nz++;
				}
				
				if(nz >= dist[nx][ny]) {
					continue;
				}
				pq.offer(new Point(nx, ny, nz));
				dist[nx][ny] = nz;
			}
		}
		
		if(dist[SIZE][SIZE] == Integer.MAX_VALUE) {
			return -1;
		} else {
			return dist[SIZE][SIZE];
		}
	}
}