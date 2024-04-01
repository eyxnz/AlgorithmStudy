import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int N, K;
	static int[] D; // 각 건물당 건설에 걸리는 시간
	static int W; // 승리하기 위해 건설해야 할 건물
	
	static int[] inDegree; // 진입차수
	static List<Integer>[] graph; // 건설순서 규칙
	static Queue<Integer> queue; // 진입차수가 0인 건물
	static int[] time; // 해당 건물을 건설하기까지 걸리는 시간
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			D = new int[N + 1];
			
			inDegree = new int[N + 1];
			graph = new ArrayList[N + 1];
			for(int i = 0; i < N + 1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i < N + 1; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				inDegree[y]++;
				graph[x].add(y);
			}
			
			queue = new LinkedList<>();
			for(int i = 1; i < N + 1; i++) {
				if(inDegree[i] != 0) {
					continue;
				}
				
				queue.add(i);
			}
			
			W = Integer.parseInt(br.readLine());
			time = new int[N + 1];
			
			while(!queue.isEmpty()) {
				int building = queue.poll();
				time[building] += D[building];
				
				if(building == W) {
					sb.append(time[W]).append("\n");
					break;
				}
				
				for(int near : graph[building]) {
					inDegree[near]--;
					
					time[near] = Math.max(time[near], time[building]);
					
					if(inDegree[near] == 0) {
						queue.add(near);
					}
				}
			}
		}
		
		System.out.print(sb.toString());
	}
}
