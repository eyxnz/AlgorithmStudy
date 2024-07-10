import java.io.*;
import java.util.*;

public class Main {
    static int N, H;
    static int[] arr;
    static int min = Integer.MAX_VALUE, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H + 1];

        for(int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());

            if(i % 2 == 0) { // 석순
                arr[0]++;

                if(h < H) {
                    arr[h]--;
                }
            } else { // 종유석
                arr[H - h] += 1;
            }
        }

        for(int i = 1; i < H; i++) {
            arr[i] += arr[i - 1];
        }

        for(int i = 0; i < H; i++) {
            if (arr[i] < min) {
                min = arr[i];
                cnt = 1;
            } else if (arr[i] == min) {
                cnt += 1;
            }
        }

        System.out.println(min + " " + cnt);
    }
}
