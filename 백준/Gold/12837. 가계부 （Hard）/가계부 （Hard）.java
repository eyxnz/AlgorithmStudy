import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	int treeSize;
	long[] tree; // 이전 값, 지금 값
	
	public SegmentTree(int arrSize) {
		treeSize = arrSize * 4;
		tree = new long[treeSize];
	}
	
	public void update(int node, int start, int end, int index, int value) {
		if(index < start || end < index) {
			return;
		}
		
		if(start == end) {
			tree[node] += value;
			return;
		}
		
		update(node * 2, start, (start + end) / 2, index, value);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, value);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public long sum(int node, int start, int end, int left, int right) {
		if(right < start || end < left) {
			return 0;
		}
		
		if(left <= start && end <= right) {
			return tree[node];
		}
		
		long a = sum(node * 2, start, (start + end) / 2, left, right);
		long b = sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return a + b;
	}
}

public class Main {
	static int N, Q;
	static SegmentTree tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new SegmentTree(N);
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int op = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int x = Integer.parseInt(st.nextToken());
				tree.update(1, 1, N, p, x);
			} else {
				int q = Integer.parseInt(st.nextToken());
				sb.append(tree.sum(1, 1, N, p, q)).append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}
