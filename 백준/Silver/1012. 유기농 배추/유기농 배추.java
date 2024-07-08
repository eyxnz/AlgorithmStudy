import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int M, N, K;
    static boolean[][] arr;
    static boolean[][] visited;

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new boolean[N][M];
            visited = new boolean[N][M];

            for(int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                arr[x][y] = true;
            }

            int answer = 0;
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    if(!arr[x][y]) {
                        continue;
                    }

                    if(visited[x][y]) {
                        continue;
                    }

                    bfs(x, y);
                    answer++;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];

            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if(!arr[nx][ny]) {
                    continue;
                }

                if(visited[nx][ny]) {
                    continue;
                }

                queue.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}
