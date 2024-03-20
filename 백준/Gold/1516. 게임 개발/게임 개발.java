import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 건물의 종류 수 (1 ≤ N ≤ 500)
	static long[] time; // 건물을 짓는데 걸리는 시간
	static long[] sum; // 건물을 짓는데 걸리는 누적 시간
	static List<Integer>[] list; // 건물 관계
	static List<Integer>[] reverse; // 건물 관계 반대
	static Queue<Integer> queue; // 진입 차수가 0인 경우
	static int[] inDegree; // 진입 차수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
	
		N = Integer.parseInt(br.readLine());
		time = new long[N + 1];
		sum = new long[N + 1];
		inDegree = new int[N + 1];
		
		list = new ArrayList[N + 1];
		reverse = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			time[i] = Long.parseLong(st.nextToken());
			sum[i] = time[i];
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if(num == -1) {
					break;
				}
				
				// num 를 짓고 i 를 지어야 함
				list[num].add(i);
				reverse[i].add(num);
				inDegree[i]++;
			}
		}
		
		queue = new LinkedList<>();
		for(int i = 1; i < N + 1; i++) {
			if(inDegree[i] != 0) {
				continue;
			}
			
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			
			for(int y : list[x]) {
				inDegree[y]--;
				
				if(inDegree[y] != 0) {
					continue;
				}
				
				queue.offer(y);
				
				long max = 0;
				for(int xx : reverse[y]) { // y 를 짓기 위해 필요한 건물들이 모두 지어지는 시간 찾기
					max = max < sum[xx] ? sum[xx] : max;
				}
				sum[y] += max;
			}
		}
		
		for(int i = 1; i < N + 1; i++) {
			sb.append(sum[i]).append("\n");
		}
		System.out.print(sb.toString());
	}
}