import java.io.*;
import java.util.*;

public class Main {
	static int N, M, index;
	static int[][] room;
	static int[][] visited;
	static Map<Integer, Integer> indexArea;
	
	static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 서 북 동 남
	
	static int one, two, three;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[M][N];
		visited = new int[M][N];
		indexArea = new HashMap<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] != 0) {
					continue;
				}
				
				int area = twoBfs(i, j, ++index); // index 방의 넓이
				indexArea.put(index, area);
				
				one++;
				two = two > area ? two : area;
			}
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				int now = visited[i][j];
				
				for(int d = 0; d < dir.length; d++) {
					int nx = i + dir[d][0], ny = j + dir[d][1];
					
					if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
						continue;
					}
					if(now == visited[nx][ny]) {
						continue;
					}
					
					int area = indexArea.get(now) + indexArea.get(visited[nx][ny]);
					three = three > area ? three : area;
				}
			}
		}
		
		System.out.println(one);
		System.out.println(two);
		System.out.println(three);
	}

	private static int twoBfs(int x, int y, int index) {
		Queue<int[]> queue = new LinkedList<>();
		int area = 0;
		
		queue.offer(new int[] {x, y});
		visited[x][y] = index;
		area++;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			x = now[0];
			y = now[1];
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if((room[x][y] & (1 << d)) != 0) { // 벽
					continue;
				}
				if(visited[nx][ny] != 0) { // 이미 방문한 방
					continue;
				}
				
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = index;
				area++;
			}
		}
		
		return area;
	}
}
