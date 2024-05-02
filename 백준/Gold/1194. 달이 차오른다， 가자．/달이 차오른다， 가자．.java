import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int z;
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {
	static int N, M;
	static char[][] maze;
	static int X, Y; // 민식이의 현재 위치
	
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 이동 방향
	
	public static void main(String[] args) throws Exception { // 민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값
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
					X = i;
					Y = j;
				}
			}
		}
		
		System.out.println(bfs(X, Y));
	}

	private static int bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();
		int[][][] visited = new int[N][M][64]; // 열쇠 보유 여부 체크 -> 1 1 1 1 1 1 = 32 + 16 + 8 + 4 + 2 + 1 = 63
		
		queue.offer(new Point(x, y, 0));
		visited[x][y][0] = 1;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			x = p.x;
			y = p.y;
			int z = p.z;
			
			if(maze[x][y] == '1') {
				return visited[x][y][z] - 1;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) { // 범위 밖
					continue;
				}
				if(maze[nx][ny] == '#') { // 벽
					continue;
				}
				if(visited[nx][ny][z] != 0) { // 현재 상태에서 이미 방문
					continue;
				}
				
				if(maze[nx][ny] == 'a' || maze[nx][ny] == 'b' || maze[nx][ny] == 'c' || maze[nx][ny] == 'd' || maze[nx][ny] == 'e' || maze[nx][ny] == 'f') { // 열쇠
					int key = maze[nx][ny] - 'a';
					int nz = z | (1 << key);
					
					if(visited[nx][ny][nz] != 0) {
						continue;
					}
					
					queue.offer(new Point(nx, ny, nz));
					visited[nx][ny][nz] = visited[x][y][z] + 1;
				} else if(maze[nx][ny] == 'A' || maze[nx][ny] == 'B' || maze[nx][ny] == 'C' || maze[nx][ny] == 'D' || maze[nx][ny] == 'E' || maze[nx][ny] == 'F') { // 문
					int door = maze[nx][ny] - 'A';
					
					if((z & (1 << door)) == 0) { // 열쇠가 없다면 -> 갈 수 없음
						continue;
					}
					
					queue.offer(new Point(nx, ny, z));
					visited[nx][ny][z] = visited[x][y][z] + 1;
				} else { // 빈 칸 + 출구
					queue.offer(new Point(nx, ny, z));
					visited[nx][ny][z] = visited[x][y][z] + 1;
				}
			}
		}
		
		return -1;
	}
}