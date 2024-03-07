import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	long[] tree;
	int treeSize;
	static int DIV = 1000000007;
	
	public SegmentTree(int arrSize) {
		int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
		treeSize = (int)Math.pow(2, h + 1); // 트리 높이
		tree = new long[treeSize];
	}
	
	public void init(long[] arr, int node, int start, int end) {
		if(start == end) { // 리프 노드
			tree[node] = arr[start];
			return;
		}
		
		init(arr, node * 2, start, (start + end) / 2); // 왼쪽
		init(arr, node * 2 + 1, (start + end) / 2 + 1, end); // 오른쪽
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % DIV;
	}
	
	public void update(int node, int start, int end, int index, long val) {
		if(index < start || index > end) { // 범위 밖
			return;
		}
		
		if(start == end) { // 리프 노드
			tree[node] = val;
			return;
		}
		
		update(node * 2, start, (start + end) / 2, index, val); // 왼쪽
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, val); // 오른쪽
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % DIV;
	}
	
	public long multiply(int node, int start, int end, int left, int right) {
		if(left > end || right < start) { // 범위 밖
			return 1;
		}
		
		if(left <= start && end <= right) { // 배열의 누적 곱
			return tree[node];
		}
		
		long x = multiply(node * 2, start, (start + end) / 2, left, right) % DIV;
		long y = multiply(node * 2 + 1, (start + end) / 2 + 1, end, left, right) % DIV;
		return (x * y) % DIV;
	}
}

public class Main {
	static int N;
	static int M;
	static int K;
	static long[] arr; // 인덱스 1부터 시작
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		for(int i = 1; i < N + 1; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				tree.update(1, 1, N, b, c);
				arr[b] = c;
			} else { // a == 2
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				sb.append(tree.multiply(1, 1, N, b, c)).append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}