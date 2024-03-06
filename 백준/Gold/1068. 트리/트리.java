import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 노드의 개수 (0 < N <= 50)
	static int M; // 지울 노드의 번호
	static int root; // 루트 노드
	static List<Integer>[] parent;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parent = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			parent[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken()); // i 의 부모 p
			if(p == -1) {
				root = i;
				continue;
			}
			parent[p].add(i);
		}
		
		M = Integer.parseInt(br.readLine());
		if(M != root) { // 루트를 삭제하면 리프 노드는 존재하지 않음
			bfs(root);
		}
		
		System.out.println(answer);
	}
	private static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.offer(x);
		visited[x] = true;
		
		while(!queue.isEmpty()) {
			x = queue.poll();
			
			int cnt = 0; // 자식 노드의 개수
			for(int y : parent[x]) { // x 의 자식 노드
				if(y == M) { // 지울 노드는 탐색하지 않음 -> 자식 노드로 카운트 안 함
					continue;
				}
				if(visited[y]) { // 부모 제외
					continue;
				}
				
				queue.offer(y);
				visited[y] = true;
				cnt++;
			}
			
			if(cnt == 0) {
				answer++;
			}
		}
	}
}