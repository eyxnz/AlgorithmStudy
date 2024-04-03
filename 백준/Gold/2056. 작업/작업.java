import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] time;
	static int[] total;
	static int[] inDegree;
	static List<Integer>[] pre;
	static Queue<Integer> queue;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		time = new int[N + 1];
		total = new int[N + 1];
		inDegree = new int[N + 1];
		pre = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			pre[i] = new ArrayList<>();
		}
		queue = new LinkedList<>();
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			time[i] = Integer.parseInt(st.nextToken()); // 걸리는 시간
			
			int cnt = Integer.parseInt(st.nextToken()); // 선행 관계에 있는 작업들의 개수
			inDegree[i] += cnt;
			
			if(cnt == 0) {
				queue.offer(i);
			}
			
			for(int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken()); // 선행 관계에 있는 작업들의 번호
				
				pre[num].add(i); // 선행 관계 표시
			}
		}
		
		while(!queue.isEmpty()) {
			int work = queue.poll();
			
			total[work] += time[work];
			answer = Math.max(answer, total[work]);
			
			for(int another : pre[work]) {
				inDegree[another]--;
				
				total[another] = Math.max(total[another], total[work]);
				if(inDegree[another] == 0) {
					queue.offer(another);
				}
			}
		}
		
		System.out.println(answer);
	}
}
