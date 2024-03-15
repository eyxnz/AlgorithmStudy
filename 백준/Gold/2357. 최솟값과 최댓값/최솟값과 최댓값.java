import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SegmentTree {
	int[] maxTree; // 최댓값 저장
	int[] minTree; // 최솟값 저장
	int treeSize; // 트리 크기
	
	public SegmentTree(int arrSize) {
		int h = (int)Math.ceil(Math.log(arrSize) / Math.log(2));
		treeSize = (int)Math.pow(2, h + 1);
		maxTree = new int[treeSize];
		minTree = new int[treeSize];
	}
	
	public void init(int[] arr, int node, int start, int end) {
		if(start == end) { // 리프 노드
			maxTree[node] = arr[start];
			minTree[node] = arr[start];
			return;
		}
		
		init(arr, node * 2, start, (start + end) / 2);
		init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
		maxTree[node] = maxTree[node * 2] > maxTree[node * 2 + 1] ? maxTree[node * 2] : maxTree[node * 2 + 1];
		minTree[node] = minTree[node * 2] > minTree[node * 2 + 1] ? minTree[node * 2 + 1] : minTree[node * 2];
	}
	
	public int[] get(int node, int start, int end, int left, int right) {
		if(right < start || end < left) { // 범위 밖
			return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
		}
		
		if(left <= start && end <= right) {
			return new int[] {minTree[node], maxTree[node]};
		}
		
		int[] a = get(node * 2, start, (start + end) / 2, left, right);
		int[] b = get(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		int min = a[0] > b[0] ? b[0] : a[0];
		int max = a[1] > b[1] ? a[1] : b[1];
		return new int[] {min, max};
	}
}

public class Main {
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree tree = new SegmentTree(N);
		tree.init(arr, 1, 1, N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int[] answer = tree.get(1, 1, N, a, b);
			sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}