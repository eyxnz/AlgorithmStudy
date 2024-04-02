import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Num implements Comparable<Num> {
	int value;
	int modulus;
	
	public Num(int value, int modulus) {
		this.value = value;
		this.modulus = modulus;
	}

	@Override
	public int compareTo(Num o) {
		if(Integer.compare(modulus, o.modulus) == 0) {
			return Integer.compare(value, o.value);
		}
		return Integer.compare(modulus, o.modulus);
	}
}

public class Main {
	static int N;
	static PriorityQueue<Num> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {
				if(pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll().value).append("\n");
				}
			} else {
				int y = x > 0 ? x : -x;
				
				pq.offer(new Num(x, y));
			}
		}
		
		System.out.println(sb.toString());
	}
}
