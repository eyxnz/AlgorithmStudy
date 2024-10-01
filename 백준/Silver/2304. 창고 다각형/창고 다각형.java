import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int MAX = 1_000;
        int[] height = new int[MAX + 1];

        int mx = 0, my = 0; // 가장 높이가 높은 기둥 정보
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            height[x] = y;

            if(my < y) {
                mx = x;
                my = y;
            }
        }

        int answer = my;

        // 가장 높이가 높은 기둥 기준 왼쪽
        int sx = 0, sy = 0;
        for(int x = 1; x <= mx; x++) {
            if(height[x] == 0) {
                continue;
            }

            if(sx == 0) {
                sx = x;
                sy = height[x];
                continue;
            }

            if(sy <= height[x]) {
                answer += (x - sx) * sy;

                sx = x;
                sy = height[x];
            }
        }

        // 가장 높이가 높은 기둥 기준 오른쪽
        sx = 0;
        sy = 0;
        for(int x = MAX; x >= mx; x--) {
            if(height[x] == 0) {
                continue;
            }

            if(sx == 0) {
                sx = x;
                sy = height[x];
                continue;
            }

            if(sy <= height[x]) {
                answer += (sx - x) * sy;

                sx = x;
                sy = height[x];
            }
        }

        System.out.println(answer);
    }
}
