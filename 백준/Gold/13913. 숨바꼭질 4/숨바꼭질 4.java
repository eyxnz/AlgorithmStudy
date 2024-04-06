import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int MAX = 100000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs();
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[2 * MAX + 1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		queue.offer(N);
		visited[N] = -1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll(); // 현재 위치
			
			if(now == K) {
				StringBuilder sb = new StringBuilder();
				List<Integer> answer = new ArrayList<>();
				
				do {
					answer.add(now);
					now = visited[now];
				} while(now != -1);
				
				sb.append(answer.size() - 1).append("\n");
				for(int i = answer.size() - 1; i >= 0; i--) {
					sb.append(answer.get(i)).append(" ");
				}
				
				System.out.println(sb.toString());
				return;
			}
			
			int[] dir = {-1, 1, now};
			for(int d = 0; d < dir.length; d++) {
				int next = now + dir[d];
				
				if(next < 0 || next > 2 * MAX) {
					continue;
				}
				if(visited[next] != Integer.MAX_VALUE) {
					continue;
				}
				
				queue.offer(next);
				visited[next] = now;
			}
		}
	}
}
