import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 높이, 너비
	static char[][] arr; // 공간
	static int CX, CY; // 도도 위치
	static int EX, EY; // 탈출구 위치
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			
			for(int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j);
				
				if(arr[i][j] == 'C') {
					CX = i;
					CY = j;
					continue;
				}
				
				if(arr[i][j] == 'E') {
					EX = i;
					EY = j;
					continue;
				}
			}
		}
		
		int answer = bfs(CX, CY);
		if(answer == -1) {
			System.out.println("dodo sad");
		} else {
			System.out.println(answer);
		}
	}

	private static int bfs(int x, int y) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[2], o2[2]); // 소비한 체력이 가장 적은 경우를 먼저 탐색
		});
		int[][] visited = new int[N][M]; // 해당 좌표에서 소비한 체력 저장 -> 더 적은 체력을 소비한 경우 갱신 가능
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		pq.offer(new int[] {x, y, 0});
		visited[x][y] = 0;
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			x = now[0];
			y = now[1];
			int z = now[2];
			
			if(x == EX && y == EY) {
				return z;
			}

			int nx = 0, ny = 0;
			
			// 상
			nx = x - 1;
			ny = y; 
			if(arr[x][y] == 'L') { // 현재 위치가 사다리
				if(nx >= 0 && arr[nx][ny] != 'D' && arr[nx][ny] != 'X' && visited[nx][ny] > z + 5) { // 사다리를 타고 이동한 위치가 강아지가 아니고 아래가 뚫려있는 공간도 아님
					pq.offer(new int[] {nx, ny, z + 5});
					visited[nx][ny] = z + 5;
				}
			}
			
			// 하
			nx = x + 1;
			ny = y;
			if(nx < N && arr[nx][ny] == 'L' && visited[nx][ny] > z + 5) { // 아래 위치가 사다리
				pq.offer(new int[] {nx, ny, z + 5});
				visited[nx][ny] = z + 5;
			}
			
			// 좌
			nx = x;
			ny = y - 1;
			if(ny >= 0) {
				if(arr[nx][ny] == 'X') {
					boolean flag = false;
					
					while(nx < N) {
						if(arr[nx][ny] == 'D') {
							break;
						}
						
						if(arr[nx][ny] != 'D' && arr[nx][ny] != 'X') {
							flag = true;
							break;
						}
						
						nx++;
					}
					
					if(flag && visited[nx][ny] > z + 11) {
						pq.offer(new int[] {nx, ny, z + 11});
						visited[nx][ny] = z + 11;
					}
				} else if(arr[nx][ny] != 'D' && visited[nx][ny] > z + 1) {
					pq.offer(new int[] {nx, ny, z + 1});
					visited[nx][ny] = z + 1;
				}
			}
			
			// 우
			nx = x;
			ny = y + 1;
			if(ny < M) {
				if(arr[nx][ny] == 'X') {
					boolean flag = false;
					
					while(nx < N) {
						if(arr[nx][ny] == 'D') {
							break;
						}
						
						if(arr[nx][ny] != 'D' && arr[nx][ny] != 'X') {
							flag = true;
							break;
						}
						
						nx++;
					}
					
					if(flag && visited[nx][ny] > z + 11) {
						pq.offer(new int[] {nx, ny, z + 11});
						visited[nx][ny] = z + 11;
					}
				} else if(arr[nx][ny] != 'D' && visited[nx][ny] > z + 1) {
					pq.offer(new int[] {nx, ny, z + 1});
					visited[nx][ny] = z + 1;
				}
			}
		}
		
		return -1;
	}
}
