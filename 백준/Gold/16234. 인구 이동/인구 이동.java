import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R;
	static int[][] A;
	static int[][] tempA;
	static boolean[][] visited;
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		tempA = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			int cnt = 0;
			
			visited = new boolean[N][N];
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(visited[r][c]) {
						continue;
					}
					
					cnt += bfs(r, c);
				}
			}
			
			if(cnt == 0) {
				break;
			}
			
			A = tempA;
			
			answer++;
		}
		
		System.out.println(answer);
	}

	private static int bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		List<int[]> list = new ArrayList<>();
		int total = 0;
		
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		list.add(new int[] {r, c});
		total += A[r][c];
		tempA[r][c] = A[r][c];
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			r = now[0];
			c = now[1];
			
			for(int d = 0; d < dir.length; d++) {
				int nr = r + dir[d][0], nc = c + dir[d][1];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				
				int bound = Math.abs(A[r][c] - A[nr][nc]);
				if(bound < L || bound > R) {
					continue;
				}
				
				if(visited[nr][nc]) {
					continue;
				}
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				list.add(new int[] {nr, nc});
				total += A[nr][nc];
			}
		}
		
		if(list.size() == 1) {
			return 0;
		}
		
		int people = total / list.size();
		for(int i = 0; i < list.size(); i++) {
			int[] now = list.get(i);
			r = now[0];
			c = now[1];
			
			tempA[r][c] = people;
		}
		
		return 1;
	}
}
