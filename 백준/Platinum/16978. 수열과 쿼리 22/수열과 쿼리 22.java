import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class SegmentTree {
	long[] tree;
	int treeSize;
	
	public SegmentTree(int arrSize) {
		int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
		treeSize = (int)Math.pow(2, h + 1);
		tree = new long[treeSize];
	}
	
	public void init(int[] arr, int node, int start, int end) {
		if(start == end) { // 리프 노드
			tree[node] = arr[start];
			return;
		}
		
		init(arr, node * 2, start, (start + end) / 2);
		init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public void update(int node, int start, int end, int index, int value) {
		if(index < start || end < index) { // 범위 밖
			return;
		}
		
		if(start == end) { // 리프 노드
			tree[node] = value;
			return;
		}
		
		update(node * 2, start, (start + end) / 2, index, value);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public long sum(int node, int start, int end, int left, int right) {
		if(right < start || end < left) { // 범위 밖
			return 0;
		}
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		long x = sum(node * 2, start, (start + end) / 2, left, right);
		long y = sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return x + y;
	}
}

class Info {
	int k;
	int i;
	int j;
	int index;
	
	public Info(int k, int i, int j, int index) {
		this.k = k;
		this.i = i;
		this.j = j;
		this.index = index;
	}
}

class Index {
	int index;
	long sum;
	
	public Index(int index, long sum) {
		this.index = index;
		this.sum = sum;
	}
}

public class Main {
	static int N, M;
	static int[] arr;
	
	static List<int[]> one;
	static PriorityQueue<Info> two;
	static PriorityQueue<Index> answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		one = new ArrayList<>();
		two = new PriorityQueue<>((o1, o2) -> { // k가 작은 순
			if(Integer.compare(o1.k, o2.k) == 0) {
				return Integer.compare(o1.index, o2.index);
			}
			return Integer.compare(o1.k, o2.k);
		});
		answer = new PriorityQueue<>((o1, o2) -> { // 쿼리 순서가 빠른 순
			return Integer.compare(o1.index, o2.index);
		}); 
		
		M = Integer.parseInt(br.readLine());
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");

			int query = Integer.parseInt(st.nextToken());
			if(query == 1) {
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				one.add(new int[] {i, v});
			} else {
				int k = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				
				two.offer(new Info(k, i, j, m));
			}
		}
		
		while(!two.isEmpty() && two.peek().k == 0) {
			Info info = two.poll();
			long sum = tree.sum(1, 1, N, info.i, info.j);
			
			answer.offer(new Index(info.index, sum));
		}
		
		for(int k = 0; k < one.size(); k++) {
			int[] query = one.get(k);
			tree.update(1, 1, N, query[0], query[1]);
			
			while(!two.isEmpty() && two.peek().k == k + 1) {
				Info info = two.poll();
				long sum = tree.sum(1, 1, N, info.i, info.j);
				
				answer.offer(new Index(info.index, sum));
			}
		}
		
		while(!answer.isEmpty()) {
			sb.append(answer.poll().sum).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}