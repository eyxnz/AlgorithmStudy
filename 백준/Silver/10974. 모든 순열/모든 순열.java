import java.io.*;

public class Main {
    static int N;
    static int[] nums;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        visited = new boolean[N + 1];
        sb = new StringBuilder();

        perm(1);

        System.out.print(sb);
    }

    private static void perm(int depth) {
        if(depth == N + 1) {
            for(int i = 1; i < N + 1; i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for(int i = 1; i < N + 1; i++) {
            if(visited[i]) {
                continue;
            }

            nums[depth] = i;
            visited[i] = true;

            perm(depth + 1);

            visited[i] = false;
        }
    }
}
