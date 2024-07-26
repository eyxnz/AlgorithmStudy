import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] cost;
    static int[][][] dp; // [현재 집 인덱스][첫 번째 집 색상][현재 집 색상]

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3][3];
        for(int i = 0; i < N; i++) {
            for(int color = 0; color < 3; color++) {
                Arrays.fill(dp[i][color], Integer.MAX_VALUE);
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int color = 0; color < 3; color++) {
                cost[i][color] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = cost[0][0];
        dp[0][1][1] = cost[0][1];
        dp[0][2][2] = cost[0][2];

        dp[1][0][1] = cost[0][0] + cost[1][1];
        dp[1][0][2] = cost[0][0] + cost[1][2];

        dp[1][1][0] = cost[0][1] + cost[1][0];
        dp[1][1][2] = cost[0][1] + cost[1][2];

        dp[1][2][0] = cost[0][2] + cost[1][0];
        dp[1][2][1] = cost[0][2] + cost[1][1];

        for(int i = 2; i < N; i++) {
            for(int color = 0; color < 3; color++) {
                dp[i][color][0] = Math.min(dp[i - 1][color][1], dp[i - 1][color][2]) + cost[i][0];
                dp[i][color][1] = Math.min(dp[i - 1][color][0], dp[i - 1][color][2]) + cost[i][1];
                dp[i][color][2] = Math.min(dp[i - 1][color][0], dp[i - 1][color][1]) + cost[i][2];
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                answer = Math.min(answer, dp[N - 1][i][j]);
            }
        }

        System.out.println(answer);
    }
}
