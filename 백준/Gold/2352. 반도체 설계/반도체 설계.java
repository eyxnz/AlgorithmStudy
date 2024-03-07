import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] lis; // 최장 증가 부분 수열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		lis = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			lis[i] = 1; // 자기 자신 가능
		}

		int max = 1;
		for(int i = 1; i < N; i++) { // 현재 위치
			for(int j = 0; j < i; j++) { // 이전 위치
				if(arr[j] < arr[i]) { // 연결 가능
					lis[i] = Math.max(lis[i], lis[j] + 1); // 자기 자신 VS j까지 선택 + 자기 자신
				}
			}
			max = max > lis[i] ? max : lis[i];
		}

		System.out.println(max);
	}
}