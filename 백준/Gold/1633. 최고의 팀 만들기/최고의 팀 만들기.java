import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> list = new ArrayList<>(1000);

        String line;
        while ((line = br.readLine()) != null) {
            list.add(Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        System.out.println(new Main().solution(list.toArray(new int[0][])));
    }

    int solution(int[][] players) {
        int[][][] map = new int[players.length][][];

        for (int i = 0; i < map.length; i++) {
            map[i] = new int[16][];
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new int[16];
                Arrays.fill(map[i][j], -1);
            }
        }

        map[0][0][0] = 0;
        map[0][1][0] = players[0][0];
        map[0][0][1] = players[0][1];

        for (int i = 1; i < players.length; i++) {
            int white = players[i][0];
            int black = players[i][1];

            int[][] prev = map[i - 1];

            for (int j = 0; j < prev.length; j++) {
                for (int k = 0; k < prev[j].length; k++) {
                    if (prev[j][k] == -1) {
                        continue;
                    }

                    map[i][j][k] = Math.max(prev[j][k], map[i][j][k]);

                    if (j < 15) {
                        map[i][j + 1][k] = Math.max(map[i][j + 1][k], prev[j][k] + white);
                    }

                    if (k < 15) {
                        map[i][j][k + 1] = Math.max(map[i][j][k + 1], prev[j][k] + black);
                    }
                }
            }
        }
        return map[map.length - 1][15][15];
    }
}