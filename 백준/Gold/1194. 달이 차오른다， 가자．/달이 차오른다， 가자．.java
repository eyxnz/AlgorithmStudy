import java.io.*;
import java.util.*;

public class Main {
	static int N, M; // 세로, 가로
	static int sx, sy; // 민식이의 현재 위치
	static char[][] maze; // 미로
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new char[N][M];
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < M; j++) {
				maze[i][j] = temp.charAt(j);
				
				if(maze[i][j] == '0') {
					sx = i;
					sy = j;
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		int[][][] visited = new int[N][M][1 << 6];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				Arrays.fill(visited[i][j], -1);
			}
		}
		
		queue.offer(new int[] {sx, sy, 0});
		visited[sx][sy][0] = 0;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], keys = now[2];
			
			if(maze[x][y] == '1') {
				return visited[x][y][keys];
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) { // 범위 밖
					continue;
				}
				if(maze[nx][ny] == '#') { // 벽
					continue;
				}
				
				// 열쇠
				if(0 <= maze[nx][ny] - 'a' && maze[nx][ny] - 'a' <= 5) {
					int key = maze[nx][ny] - 'a';
					int newKeys = keys;
					
					if((keys & (1 << key)) == 0) { // 새로운 열쇠라면
						newKeys |= (1 << key);
					}
					
					if(visited[nx][ny][newKeys] != -1) {
						continue;
					}
					
					queue.offer(new int[] {nx, ny, newKeys});
					visited[nx][ny][newKeys] = visited[x][y][keys] + 1;
					
					continue;
				}
				
				// 문
				if(0 <= maze[nx][ny] - 'A' && maze[nx][ny] - 'A' <= 5) {
					int door = maze[nx][ny] - 'A';
					
					if((keys & (1 << door)) == 0) {
						continue;
					}
					
					if(visited[nx][ny][keys] != -1) {
						continue;
					}
					
					queue.offer(new int[] {nx, ny, keys});
					visited[nx][ny][keys] = visited[x][y][keys] + 1;
					
					continue;
				}
				
				// 빈 칸
				if(visited[nx][ny][keys] != -1) {
					continue;
				}
				queue.offer(new int[] {nx, ny, keys});
				visited[nx][ny][keys] = visited[x][y][keys] + 1;
			}
		}
		
		return -1;
	}
}
