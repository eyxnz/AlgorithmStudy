import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Road implements Comparable<Road> {
	int end;
	int length;
	
	public Road(int end, int length) {
		this.end = end;
		this.length = length;
	}

	@Override
	public int compareTo(Road o) {
		if(Integer.compare(end, o.end) == 0) {
			return Integer.compare(length, o.length);
		}
		return Integer.compare(end, o.end);
	}
}

public class Main {
	static int N;
	static int D;
	static List<Road>[] list;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[D + 1];
		for(int i = 0; i < D + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			if(start >= D || end > D || length >= end - start) { // 지름길의 의미가 없는 것
				continue;
			}
			
			list[start].add(new Road(end, length));
		}
		
		for(int i = 0; i < D + 1; i++) {
			Collections.sort(list[i]);
		}
		
		dp = new int[D + 1]; // 0 ~ D
		
		for(int i = 0; i < D + 1; i++) {
			if(i != 0) {
				if(dp[i] != 0) { // 이미 지름길로 해당 위치로 왔을 때
					dp[i] = Math.min(dp[i], dp[i - 1] + 1);
				} else { // 처음 해당 위치 방문
					dp[i] = dp[i - 1] + 1;
				}
			}
			
			if(!list[i].isEmpty()) { // 해당 위치에서 시작하는 지름길 존재
				for(Road r : list[i]) {
					int end = r.end;
					int length = r.length;
					
					if(dp[end] != 0) {
						dp[end] = Math.min(dp[end], dp[i] + length);
					} else {
						dp[end] = dp[i] + length;
					}
					
					continue;
				}
			}
		}

		System.out.println(dp[D]);
	}
}