import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static String[] alphabet;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabet = new String[C];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken();
        }
        Arrays.sort(alphabet);

        // C개 중 L개 뽑기
        sol(0, 0, "", 0, 0);

        System.out.println(sb);
    }

    private static void sol(int depth, int start, String target, int a, int b) {
        if(depth == L) {
            if(a < 1 || b < 2) {
                return;
            }

            sb.append(target).append("\n");
            return;
        }

        for(int i = start; i < C; i++) {
            String temp = alphabet[i];

            if(temp.equals("a") || temp.equals("e") || temp.equals("i") || temp.equals("o") || temp.equals("u")) {
                sol(depth + 1, i + 1, target + temp, a + 1, b);
            } else {
                sol(depth + 1, i + 1, target + temp, a, b + 1);
            }
        }
    }
}
