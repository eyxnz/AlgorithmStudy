import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] people; // 각 나라에 사는 인구수
    static int[][] temp;
    static int answer;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        people = new int[N][N];
        temp = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            int move = 0;
            boolean[][] area = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(area[i][j]) {
                        continue;
                    }

                    move += bfs(i, j, area);
                }
            }
            
            if(move == 0) { // 인구 이동 없음
                break;
            }

            people = temp;
            answer++;
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y, boolean[][] area) {
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> sameArea = new LinkedList<>(); // 연합에 속한 나라
        int total = 0; // 연합의 인구수

        queue.offer(new int[] {x, y});
        sameArea.offer(new int[] {x, y});
        total += people[x][y];
        area[x][y] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];

            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if(area[nx][ny]) {
                    continue;
                }

                int diff = Math.abs(people[x][y] - people[nx][ny]);

                if(diff < L || diff > R) {
                    continue;
                }

                queue.offer(new int[] {nx, ny});
                sameArea.offer(new int[] {nx, ny});
                total += people[nx][ny];
                area[nx][ny] = true;
            }
        }
        
        if(sameArea.size() == 1) {
            int[] country = sameArea.poll();
            temp[country[0]][country[1]] = people[country[0]][country[1]];

            return 0;
        }
        
        total = total / sameArea.size(); // 연합을 이루고 있는 각 칸의 인구수
        while(!sameArea.isEmpty()) {
            int[] country = sameArea.poll();
            temp[country[0]][country[1]] = total;
        }

        return 1;
    }
}
