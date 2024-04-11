import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R;
	static int[][] arr;
	
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 이동 방향
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sx = 0, sy = 0; // 좌상단 좌표
		int n = N, m = M;
		for(int cnt = 0; cnt < Math.min(N, M) / 2; cnt++) { // 껍질 개수
			int cycle = ((n - 1) + (m - 1)) * 2; // 주기
			int rotate = R % cycle; // 회전의 수
			
			for(int r = 0; r < rotate; r++) {
				int x = sx, y = sy, d = 0; // 시작
				int temp = arr[sx][sy]; // 시작 위치
				
				for(int i = 0; i < cycle - 1; i++) {
					int nx = x + dir[d][0], ny = y + dir[d][1];
					
					if(nx < sx || nx >= sx + n || ny < sy || ny >= sy + m) { // 범위 밖 -> 방향 전환
						d = (d + 1) % dir.length;
						nx = x + dir[d][0];
						ny = y + dir[d][1];
					}
					
					arr[x][y] = arr[nx][ny];
					x = nx;
					y = ny;
				}
				arr[x][y] = temp;
			}
			
			n -= 2;
			m -= 2;
			sx++;
			sy++;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
