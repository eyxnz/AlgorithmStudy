import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] arr;
	static List<int[]> list;
	static int size;
	static boolean[] choice;
	static int answer = Integer.MAX_VALUE;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		list = new ArrayList<>(10);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if(arr[i][j] == 2) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		size = list.size();
		choice = new boolean[size];
		combi(0, 0);
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	private static void combi(int depth, int start) {
		if(depth == M) {
			Queue<int[]> queue = new LinkedList<>();
			int[][] visited = new int[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			for(int i = 0; i < size; i++) {
				if(!choice[i]) {
					continue;
				}
				
				int[] now = list.get(i);
				queue.offer(now);
				visited[now[0]][now[1]] = 0;
			}
			
			while(!queue.isEmpty()) {
				int[] now = queue.poll();
				int x = now[0], y = now[1];
				
				for(int d = 0; d < dir.length; d++) {
					int nx = x + dir[d][0], ny = y + dir[d][1];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					if(arr[nx][ny] == 1) {
						continue;
					}
					if(visited[nx][ny] != -1) {
						continue;
					}
					
					queue.offer(new int[] {nx, ny});
					visited[nx][ny] = visited[x][y] + 1;
				}
			}
			
			int total = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == -1 && arr[i][j] != 1) {
						return;
					}
					
					if(arr[i][j] == 1) {
						continue;
					}
					
					total = total > visited[i][j] ? total : visited[i][j];
				}
			}
			
			answer = answer > total ? total : answer;
			
			return;
		}
		
		for(int i = start; i < size; i++) {
			choice[i] = true;
			combi(depth + 1, i + 1);
			choice[i] = false;
		}
	}
}
