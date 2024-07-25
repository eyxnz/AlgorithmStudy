import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x;
    int y;
    int cost; // 잃은 루피

    public Point(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(cost, o.cost);
    }
}

public class Main {
    static int N;
    static int[][] arr;
    static int index = 1;

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            if(N == 0) {
                break;
            }

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(index).append(": ").append(dijkstra()).append("\n");

            index++;
        }

        System.out.print(sb.toString());
    }

    private static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        pq.offer(new Point(0, 0, arr[0][0]));
        dist[0][0] = arr[0][0];

        while(!pq.isEmpty()) {
            Point now = pq.poll();
            int x = now.x, y = now.y, cost = now.cost;

            if(dist[x][y] < cost) {
                continue;
            }

            for(int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int ncost = cost + arr[nx][ny];

                if(dist[nx][ny] <= ncost) {
                    continue;
                }

                pq.offer(new Point(nx, ny, ncost));
                dist[nx][ny] = ncost;
            }
        }

        return dist[N - 1][N - 1];
    }
}
