import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int factory1 = Integer.parseInt(st.nextToken());
        int factory2 = Integer.parseInt(st.nextToken());


        System.out.println(solution(n, graph, factory1, factory2));
    }

    public static int solution(int n, Map<Integer, List<Edge>> graph, int start, int end) {
        Map<Integer, Integer> maxCost = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (i != start) {
                maxCost.put(i, Integer.MIN_VALUE);
            } else {
                maxCost.put(i, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0])); // max-heap
        pq.offer(new int[] { 0, start });

        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[1];

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            for (Edge next : graph.get(node)) {
                int transportable = Math.min(next.cost, maxCost.get(node));
                if (maxCost.get(next.to) < transportable) {
                    maxCost.put(next.to, transportable);
                    pq.offer(new int[] { transportable, next.to });
                }
            }
        }

        return maxCost.get(end);
    }
}
