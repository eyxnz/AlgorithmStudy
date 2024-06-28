import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] inDegree;
	static List<Integer>[] graph;
	static Queue<Integer> queue;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	inDegree = new int[N + 1];
    	graph = new ArrayList[N + 1];
    	for(int i = 1; i < N + 1; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	queue = new LinkedList<>();
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		// a가 b의 앞
    		inDegree[b]++;
    		graph[a].add(b);
    	}
    	
    	for(int i = 1; i < N + 1; i++) {
    		if(inDegree[i] != 0) {
    			continue;
    		}
    		
    		queue.offer(i);
    	}
    	
    	while(!queue.isEmpty()) {
    		int now = queue.poll();
    		sb.append(now).append(" ");
    		
    		for(int next : graph[now]) {
    			inDegree[next]--;
    			
    			if(inDegree[next] != 0) {
    				continue;
    			}
    			
    			queue.offer(next);
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}
