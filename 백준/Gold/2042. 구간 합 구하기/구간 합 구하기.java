import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	long[] tree;
	int treeSize;
	
	public SegmentTree(int arrSize) {
		int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2)); // 트리의 높이
		treeSize = (int)Math.pow(2, h + 1);
		tree = new long[treeSize];
	}
	
	public void init(long[] arr, int node, int start, int end) {
		if(start == end) { // 시작 인덱스 == 끝 인덱스 -> 리프 노드
			tree[node] = arr[start];
			return;
		}
		
		init(arr, node * 2, start, (start + end) / 2); // 왼쪽 자식 노드
		init(arr, node * 2 + 1, (start + end) / 2 + 1, end); // 오른쪽 자식 노드
		tree[node] = tree[node * 2] + tree[node * 2 + 1]; // 왼쪽 자식 노드 누적합 + 오른쪽 자식 노드 누적합
	}
	
	public void update(int node, int start, int end, int index, long diff) {
		if(index < start || end < index) { // 범위 밖
			return;
		}
		
		tree[node] += diff; // 값 변경
		
		if(start != end) { // 리프 노드가 아니라면 자식 노드도 변경
			update(node * 2, start, (start + end) / 2, index, diff); // 왼쪽 자식 노드
			update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff); // 오른쪽 자식 노드
		}
	}
	
	public long sum(int node, int start, int end, int left, int right) {
		if(left > end || right < start) { // 범위 밖
			return 0;
		}
		
		if(left <= start && end <= right) { // 전체 배열의 누적합
			return tree[node];
		}
		
		return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}
}

public class Main {
	static int N; // 수의 개수
	static int M; // 수의 변경이 일어나는 횟수
	static int K; // 구간의 합을 구하는 횟수
	static long[] arr; // 배열 -> 인덱스 1부터 시작
	
	public static void main(String[] args) throws Exception { // 배열 원소의 변경이 잦을 때의 누적합 구하기 -> 세그먼트 트리
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
				
				tree.update(1, 1, N, b, c - arr[b]);
				arr[b] = c;
			} else { // a == 2
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				sb.append(tree.sum(1, 1, N, b, c)).append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}