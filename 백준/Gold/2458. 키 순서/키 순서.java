import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[][] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b); // a -> b
		}
		
		visited = new boolean[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			dfs(i, i);
		}
		
		for(int i = 1; i < N + 1; i++) {
			int cnt = 0;
			
			for(int j = 1; j < N + 1; j++) {
				if(i == j) {
					continue;
				}
				
				if(visited[i][j]) {
					cnt++;
				}
				if(visited[j][i]) {
					cnt++;
				}
				
				if(cnt == N - 1) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int start, int x) {
		for(int y : graph[x]) {
			if(visited[start][y]) {
				continue;
			}
			
			visited[start][y] = true;
			dfs(start, y);
		}
	}
}
