import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N; // 사람의 수 (1 <= N <= 50)
	static int M; // 파티의 수 (1 <= N <= 50)
	
	static List<Integer>[] party; // 파티
	static int[] parent; // 진실을 아는 사람과 같은 집합이라면 부모를 0으로 세팅
	
	static int answer; // 과장된 이야기를 할 수 있는 파티 개수의 최댓값
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		parent = new int[N + 1];
		for(int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int cnt = Integer.parseInt(st.nextToken());
		for(int i = 0; i < cnt; i++) {
			int person = Integer.parseInt(st.nextToken());
			parent[person] = 0; // 진실을 아는 사람의 부모를 0 로 세팅
		}
		
		party = new ArrayList[M];
		for(int m = 0; m < M; m++) {
			party[m] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine(), " ");
			cnt = Integer.parseInt(st.nextToken()); // 해당 파티에 오는 사람의 수
			party[m].add(Integer.parseInt(st.nextToken())); // 무조건 한 명 이상은 파티를 옴
			for(int i = 1; i < cnt; i++) {
				int person = Integer.parseInt(st.nextToken()); // 해당 파티에 오는 사람의 번호
				party[m].add(person);
				
				unionParent(party[m].get(i - 1), party[m].get(i)); // 이전 사람과 지금 사람을 같은 집합으로
			}
		}
		
		for(int m = 0; m < M; m++) { // 각 파티 확인
			boolean flag = true;
			
			int size = party[m].size();
			for(int i = 0; i < size; i++) {
				if(findParent(party[m].get(i)) == 0) { // 해당 파티에서는 거짓말을 할 수 없음
					flag = false;
					break;
				}
			}
			
			if(flag) { // 해당 파티에 진실을 아는 사람이 없다면 거짓말 가능
				answer++;
			}
		}
		
		System.out.println(answer);
	}

	private static void unionParent(int a, int b) {
		a = findParent(a);
		b = findParent(b);
		
		if(a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	private static int findParent(int x) {
		if(x != parent[x]) {
			return findParent(parent[x]);
		}
		return parent[x];
	}
}