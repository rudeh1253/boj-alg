import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        int[] answer = new Main().solution(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        System.out.println(answer[0] + " " + answer[1]);
    }

    int[] solution(int[] liquid) {
        Arrays.sort(liquid);

        int a = liquid[0];
        int b = liquid[liquid.length - 1];
        int min = Math.abs(a + b);

        int aPointer = 0;
        int bPointer = liquid.length - 1;
        int sum = liquid[aPointer] + liquid[bPointer];

        while (aPointer < bPointer) {
            sum = liquid[aPointer] + liquid[bPointer];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                a = liquid[aPointer];
                b = liquid[bPointer];
            }

            if (sum > 0) {
                bPointer--;
            } else if (sum < 0) {
                aPointer++;
            } else {
                break;
            }
        }

        return Arrays.stream(new int[] { a, b })
                .sorted()
                .toArray();
    }
}