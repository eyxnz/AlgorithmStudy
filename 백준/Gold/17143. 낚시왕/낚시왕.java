import java.io.*;
import java.util.*;

class Shark {
	int s;
	int d;
	int z;
	
	public Shark(int s, int d, int z) {
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class Main {
	static int R, C, M;
	static Shark[][] arr;
	static int answer;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Shark[R][C];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			arr[r][c] = new Shark(s, d, z);
		}
		
		for(int c = 0; c < C; c++) { // 낚시왕 이동
			fishing(c);
			
			moveAndEat();
		}
		
		System.out.println(answer);
	}

	private static void moveAndEat() {
		Shark[][] newArr = new Shark[R][C];
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(arr[r][c] == null) {
					continue;
				}
				
				Shark shark = arr[r][c];
				int sr = r, sc = c;
				for(int s = 0; s < shark.s; s++) {
					int nr = sr + dir[shark.d][0], nc = sc + dir[shark.d][1]; // 다음 위치
					
					if(nr < 0 || nr >= R || nc < 0 || nc >= C) { // 범위 밖
						if(shark.d % 2 == 0) {
							shark.d++;
						} else {
							shark.d--;
						}
						
						nr = sr + dir[shark.d][0];
						nc = sc + dir[shark.d][1];
					}
					
					sr = nr;
					sc = nc;
				}
				
				if(newArr[sr][sc] == null) {
					newArr[sr][sc] = shark;
				} else { // 이미 해당 위치에 다른 상어가 있는 경우
					Shark now = newArr[sr][sc];
					
					if(now.z > shark.z) {
						continue;
					}
					
					newArr[sr][sc] = shark;
				}
			}
		}
		
		arr = newArr;
	}

	private static void fishing(int c) {
		for(int r = 0; r < R; r++) {
			if(arr[r][c] == null) {
				continue;
			}

			answer += arr[r][c].z;
			arr[r][c] = null;
			
			break;
		}
	}
}
