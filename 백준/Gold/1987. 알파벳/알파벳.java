import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] arr;
    static int answer;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for(int r = 0; r < R; r++) {
            String temp = br.readLine();

            for(int c = 0; c < C; c++) {
                arr[r][c] = temp.charAt(c);
            }
        }

        int visited = 0;
        int alphabet = arr[0][0] - 'A';
        dfs(0, 0, visited | (1 << alphabet), 1);

        System.out.println(answer);
    }

    private static void dfs(int x, int y, int visited, int cnt) {
        answer = Math.max(answer, cnt);

        for(int d = 0; d < dir.length; d++) {
            int nx = x + dir[d][0], ny = y + dir[d][1];

            if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            int alphabet = arr[nx][ny] - 'A';
            if((visited & (1 << alphabet)) != 0) {
                continue;
            }

            dfs(nx, ny, visited | (1 << alphabet), cnt + 1);
        }
    }
}
