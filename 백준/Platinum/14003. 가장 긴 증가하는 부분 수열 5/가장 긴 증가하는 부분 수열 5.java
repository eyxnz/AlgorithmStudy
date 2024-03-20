import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	
	static int size;
	static int[] index; // LIS 인덱스 저장
	
	static int[] lis; // LIS 저장 (현재 인덱스 앞에 오는 수의 인덱스 저장)
	
	static List<Integer> answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		size = 0;
		index = new int[N];
		lis = new int[N];
		Arrays.fill(lis, -1);
		
		for(int i = 0; i < N; i++) {
			int num = arr[i];
			
			if(size == 0) {
				index[size++] = i;
				continue;
			}
			
			if(arr[index[size - 1]] < num) {
				index[size] = i;
				lis[i] = index[size - 1];
				size++;
				continue;
			}
			
			if(arr[index[0]] >= num) {
				index[0] = i;
				continue;
			}
			
			boolean flag = true; // 중복 체크
			int left = 0, right = size - 1;
			while(right - left > 1) {
				int mid = (left + right) / 2;
				
				if(arr[index[mid]] == num) {
					flag = false;
					break;
				} else if(arr[index[mid]] > num) {
					right = mid;
				} else {
					left = mid;
				}
			}
			if(!flag) {
				continue;
			}
			
			index[right] = i;
			lis[i] = index[right - 1];
		}
		
		sb.append(size).append("\n"); // LIS 길이
	
		answer = new ArrayList<>();
		
		int idx = index[size - 1];
		for(int i = 0; i < size; i++) {
			answer.add(arr[idx]);
			idx = lis[idx];
		}
		
		for(int i = answer.size() - 1; i >= 0; i--) {
			sb.append(answer.get(i)).append(" ");
		}
		
		System.out.println(sb.toString());
	}
}