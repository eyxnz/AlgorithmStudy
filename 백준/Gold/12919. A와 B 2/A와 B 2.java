import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        System.out.println(bfs(T, S));
    }

    private static int bfs(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            String current = queue.poll();

            if(current.equals(end)) {
                return 1;
            }

            if(current.length() == end.length()) {
                continue;
            }

            if(current.startsWith("B")) { // 맨 앞이 B
                String temp = new StringBuilder(current.substring(1)).reverse().toString();

                if(!visited.contains(temp)) {
                    queue.offer(temp);
                    visited.add(temp);
                }
            }

            if(current.endsWith("A")) { // 맨 뒤가 A
                String temp = current.substring(0, current.length() - 1);

                if(!visited.contains(temp)) {
                    queue.offer(temp);
                    visited.add(temp);
                }
            }
        }

        return 0;
    }
}
