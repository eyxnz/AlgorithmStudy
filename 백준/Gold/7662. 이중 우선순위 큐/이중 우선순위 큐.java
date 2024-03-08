import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Num implements Comparable<Num> {
	int num;
	int index;
	
	public Num(int num, int index) {
		this.num = num;
		this.index = index;
	}

	@Override
	public int compareTo(Num o) {
		if(Integer.compare(num, o.num) == 0) {
			return Integer.compare(index, o.index);
		}
		return Integer.compare(num, o.num);
	}
}

public class Main {
	static int T;
	static int K;
	static TreeSet<Num> set;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			set = new TreeSet<>();
			K = Integer.parseInt(br.readLine());
			
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				String op = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				
				if(op.equals("I")) { // 삽입
					set.add(new Num(n, k));
				} else { // 삭제
					if(n == -1) { // 최솟값 삭제
						set.pollFirst();
					} else { // 최댓값 삭제
						set.pollLast();
					}
				}
			}
			
			if(set.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(set.last().num).append(" ").append(set.first().num).append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}
