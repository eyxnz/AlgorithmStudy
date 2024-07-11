import java.io.*;
import java.util.*;

class Info implements Comparable<Info> {
    int one;
    int two;
    double dist;

    public Info(int one, int two, double dist) {
        this.one = one;
        this.two = two;
        this.dist = dist;
    }

    @Override
    public int compareTo(Info o) {
        return Double.compare(dist, o.dist);
    }
}

public class Main {
    static int N;
    static int[] parent;
    static double[][] stars;
    static PriorityQueue<Info> pq;

    static double answer;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        stars = new double[N][2];

        pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars[i][0] = x;
            stars[i][1] = y;
        }

        for(int one = 0; one < N - 1; one++) {
            for(int two = one + 1; two < N; two++) {
                double dist = Math.sqrt(Math.pow(stars[one][0] - stars[two][0], 2) + Math.pow(stars[one][1] - stars[two][1], 2));

                pq.offer(new Info(one, two, dist));
            }
        }

        while(!pq.isEmpty()) {
            Info info = pq.poll();

            if(!unionParent(info.one, info.two)) {
                continue;
            }

            answer += info.dist;
        }

        System.out.printf("%.2f", answer);
    }

    private static boolean unionParent(int one, int two) {
        one = findParent(one);
        two = findParent(two);

        if(one == two) {
            return false;
        }

        if(one < two) {
            parent[two] = one;
        } else {
            parent[one] = two;
        }

        return true;
    }

    private static int findParent(int x) {
        if(x == parent[x]) {
            return parent[x];
        }

        return parent[x] = findParent(parent[x]);
    }
}
