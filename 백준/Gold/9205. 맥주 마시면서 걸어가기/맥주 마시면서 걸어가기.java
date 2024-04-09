import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int M = 50; // 50미터에 한 병씩
	static int MAX = 20; // 박스에 담을 수 있는 최대 맥주 개수

	static int N; // 맥주를 파는 편의점의 개수 (0 ≤ N ≤ 100)
	static int[][] store; // 편의점 좌표
	static int HX, HY; // 상근이네 집 좌표
	static int RX, RY; // 락 페스티벌 좌표
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			store = new int[N][2];
			
			st = new StringTokenizer(br.readLine(), " ");
			HX = Integer.parseInt(st.nextToken());
			HY = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				store[i][0] = Integer.parseInt(st.nextToken());
				store[i][1] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			RX = Integer.parseInt(st.nextToken());
			RY = Integer.parseInt(st.nextToken());
			
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static String bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.offer(new int[] {HX, HY});
		
		while(!queue.isEmpty()) {
			int[] position = queue.poll();
			int x = position[0], y = position[1];
			
			if(Math.abs(x - RX) + Math.abs(y - RY) <= MAX * M) {
				return "happy";
			}
			
			for(int i = 0; i < N; i++) {
				int nx = store[i][0], ny = store[i][1];
				
				if(visited[i]) {
					continue;
				}
				
				if(Math.abs(x - nx) + Math.abs(y - ny) > MAX * M) {
					continue;
				}
				
				visited[i] = true;
				queue.offer(new int[] {nx, ny});
			}
		}
		
		return "sad";
	}
}