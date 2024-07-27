import java.io.*;
import java.util.*;

class CCTV {
    int x;
    int y;
    int num;

    public CCTV(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}

public class Main {
    static int N, M;
    static int[][] arr;
    static List<CCTV> cctv;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우
    static int[][][] cctvDir = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}},
            {{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
            {{0, 1, 2, 3}}
    };

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        cctv = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 0 || arr[i][j] == 6) {
                    continue;
                }

                cctv.add(new CCTV(i, j, arr[i][j]));
            }
        }
        
        sol(0, arr);

        System.out.println(answer);
    }

    private static void sol(int depth, int[][] arr) {
        if(depth == cctv.size()) { // 모든 CCTV의 방향 선택 완료
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(arr[i][j] != 0) {
                        continue;
                    }

                    cnt++;
                }
            }

            answer = Math.min(answer, cnt);

            return;
        }

        CCTV now = cctv.get(depth);
        int x = now.x, y = now.y, num = now.num;

        for(int t = 0; t < cctvDir[num].length; t++) {
            int[][] temp = new int[N][M];
            for(int i = 0; i < N; i++) {
                temp[i] = arr[i].clone();
            }

            for(int k = 0; k < cctvDir[num][t].length; k++) {
                int d = cctvDir[num][t][k]; // 해당 방향으로 감시 시작
                int nx = x + dir[d][0], ny = y + dir[d][1];

                while(true) {
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) { // 범위 밖
                        break;
                    }
                    
                    if(arr[nx][ny] == 6) { // 벽
                        break;
                    }

                    temp[nx][ny] = -1; // 감시

                    nx += dir[d][0];
                    ny += dir[d][1];
                }
            }

            sol(depth + 1, temp);
        }
    }
}
