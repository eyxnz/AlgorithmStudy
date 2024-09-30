import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 메모장에 적은 키워드 개수
        int M = Integer.parseInt(st.nextToken()); // 가희가 블로그에 쓴 글의 개수

        Set<String> keywords = new HashSet<>(); // 키워드

        for(int i = 0; i < N; i++) {
            keywords.add(br.readLine());
        }

        for(int i = 0; i < M; i++) {
            String[] str = br.readLine().split(",");

            for(String s : str) {
                if(keywords.contains(s)) {
                    keywords.remove(s);
                }
            }

            sb.append(keywords.size()).append("\n");
        }

        System.out.print(sb);
    }
}
