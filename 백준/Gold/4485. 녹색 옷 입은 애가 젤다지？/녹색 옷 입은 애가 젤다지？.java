import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 1;
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
			
			int answer = dijkstra();
			sb.append("Problem ").append(T).append(": ").append(answer).append("\n");
			
			T++;
		}
		
		System.out.print(sb.toString());
	}

	private static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[2], o2[2]);
		});
		int[][] dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		pq.offer(new int[] {0, 0, arr[0][0]});
		dist[0][0] = arr[0][0];
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int x = now[0], y = now[1], cost = now[2];
			
			if(dist[x][y] < cost) {
				continue;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				
				int newCost = cost + arr[nx][ny];
				if(dist[nx][ny] > newCost) {
					pq.offer(new int[] {nx, ny, newCost});
					dist[nx][ny] = newCost;
				}
			}
		}
		
		return dist[N - 1][N - 1];
	}
}