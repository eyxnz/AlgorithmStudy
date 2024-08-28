import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        int[] len = new int[N - 1]; // i와 i + 1 사이의 거리
        for(int i = 0; i < N - 1; i++) {
            len[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] cost = new int[N]; // i의 주유소의 리터당 가격
        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N - 1; i++) {
            if(min > cost[i]) {
                min = cost[i];
            }

            answer += min * len[i];
        }

        System.out.println(answer);
    }
}
