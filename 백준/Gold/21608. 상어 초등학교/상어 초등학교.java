import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
	int x;
	int y;
	int like; // 인접한 칸 중에서 좋아하는 학생이 있는 칸의 개수
	int blank; // 인접한 칸 중에서 비어있는 칸의 개수
	
	public Point(int x, int y, int like, int blank) {
		this.x = x;
		this.y = y;
		this.like = like;
		this.blank = blank;
	}
	
	@Override
	public int compareTo(Point o) {
		if(Integer.compare(o.like, like) == 0) {
			if(Integer.compare(o.blank, blank) == 0) {
				if(Integer.compare(x, o.x) == 0) {
					return Integer.compare(y, o.y);
				}
				
				return Integer.compare(x, o.x);
			}
			
			return Integer.compare(o.blank, blank);
		}
		
		return Integer.compare(o.like, like);
	}
}

public class Main {
	static int N;
	static int M = 4;
	static Set<Integer>[] like; // 각 학생이 좋아하는 학생들
	static int[][] students; // 자리 배치
	static boolean[][] blank; // 빈 칸
	static int answer; // 만족도의 총 합
	
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		like = new HashSet[N * N + 1];
		for(int i = 1; i < N * N + 1; i++) {
			like[i] = new HashSet<>(4);
		}
		students = new int[N][N];
		blank = new boolean[N][N];
		
		for(int t = 0; t < N * N; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int stu = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < M; i++) {
				like[stu].add(Integer.parseInt(st.nextToken()));
			}
			
			PriorityQueue<Point> pq = new PriorityQueue<>();
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < N; y++) {
					if(blank[x][y]) {
						continue;
					}
					
					int l = 0, b = 0;
					for(int d = 0; d < dir.length; d++) {
						int nx = x + dir[d][0], ny = y + dir[d][1];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}
						
						if(students[nx][ny] == 0) { // 빈 칸
							b++;
						} else if(like[stu].contains(students[nx][ny])) { // 좋아하는 학생
							l++;
						}
					}
					
					pq.add(new Point(x, y, l, b));
				}
			}
			
			Point point = pq.poll();
			students[point.x][point.y] = stu;
			blank[point.x][point.y] = true;
		}
		
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				int stu = students[x][y];
				
				int cnt = 0;
				for(int d = 0; d < dir.length; d++) {
					int nx = x + dir[d][0], ny = y + dir[d][1];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}
					
					if(like[stu].contains(students[nx][ny])) {
						cnt++;
					}
				}
				
				if(cnt == 0) {
					continue;
				}
				answer += Math.pow(10, cnt - 1);
			}
		}
		
		System.out.println(answer);
	}
}
