import java.io.*;
import java.util.*;

class Info implements Comparable<Info> {
    int x;
    int y;
    int z; // (x, y) 까지 소비한 체력

    public Info(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int compareTo(Info o) {
        return Integer.compare(z, o.z);
    }
}
public class Main {
    static int N, M;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        int x = 0, y = 0; // 도도 초기 위치
        
        for(int i = 0; i < N; i++) {
            String temp = br.readLine();

            for(int j = 0; j < M; j++) {
                arr[i][j] = temp.charAt(j);

                if(arr[i][j] == 'C') {
                    x = i;
                    y = j;
                }
            }
        }

        int answer = solution(x, y);
        if(answer == -1) {
            System.out.println("dodo sad");
        } else {
            System.out.println(answer);
        }
    }

    private static int solution(int x, int y) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int[][] visited = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        pq.offer(new Info(x, y, 0));
        visited[x][y] = 0;

        while(!pq.isEmpty()) {
            Info now = pq.poll();
            x = now.x;
            y = now.y;
            int z = now.z;

            if(arr[x][y] == 'E') { // 탈출구
                return z;
            }

            if(arr[x][y] == 'D') { // 강아지
                continue;
            }

            if(arr[x][y] == 'X') { // 아래가 뚫려있는 공간
                int nx = x + 1, nz = z + 10;

                while(arr[nx][y] == 'X') {
                    nx++;
                }

                if(visited[nx][y] > nz) {
                    pq.offer(new Info(nx, y, nz));
                    visited[nx][y] = nz;
                }

                continue;
            }

            if(arr[x][y] == 'L') { // 사다리
                int nx = x - 1, nz = z + 5;

                if(nx >= 0 && visited[nx][y] > nz) {
                    pq.offer(new Info(nx, y, nz));
                    visited[nx][y] = nz;
                }
            }

            if(x + 1 < N && arr[x + 1][y] == 'L') { // 아래가 사다리
                int nx = x + 1, nz = z + 5;

                if(nx < N && visited[nx][y] > nz) {
                    pq.offer(new Info(nx, y, nz));
                    visited[nx][y] = nz;
                }
            }

            int ny = y - 1, nz = z + 1; // 왼쪽
            if(ny >= 0 && visited[x][ny] > nz) {
                pq.offer(new Info(x, ny, nz));
                visited[x][ny] = nz;
            }

            ny = y + 1; // 오른쪽
            if(ny < M && visited[x][ny] > nz) {
                pq.offer(new Info(x, ny, nz));
                visited[x][ny] = nz;
            }
        }

        return -1;
    }
}
