import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int x = 0, y = 0; // 민식이 현재 위치
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);

                if (map[i][j] == '0') {
                    x = i;
                    y = j;
                }
            }
        }

        System.out.println(bfs(x, y));
    }

    private static int bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        int[][][] visited = new int[N][M][1 << 6]; // 해당 열쇠 리스트를 가지고 방문했을 때, 이동 횟수의 최솟값
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        queue.add(new int[]{sx, sy, 0});
        visited[sx][sy][0] = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], z = now[2];

            if (map[x][y] == '1') {
                return visited[x][y][z];
            }

            for (int d = 0; d < dir.length; d++) {
                int nx = x + dir[d][0], ny = y + dir[d][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) { // 범위 밖
                    continue;
                }

                if (map[nx][ny] == '#') { // 벽
                    continue;
                }

                if (visited[nx][ny][z] != -1) { // 이미 해당 열쇠 리스트를 가지고 방문
                    continue;
                }

                if (map[nx][ny] == 'a' || map[nx][ny] == 'b' || map[nx][ny] == 'c' || map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f') { // 열쇠
                    int key = map[nx][ny] - 'a';
                    int nz = z | (1 << key);

                    queue.offer(new int[]{nx, ny, nz});
                    visited[nx][ny][nz] = visited[x][y][z] + 1;
                } else if (map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C' || map[nx][ny] == 'D' || map[nx][ny] == 'E' || map[nx][ny] == 'F') { // 문
                    int key = map[nx][ny] - 'A';

                    if ((z & (1 << key)) == 0) {
                        continue;
                    }

                    queue.offer(new int[]{nx, ny, z});
                    visited[nx][ny][z] = visited[x][y][z] + 1;
                } else { // 그 외
                    queue.offer(new int[]{nx, ny, z});
                    visited[nx][ny][z] = visited[x][y][z] + 1;
                }
            }
        }

        return -1;
    }
}
