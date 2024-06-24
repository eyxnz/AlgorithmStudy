import java.io.*;
import java.util.*;

class Stone {
	int a;
	int b;
	int c;
	
	public Stone(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b, c);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stone other = (Stone) obj;
		return a == other.a && b == other.b && c == other.c;
	}
}

public class Main {
	static int A, B, C, MAX;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		MAX = A + B + C;
		
		if(MAX % 3 != 0) {
			System.out.println(0);
			return;
		}
		
		System.out.println(bfs(A, B, C));
	}

	private static int bfs(int a, int b, int c) {
		Queue<Stone> queue = new LinkedList<>();
		Set<Stone> visited = new HashSet<>();
		
		Stone s = new Stone(a, b, c);
		queue.offer(s);
		visited.add(s);
		
		while(!queue.isEmpty()) {
			s = queue.poll();
			a = s.a;
			b = s.b;
			c = s.c;
			
			if(a == b && b == c) {
				return 1;
			}
			
			// a b
			if(a < b) {
				s = new Stone(a + a, b - a, c);
				if(b - a > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			} else if(b < a) {
				s = new Stone(a - b, b + b, c);
				if(a - b > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			}
			
			// b c
			if(b < c) {
				s = new Stone(a, b + b, c - b);
				if(c - b > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			} else if(c < b) {
				s = new Stone(a, b - c, c + c);
				if(b - c > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			}
			
			// a c
			if(a < c) {
				s = new Stone(a + a, b, c - a);
				if(c - a > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			} else if(c < a) {
				s = new Stone(a - c, b, c + c);
				if(a - c > 0 && !visited.contains(s)) {
					queue.offer(s);
					visited.add(s);
				}
			}
		}
		
		return 0;
	}
}
