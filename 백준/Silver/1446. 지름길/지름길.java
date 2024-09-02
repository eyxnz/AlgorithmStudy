import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}

public class Main {
    static int N, D;
    static List<Edge>[] shortCuts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        shortCuts = new ArrayList[D + 1];
        for(int i = 0; i < D + 1; i++) {
            shortCuts[i] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(s > D || e > D) {
                continue;
            }

            shortCuts[s].add(new Edge(e, d));
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Edge(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int node = now.node;
            int cost = now.cost;

            if(dist[node] < cost) {
                continue;
            }

            for(Edge next : shortCuts[node]) {
                int nextNode = next.node;
                int nextCost = cost + next.cost;

                if(dist[nextNode] <= nextCost) {
                    continue;
                }

                pq.offer(new Edge(nextNode, nextCost));
                dist[nextNode] = nextCost;
            }

            int nextNode = node + 1;
            int nextCost = cost + 1;

            if(nextNode > D) {
                continue;
            }

            if(dist[nextNode] <= nextCost) {
                continue;
            }

            pq.offer(new Edge(nextNode, nextCost));
            dist[nextNode] = nextCost;
        }

        return dist[D];
    }
}
