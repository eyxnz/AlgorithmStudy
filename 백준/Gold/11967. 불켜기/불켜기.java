import java.io.*;
import java.util.*;

public class Main {
   static int N, M;
   static List<Integer>[] list; // 서로 불을 킬 수 있는 스위치 정보
   static int answer;
   
   static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      st = new StringTokenizer(br.readLine(), " ");
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      list = new ArrayList[N * N];
      for(int i = 0; i < N * N; i++) {
         list[i] = new ArrayList<>();
      }
      
      for(int m = 0; m < M; m++) {
         st = new StringTokenizer(br.readLine(), " ");
         
         int x = Integer.parseInt(st.nextToken()) - 1;
         int y = Integer.parseInt(st.nextToken()) - 1;
         int a = Integer.parseInt(st.nextToken()) - 1;
         int b = Integer.parseInt(st.nextToken()) - 1;
         
         // 2차원 -> 1차원
         list[x * N + y].add(a * N + b);
      }
      
      System.out.println(findRoom());
   }

	private static int findRoom() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N]; // 방문 배열
		boolean[][] isLight = new boolean[N][N]; // 불이 켜진 방
		int answer = 0;
		
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		isLight[0][0] = true;
		answer++;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0], y = now[1];
			
			// 현재 위치에서 불을 킬 수 있는 방 키기
			if(list[x * N + y].size() != 0) {
				// 방문 배열 초기화
				visited = new boolean[N][N];
				visited[x][y] = true;
				
				for(int i = list[x * N + y].size() - 1; i >= 0; i--) {
					int nroom = list[x * N + y].get(i);
					int nx = nroom / N, ny = nroom % N;
					
					// 이미 불이 켜진 곳
					if(isLight[nx][ny]) {
						continue;
					}
					
					// 새롭게 불을 킨 곳
					isLight[nx][ny] = true;
					answer++;
				}
				list[x * N + y].clear();
			}
			
			// 현재 위치에서 갈 수 있는 방 탐색
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) { // 범위 밖
					continue;
				}
				if(!isLight[nx][ny]) { // 불이 꺼진 곳
					continue;
				}
				if(visited[nx][ny]) { // 이미 방문한 곳
					continue;
				}
				
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
		
		return answer;
	}
}
