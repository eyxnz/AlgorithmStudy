import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int answer;
	
	static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		answer = Integer.MAX_VALUE;
		
		int rx = 0, ry = 0, bx = 0, by = 0;
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				
				if(map[i][j] == 'R') {
					rx = i;
					ry = j;
					continue;
				}
				
				if(map[i][j] == 'B') {
					bx = i;
					by = j;
					continue;
				}
			}
		}
		
		dfs(0, rx, ry, bx, by);
		
		if(answer == Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}

	private static void dfs(int depth, int rx, int ry, int bx, int by) {
		if(depth >= 10) { // 이동 횟수가 10번 초과
			return;
		}
		
		for(int d = 0; d < dir.length; d++) {
			int nrx = rx, nry = ry, nbx = bx, nby = by; // 해당 방향으로 기울였을 때, 구슬의 위치
			
			while(true) {
				nrx += dir[d][0];
				nry += dir[d][1];
				
				if(map[nrx][nry] == '#') {
					nrx -= dir[d][0];
					nry -= dir[d][1];
					break;
				}
				
				if(map[nrx][nry] == 'O') {
					break;
				}
			}
			
			
			while(true) {
				nbx += dir[d][0];
				nby += dir[d][1];
				
				if(map[nbx][nby] == '#') {
					nbx -= dir[d][0];
					nby -= dir[d][1];
					break;
				}
				
				if(map[nbx][nby] == 'O') {
					break;
				}
			}
			
			if(map[nbx][nby] == 'O') {
				continue;
			}
			
			if(nrx == nbx && nry == nby) { // 같은 위치? -> 더 많이 이동한 구슬을 한 칸 뒤로
				int distRed = Math.abs(nrx - rx) + Math.abs(nry - ry);
				int distBlue = Math.abs(nbx - bx) + Math.abs(nby - by);
				
				if(distRed > distBlue) {
					nrx -= dir[d][0];
					nry -= dir[d][1];
				} else {
					nbx -= dir[d][0];
					nby -= dir[d][1];
				}
			}
			
			if(map[nrx][nry] == 'O') {
				answer = answer > depth + 1 ? depth + 1 : answer;
				continue;
			}
			
			dfs(depth + 1, nrx, nry, nbx, nby);
		}
	}
}
