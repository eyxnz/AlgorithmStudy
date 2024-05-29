import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int RX, RY, BX, BY;
	static char[][] map;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				
				if(map[i][j] == 'R') {
					RX = i;
					RY = j;
				} else if(map[i][j] == 'B') {
					BX = i;
					BY = j;
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		queue.offer(new int[] {RX, RY, BX, BY, 0});
		visited[RX][RY][BX][BY] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int rx = now[0], ry = now[1], bx = now[2], by = now[3], cnt = now[4];
			
			if(cnt > 10) {
				break;
			}
			
			if(map[rx][ry] == 'O') {
				return 1;
			}
			
			for(int d = 0; d < dir.length; d++) {
				int nrx = rx, nry = ry, nbx = bx, nby = by; // 현재 위치
				int r = 0, b = 0;
				
				while(true) {
					if(map[nrx][nry] == '#') {
						nrx -= dir[d][0];
						nry -= dir[d][1];
						r--;
						break;
					}
					
					if(map[nrx][nry] == 'O') {
						break;
					}
					
					nrx += dir[d][0];
					nry += dir[d][1];
					r++;
				}
				
				while(true) {
					if(map[nbx][nby] == '#') {
						nbx -= dir[d][0];
						nby -= dir[d][1];
						b--;
						break;
					}
					
					if(map[nbx][nby] == 'O') {
						break;
					}
					
					nbx += dir[d][0];
					nby += dir[d][1];
					b++;
				}
				
				if(nrx == nbx && nry == nby) {
					if(map[nbx][nby] == 'O') {
						continue;
					}
					
					if(r > b) {
						nrx -= dir[d][0];
						nry -= dir[d][1];
					} else {
						nbx -= dir[d][0];
						nby -= dir[d][1];
					}
				}
				
				if(visited[nrx][nry][nbx][nby]) {
					continue;
				}
				
				queue.offer(new int[] {nrx, nry, nbx, nby, cnt + 1});
				visited[nrx][nry][nbx][nby] = true;
			}
		}
		
		return 0;
	}
}
