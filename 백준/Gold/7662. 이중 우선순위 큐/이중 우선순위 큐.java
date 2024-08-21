import java.io.*;
import java.util.*;

class Num implements Comparable<Num> {
    int index;
    int num;

    public Num(int index, int num) {
        this.index = index;
        this.num = num;
    }

    @Override
    public int compareTo(Num o) {
        if(Integer.compare(num, o.num) == 0) {
            return Integer.compare(index, o.index);
        }
        return Integer.compare(num, o.num); // 오름차순 정렬
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            TreeSet<Num> nums = new TreeSet<>();
            int size = 0;

            int K = Integer.parseInt(br.readLine());

            for(int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");

                String operator = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if(operator.equals("D")) {
                    if(size == 0) {
                        continue;
                    }

                    if(num == 1) { // 최댓값 삭제
                        nums.pollLast();
                    } else { // 최솟값 삭제
                        nums.pollFirst();
                    }

                    size--;
                } else { // "I"
                    nums.add(new Num(k, num));
                    size++;
                }
            }

            if(size == 0) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(nums.last().num).append(" ").append(nums.first().num).append("\n");
            }
        }

        System.out.print(sb);
    }
}
