import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int node;
	int cost;
	
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(cost, o.cost);
	}
}

public class Main {
	static int N, M, R;
	static int[] item;
	static int[][] dist;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		item = new int[N + 1];
		dist = new int[N + 1][N + 1];
		for(int i = 1; i < N + 1; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < N + 1; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			dist[a][b] = l;
			dist[b][a] = l;
		}
		
		for(int k = 1; k < N + 1; k++) {
			for(int i = 1; i < N + 1; i++) {
				for(int j = 1; j < N + 1; j++) {
					if(dist[i][k] >= Integer.MAX_VALUE || dist[k][j] >= Integer.MAX_VALUE) {
						continue;
					}
					
					int newDist = dist[i][k] + dist[k][j];
					dist[i][j] = dist[i][j] > newDist ? newDist : dist[i][j];
				}
			}
		}

		for(int i = 1; i < N + 1; i++) {
			int sum = item[i];
			
			for(int j = 1; j < N + 1; j++) {
				if(i == j) {
					continue;
				}
				if(dist[i][j] > M) {
					continue;
				}
				
				sum += item[j];
			}
			
			answer = answer > sum ? answer : sum;
		}
		
		System.out.println(answer);
	}
}
