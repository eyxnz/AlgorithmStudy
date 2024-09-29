import java.io.*;
import java.util.*;

public class Main {
    static int S, C;
    static int[] len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        len = new int[S];

        long answer = 0; // 모든 파의 길이의 합
        int max = 0; // 가장 긴 파의 길이
        for(int i = 0; i < S; i++) {
            len[i] = Integer.parseInt(br.readLine());

            answer += len[i];
            max = Math.max(max, len[i]);
        }
        Arrays.sort(len);

        int left = 1, right = max, limit = 0;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(check(mid)) {
                limit = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer - (long) limit * C);
    }

    public static boolean check(int limit) {
        long count = 0;

        for(int i = 0; i < S; i++) {
            count += len[i] / limit;
        }

        return count >= C;
    }
}
