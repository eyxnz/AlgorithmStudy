import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
	int pos;
	int time;
	
	public Position(int pos, int time) {
		this.pos = pos;
		this.time = time;
	}
	
	@Override
	public int compareTo(Position o) {
		return Integer.compare(time, o.time);
	}
}

public class Main {
	static int N, K;
	static int MAX = 100_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra());
	}

	private static int dijkstra() {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		int[] dist = new int[MAX + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Position(N, 0));
		dist[N] = 0;
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			
			if(now.time > dist[now.pos]) {
				continue;
			}
			
			int[] dir = {-1, 1, now.pos};
			for(int i = 0; i < dir.length; i++) {
				int newPos = now.pos + dir[i];
				int newTime = now.time + 1;
				if(i == dir.length - 1) {
					newTime--;
				}
				
				if(newPos < 0 || newPos > MAX) {
					continue;
				}
				
				if(newTime < dist[newPos]) {
					pq.offer(new Position(newPos, newTime));
					dist[newPos] = newTime;
				}
			}
		}
		
		return dist[K];
	}
}
