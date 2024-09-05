import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            int[] alphabet = new int[26];
            for(int i = 0; i < W.length(); i++) {
                alphabet[W.charAt(i) - 'a']++;
            }

            if(K == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            int a = Integer.MAX_VALUE, b = 0;

            for(int i = 0; i < W.length() - 1; i++) {
                if(alphabet[W.charAt(i) - 'a'] < K) {
                    continue;
                }

                int cnt = 1;
                for(int j = i + 1; j < W.length(); j++) {
                    if(W.charAt(i) == W.charAt(j)) {
                        cnt++;
                    }

                    if(cnt == K) {
                        a = Math.min(a, j - i + 1);
                        b = Math.max(b, j - i + 1);
                        break;
                    }
                }
            }

            if(a == Integer.MAX_VALUE || b == 0) {
                sb.append(-1).append("\n");
            } else {
                sb.append(a).append(" ").append(b).append("\n");
            }
        }

        System.out.println(sb);
    }
}
