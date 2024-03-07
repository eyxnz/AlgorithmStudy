import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] lis; // 최장 증가 부분 수열 (lis[index] : 길이가 index 인 LIS 중 마지막에 오는 수 저장)
	static int size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		lis = new int[N];
		Arrays.fill(lis, N + 1); // 최댓값 + 1 로 설정
		size = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		lis[size++] = Integer.parseInt(st.nextToken()); // 첫 번째 수
		
		for(int i = 1; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(lis[size - 1] < num) {
				lis[size++] = num;
				continue;
			} else if(lis[0] > num) {
				lis[0] = num;
				continue;
			}
			
			// 이분 탐색으로 num 이 들어갈 위치 찾기
			int start = 0, end = size - 1;
			while(end - start > 1) {
				int mid = (start + end) / 2;
				
				if(lis[mid] > num) {
					end = mid;
				} else {
					start = mid;
				}
			}
			lis[end] = num;
		}
		System.out.println(size);
	}
}