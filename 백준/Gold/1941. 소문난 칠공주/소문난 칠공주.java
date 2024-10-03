import java.io.*;
import java.util.*;

public class Main {
    static int N = 5;
    static int[][] arr;
    static boolean[][] choice;

    static int answer;

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[N][N]; // S : 0, Y : 1
        choice = new boolean[N][N];

        for(int i = 0; i < N; i++) {
           String temp = br.readLine();

           for(int j = 0; j < N; j++) {
               char ch = temp.charAt(j);

               if(ch == 'S') {
                   arr[i][j] = 0;
               } else {
                   arr[i][j] = 1;
               }
           }
        }

        permutation(0, -1, 0, 0);

        System.out.println(answer);
    }

    private static void permutation(int depth, int start, int zero, int one) {
        if(one > 3) {
            return;
        }

        if(depth == 7) {
            int x = start / N, y = start % N;

            if(isConnected(x, y)) {
                answer++;
            }

            return;
        }

        for(int i = start + 1; i < N * N; i++) {
            int x = i / N, y = i % N;

            choice[x][y] = true;

            if(arr[x][y] == 0) {
                permutation(depth + 1, i, zero + 1, one);
            } else {
                permutation(depth + 1, i, zero, one + 1);
            }

            choice[x][y] = false;
        }
    }

    private static boolean isConnected(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int cnt = 0;

        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        cnt++;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];

            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if(visited[nx][ny]) {
                    continue;
                }

                if(!choice[nx][ny]) {
                    continue;
                }

                queue.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
                cnt++;
            }
        }

        if(cnt < 7) {
            return false;
        }
        return true;
    }
}
