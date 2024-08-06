import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;
        int index = 1;
        long dist = 0;

        // ->
        while(true) {
            if(K < dist + arr[index]) {
                flag = true;
                break;
            }

            dist += arr[index++];

            if(index == N + 1) {
                break;
            }
        }

        if(flag) {
            System.out.println(index);
            return;
        }

        --index;

        if(K == dist) {
            System.out.println(index);
            return;
        }

        while(true) {
            if(K < dist + arr[index]) {
                break;
            }

            dist += arr[index--];
        }

        System.out.println(index);
    }
}
