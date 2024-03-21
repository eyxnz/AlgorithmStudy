import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N;
	static TreeMap<Integer, Integer> map;
	
	static int size;
	static int[] length;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new TreeMap<>();
		length = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			int num = map.pollFirstEntry().getValue();
			
			if(size == 0 || length[size - 1] < num) {
				length[size++] = num;
				continue;
			}
			
			if(length[0] > num) {
				length[0] = num;
				continue;
			}
			
			int left = 0, right = size - 1;
			while(right - left > 1) {
				int mid = (left + right) / 2;
				
				if(length[mid] > num) {
					right = mid;
				} else {
					left = mid;
				}
			}
			length[right] = num;
		}
		
		System.out.println(N - size);
	}
}