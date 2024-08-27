import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        boolean[] visited = new boolean[N];
        int answer = 0;
;        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == 'H') {
                continue;
            }

            int left = Math.max(i - K, 0);
            int right = Math.min(i + K, N - 1);
            for(int j = left; j <= right; j++) {
                if(str.charAt(j) == 'P') {
                    continue;
                }

                if(visited[j]) {
                    continue;
                }

                answer++;
                visited[j] = true;
                break;
            }
        }

        System.out.println(answer);
    }
}
