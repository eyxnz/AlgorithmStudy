import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Info implements Comparable<Info> {
	int a;
	int b;
	
	public Info(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Info o) {
		return Integer.compare(a, o.a); // a 작은 순
	}
}

public class Main {
	static int N;
	static List<Info> list;
	
	static int size;
	static int[] index; // LIS 인덱스 저장
	static int[] lis; // 앞에 위치한 수의 인덱스 저장
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.add(new Info(a, b));
		}
		Collections.sort(list);
		
		index = new int[N];
		lis = new int[N];
		
		for(int i = 0; i < N; i++) {
			int b = list.get(i).b;
			
			if(size == 0) {
				index[size++] = i;
				continue;
			}
			
			if(list.get(index[size - 1]).b < b) {
				index[size] = i;
				lis[i] = index[size - 1];
				size++;
				continue;
			}
			
			if(list.get(index[0]).b > b) {
				index[0] = i;
				continue;
			}
			
			int left = 0, right = size - 1;
			while(right - left > 1) {
				int mid = (left + right) / 2;
				
				if(list.get(index[mid]).b < b) {
					left = mid;
				} else {
					right = mid;
				}
			}
			
			index[right] = i;
			lis[i] = index[right - 1];
		}
		
		System.out.println(N - size);

		set = new HashSet<>();
		int idx = index[size - 1];
		for(int i = 0; i < size; i++) {
			set.add(idx);
			idx = lis[idx];
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(set.contains(i)) {
				continue;
			}
			
			sb.append(list.get(i).a).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}