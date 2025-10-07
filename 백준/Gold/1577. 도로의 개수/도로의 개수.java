import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLine[0];
        int m = firstLine[1];

        int k = Integer.parseInt(br.readLine());

        int[][] constructionZone = new int[k][];
        for (int i = 0; i < k; i++) {
            constructionZone[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(new Main().solution(n, m, k, constructionZone));
    }

    long solution(int n, int m, int c, int[][] constructionZone) {
        Map<Coord, Long> table = new HashMap<>();
        table.put(new Coord(n, m), 1L);

        Set<Edge> constructionZoneSet = new HashSet<>();
        for (int[] pair : constructionZone) {
            constructionZoneSet.add(new Edge(new Coord(pair[0], pair[1]), new Coord(pair[2], pair[3])));
        }

        return dp(new Coord(0, 0),
                n,
                m,
                constructionZoneSet,
                table);
    }

    long dp(Coord coord, int n, int m, Set<Edge> constructionZones, Map<Coord, Long> table) {
        if (table.containsKey(coord)) {
            return table.get(coord);
        }

        Coord horizontalNext = new Coord(coord.horizontal + 1, coord.vertical);
        Coord verticalNext = new Coord(coord.horizontal, coord.vertical + 1);

        Edge one = new Edge(coord, verticalNext);
        Edge two = new Edge(coord, horizontalNext);

        long sum = 0;
        if (!constructionZones.contains(one) && verticalNext.vertical <= m) {
            sum += dp(verticalNext, n, m, constructionZones, table);
        }

        if (!constructionZones.contains(two) && horizontalNext.horizontal <= n) {
            sum += dp(horizontalNext, n, m, constructionZones, table);
        }

        table.put(coord, sum);

        return sum;
    }

    static class Edge {
        Coord coord1;
        Coord coord2;

        Edge(Coord coord1, Coord coord2) {
            this.coord1 = coord1;
            this.coord2 = coord2;
        }

        @Override
        public boolean equals(Object obj) {
            Edge target = (Edge) obj;
            return this.coord1.equals(target.coord1) && this.coord2.equals(target.coord2)
                    || this.coord1.equals(target.coord2) && this.coord2.equals(target.coord1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.coord1, this.coord2) + Objects.hash(this.coord2, this.coord1);
        }
    }

    static class Coord {
        int horizontal;
        int vertical;

        Coord(int horizontal, int vertical) {
            this.vertical = vertical;
            this.horizontal = horizontal;
        }

        @Override
        public boolean equals(Object obj) {
            Coord target = (Coord) obj;
            return this.vertical == target.vertical
                    && this.horizontal == target.horizontal;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.vertical, this.horizontal);
        }
    }
}
