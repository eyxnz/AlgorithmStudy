import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Time implements Comparable<Time> {
	int start;
	int end;
	
	public Time(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Time o) { // 빨리 끝나고, 빨리 시작하는 순 정렬
		if(Integer.compare(end, o.end) == 0) {
			return Integer.compare(start, o.start);
		}
		return Integer.compare(end, o.end);
	}
}

public class Main {
	static int N;
	static int answer = 1;
	static PriorityQueue<Time> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.offer(new Time(start, end));
		}
		
		Time first = pq.poll();
		int end = first.end;
		
		while(!pq.isEmpty()) {
			Time t = pq.poll();
			
			if(end <= t.start) { // 이어서 회의를 시작할 수 있으면
				end = t.end; // 갱신
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}
