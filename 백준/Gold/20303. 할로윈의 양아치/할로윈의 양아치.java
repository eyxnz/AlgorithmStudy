import java.io.*;
import java.util.*;

class Candy {
	int amount;
	int count;
	
	public Candy(int amount, int count) {
		this.amount = amount;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Candy [amount=" + amount + ", count=" + count + "]";
	}
}

public class Main {
	static int N, M, K;
	static int[] candy; // 아이들이 받은 사탕의 양
	static int[] parent; // 집합
	static int[] setAmount; // 해당 집합의 아이들의 인원 수
	static int[] setCount; // 해당 집합의 사탕의 양
	
	static Candy[] candies;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		candy = new int[N + 1];
		parent = new int[N + 1];
		setAmount = new int[N + 1];
		setCount = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < N + 1; i++) {
			candy[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			unionParent(a, b);
		}
		
		int size = 0;
		for(int i = 1; i < N + 1; i++) {
			int p = findParent(i);
			
			setAmount[p]++;
			setCount[p] += candy[i];
			
			if(setAmount[p] == 1) {
				size++;
			}
		}
		
		int idx = 1;
		candies = new Candy[size + 1];
		candies[0] = new Candy(0, 0);
		for(int i = 1; i < N + 1; i++) {
			if(setAmount[i] == 0) {
				continue;
			}
			
			candies[idx++] = new Candy(setAmount[i], setCount[i]);
		}
		
		dp = new int[size + 1][K];
		for(int i = 1; i < size + 1; i++) {
			for(int k = 0; k < K; k++) {
				int amount = candies[i].amount;
				int count = candies[i].count;
				
				if(amount > k) {
					dp[i][k] = dp[i - 1][k];
					continue;
				}
				
				dp[i][k] = Math.max(dp[i - 1][k], dp[i - 1][k - amount] + count);
			}
		}
		
		System.out.println(dp[size][K - 1]);
	}

	private static int findParent(int x) {
		if(x == parent[x]) {
			return parent[x];
		}
		return parent[x] = findParent(parent[x]);
	}

	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a <= b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}
