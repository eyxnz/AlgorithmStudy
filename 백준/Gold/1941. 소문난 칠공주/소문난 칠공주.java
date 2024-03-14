import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N = 5;
	static int MAX = 7;
	static int[][] arr;
	static int answer;
	
	static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
		
			for(int j = 0; j < N; j++) {
				if(str.charAt(j) == 'Y') { // 임도연파
					arr[i][j] = 1;
				}
			}
		}
		
		combi(0, 0, 0, 0);
		
		System.out.println(answer);
	}

	private static void combi(int depth, int start, int s, int y) {
		if(y >= MAX / 2 + 1) { // 임도연파가 우위를 점할 경우
			return;
		}
		
		if(depth == MAX) {
			if(!check(start - 1)) {
				return;
			}
			
			answer++;
			return;
		}
		
		for(int i = start; i < N * N; i++) {
			int r = i / N, c = i % N;
			int temp = arr[r][c];
			
			arr[r][c] = 2;
			if(temp == 1) {
				combi(depth + 1, i + 1, s, y + 1);
			} else {
				combi(depth + 1, i + 1, s + 1, y);
			}
			arr[r][c] = temp;
		}
	}

	private static boolean check(int point) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		cnt++;
		queue.offer(new int[] {point / N, point % N});
		visited[point / N][point % N] = true;
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			int x = p[0];
			int y = p[1];
			
			for(int d = 0; d < dir.length; d++) {
				int nx = x + dir[d][0], ny = y + dir[d][1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				if(arr[nx][ny] != 2) {
					continue;
				}
				if(visited[nx][ny]) {
					continue;
				}
				
				cnt++;
				queue.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
		
		if(cnt != MAX) {
			return false;
		}
		return true;
	}
}