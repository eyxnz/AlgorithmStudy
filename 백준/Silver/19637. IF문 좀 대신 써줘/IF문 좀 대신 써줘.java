import java.io.*;
import java.util.*;

class Title {
    String name;
    int power;

    public Title(String name, int power) {
        this.name = name;
        this.power = power;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Title[] titles = new Title[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());

            titles[i] = new Title(name, power);
        }

        for(int i = 0; i < M; i++) {
            int power = Integer.parseInt(br.readLine());

            if(power <= titles[0].power) {
                sb.append(titles[0].name).append("\n");
                continue;
            }

            int left = 0, right = N - 1;
            while(left < right) {
                int mid = (left + right) / 2;

                if(power <= titles[mid].power) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            sb.append(titles[left].name).append("\n");
        }

        System.out.print(sb);
    }
}
