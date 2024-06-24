import java.io.*;
import java.util.*;

public class Main {
	static int A, B, C, MAX;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		MAX = A + B + C;
		
		if(MAX % 3 != 0) {
			System.out.println(0);
			return;
		}
		
		System.out.println(bfs(A, B, C));
	}

	private static int bfs(int a, int b, int c) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[MAX + 1][MAX + 1];
		
		queue.offer(new int[] {a, b, c});
		visited[a][b] = true;
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			a = now[0];
			b = now[1];
			c = now[2];
			
			if(a == b && b == c) {
				return 1;
			}
			
			// a b
			if(a < b) {
				if(b - a > 0 && !visited[a + a][b - a]) {
					queue.offer(new int[] {a + a, b - a, c});
					visited[a + a][b - a] = true;
				}
			} else if(b < a) {
				if(a - b > 0 && !visited[a - b][b + b]) {
					queue.offer(new int[] {a - b, b + b, c});
					visited[a - b][b + b] = true;
				}
			}
			
			// b c
			if(b < c) {
				if(c - b > 0 && !visited[b + b][c - b]) {
					queue.offer(new int[] {a, b + b, c - b});
					visited[b + b][c - b] = true;
				}
			} else if(c < b) {
				if(b - c > 0 && !visited[b - c][c + c]) {
					queue.offer(new int[] {a, b - c, c + c});
					visited[b - c][c + c] = true;
				}
			}
			
			// a c
			if(a < c) {
				if(c - a > 0 && !visited[a + a][c - a]) {
					queue.offer(new int[] {a + a, b, c - a});
					visited[a + a][c - a] = true;
				}
			} else if(c < a) {
				if(a - c > 0 && !visited[a - c][c + c]) {
					queue.offer(new int[] {a - c, b, c + c});
					visited[a - c][c + c] = true;
				}
			}
		}
		
		return 0;
	}
}
