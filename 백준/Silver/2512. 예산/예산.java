import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        int total = 0, max = 0; // 예산요청 총 액, 예산요청 최댓값
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            total += arr[i];
            max = Math.max(max, arr[i]);
        }

        M = Integer.parseInt(br.readLine());

        if(total <= M) {
            answer = max;
        } else {
            int left = 1, right = max, mid = 0;
            while(left <= right) {
                mid = (left + right) / 2;

                if(check(mid)) {
                    left = mid + 1;
                    answer = mid;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int limit) {
        int sum = 0;

        for(int i = 0; i < N; i++) {
            if(arr[i] <= limit) {
                sum += arr[i];
            } else {
                sum += limit;
            }
        }

        return sum <= M;
    }
}
