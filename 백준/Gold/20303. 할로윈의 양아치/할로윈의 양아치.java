import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] parent; // 집합
	static int[] child; // 각 아이들이 받은 사탕의 수
	static Map<Integer, int[]> map; // 각 집합이 받은 사탕의 수
	static int[][] candy;
	static int[][] dp;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	parent = new int[N + 1];
    	child = new int[N + 1];
    	
    	st = new StringTokenizer(br.readLine(), " ");
    	for(int i = 1; i < N + 1; i++) {
    		parent[i] = i;
    		child[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	for(int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		unionParent(a, b);
    	}
    	
    	map = new HashMap<>();
    	
    	for(int i = 1; i < N + 1; i++) {
    		int key = findParent(i); // 집합
    		
    		if(!map.containsKey(key)) {
    			map.put(key, new int[] {1, child[i]});
    		} else {
    			int[] value = map.get(key);
    			
    			map.put(key, new int[] {value[0] + 1, value[1] + child[i]});
    		}
    	}
    	
    	int index = 1, size = map.size();
    	candy = new int[size + 1][2];
    	
    	for(int key : map.keySet()) {
    		int[] value = map.get(key);
    		
    		candy[index][0] = value[0];
    		candy[index][1] = value[1];
    		index++;
    	}
    	
    	dp = new int[size + 1][K];
    	
    	for(int i = 1; i < size + 1; i++) {
    		for(int j = 0; j < K; j++) {
    			if(candy[i][0] > j) {
    				dp[i][j] = dp[i - 1][j];
    				continue;
    			}
    			
    			dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - candy[i][0]] + candy[i][1]);
    		}
    	}
    	
    	System.out.println(dp[size][K - 1]);
    }

	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a == b) {
			return;
		} else if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	private static int findParent(int x) {
		if(x == parent[x]) {
			return x;
		}
		
		return parent[x] = findParent(parent[x]);
	}
}
