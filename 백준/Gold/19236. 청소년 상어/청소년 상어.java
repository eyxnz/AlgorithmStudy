import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
	int num; // 번호
	int direction; // 방향
	int x, y; // 좌표
	
	public Fish(int num, int direction, int x, int y) {
		this.num = num;
		this.direction = direction;
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N = 4; // 공간 크기
	static int answer;
	
	static int[][] dir = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; // 화살표 방향
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] arr = new int[N][N]; // 해당 좌표에 있는 물고기 번호 저장 (0 : 상어, -1 : 빈 공간)
		Fish[] fish = new Fish[N * N + 1]; // 해당 번호의 물고기 정보 저장
		
		for(int i = 0; i < N; i++) { // 행
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) { // 열
				int a = Integer.parseInt(st.nextToken()); // 번호
				int b = Integer.parseInt(st.nextToken()); // 방향
				
				arr[i][j] = a;
				fish[a] = new Fish(a, b, i, j);
			}
		}
		
		// [상황 1] 상어가 공간에 들어감
		int eat = arr[0][0];
		Fish shark = new Fish(0, fish[eat].direction, 0, 0);
		
		arr[0][0] = 0;
		fish[eat] = null;
		
		play(arr, fish, shark, eat);
		
		System.out.println(answer);
	}

	private static void play(int[][] arr, Fish[] fish, Fish shark, int eat) {
		// 깊은 복사
		int[][] tempArr = new int[N][N];
		for(int i = 0; i < N; i++) {
			tempArr[i] = arr[i].clone();
		}
		Fish[] tempFish = new Fish[N * N + 1];
		tempFish = fish.clone();
		
		// [상황 2] 물고기 이동
		for(int i = 1; i < N * N + 1; i++) {
			if(tempFish[i] == null) { // 이미 먹힌 물고기
				continue;
			}
			
			Fish now = tempFish[i];
			int x = now.x, y = now.y, nd = now.direction;
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[nd][0], ny = y + dir[nd][1];
				
				if(!checkArea(nx, ny) || tempArr[nx][ny] == 0) { // 범위 밖 or 상어
					nd = nd + 1 >= dir.length ? 1 : nd + 1; // 반시계 회전
					continue;
				}
				
				if(tempArr[nx][ny] == -1) { // 빈 공간
					tempArr[x][y] = -1;
					tempArr[nx][ny] = now.num;
					
					tempFish[now.num] = new Fish(now.num, nd, nx, ny);
				} else { // 다른 물고기 존재
					Fish another = tempFish[tempArr[nx][ny]];
					
					tempArr[x][y] = another.num;
					tempArr[nx][ny] = now.num;
					
					tempFish[now.num] = new Fish(now.num, nd, nx, ny);
					tempFish[another.num] = new Fish(another.num, another.direction, x, y);
				}
				
				break;
			}
		}
		
		// [상황 3] 상어 이동
		int cnt = 0; // 상어가 이동할 수 있는 칸의 개수
		int x = shark.x, y = shark.y, d = shark.direction; // 상어 정보
		int nx = x + dir[d][0], ny = y + dir[d][1]; // 이동 좌표
		tempArr[x][y] = -1; // 현재 상어가 있던 곳은 빈 공간으로 처리
		
		while(checkArea(nx, ny)) {
			if(tempArr[nx][ny] != -1) {
				cnt++;
				
				// 먹을 물고기
				int num = tempArr[nx][ny];
				Fish target = tempFish[num];
				
				tempArr[nx][ny] = 0;
				tempFish[num] = null;
				Fish tempShark = new Fish(0, target.direction, nx, ny);
				
				play(tempArr, tempFish, tempShark, eat + num);
				
				tempArr[nx][ny] = num;
				tempFish[num] = target;
			}
			
			nx += dir[d][0];
			ny += dir[d][1];
		}
		
		// 상어가 이동할 수 있는 칸이 없음 -> 종료
		if(cnt == 0) {
			answer = answer > eat ? answer : eat;
			return;
		}
	}
	
	private static boolean checkArea(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= N);
	}
}