import java.io.*;
import java.util.*;

public class Main {
	static int N; // (4 ≤ N ≤ 50)
	static char[][] arr;
	static int[] B;
	static int[] E;
	static int bx, by, bz; // B의 중심 좌표
	static int ex, ey, ez; // E의 중심 좌표
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		B = new int[3];
		E = new int[3];
		
		int b = 0, e = 0;
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < N; j++) {
				char ch = temp.charAt(j);
				arr[i][j] = ch;
				
				if(ch == 'B') {
					B[b++] = i * N + j;
				} else if(ch == 'E') {
					E[e++] = i * N + j;
				}
			}
		}
		
		Arrays.sort(B);
		Arrays.sort(E);
		
		bx = B[1] / N;
		by = B[1] % N;
		if(B[0] / N == B[2] / N) { // 가로
			bz = 0;
		} else { // 세로
			bz = 1;
		}
		
		ex = E[1] / N;
		ey = E[1] % N;
		if(E[0] / N == E[2] / N) { // 가로
			ez = 0;
		} else { // 세로
			ez = 1;
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[N][N][2];
		
		queue.offer(new int[] {bx, by, bz, 0});
		visited[bx][by][bz] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1], z = now[2], cnt = now[3];
			
			if(x == ex && y == ey && z == ez) {
				return cnt;
			}
			
			for(int d = 0; d < 4; d++) { // 상 하 좌 우
				int nx = x + dir[d][0], ny = y + dir[d][1]; // 이동한 중심 좌표
				int lx, ly, rx, ry; // 이동한 양쪽 사이드 좌표
				
				if(z == 0) { // 가로 -> 좌 우
					lx = nx;
					ly = ny - 1;
					rx = nx;
					ry = ny + 1;
				} else { // 세로 -> 상 하
					lx = nx - 1;
					ly = ny;
					rx = nx + 1;
					ry = ny;
				}
				
				if(lx < 0 || lx >= N || ly < 0 || ly >= N || rx < 0 || rx >= N || ry < 0 || ry >= N) { // 범위 밖
					continue;
				}
				if(arr[lx][ly] == '1' || arr[nx][ny] == '1' || arr[rx][ry] == '1') { // 나무
					continue;
				}
				if(visited[nx][ny][z]) { // 이미 방문한 좌표
					continue;
				}
				
				queue.offer(new int[] {nx, ny, z, cnt + 1});
				visited[nx][ny][z] = true;
			}
			
			// 회전
			boolean canTurn = true;
			int nz = z == 0 ? 1 : 0;
			
			if(visited[x][y][nz]) { // 이미 방문한 좌표
				continue;
			}
			for(int d = 0; d < dir.length; d++) { // 8방 확인
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || arr[nx][ny] == '1') {
					canTurn = false;
					break;
				}
			}
			if(canTurn) {
				queue.offer(new int[] {x, y, nz, cnt + 1});
				visited[x][y][nz] = true;
			}
		}
		
		return 0;
	}
}
