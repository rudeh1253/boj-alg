import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        long answer = new Main().solution(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        System.out.println(answer);
    }

    long solution(int[] students) {
        Arrays.sort(students);

        long count = 0;
        for (int i = 0; i < students.length - 2; i++) {
            int student0 = students[i];

            int aPointer = i + 1;
            int bPointer = students.length - 1;

            while (aPointer < bPointer) {
                int sum = student0 + students[aPointer] + students[bPointer];

                if (sum > 0) {
                    bPointer--;
                } else if (sum < 0) {
                    aPointer++;
                } else {
                    if (students[aPointer] == students[bPointer]) {
                        long n = bPointer - aPointer + 1;
                        count += n * (n - 1) / 2;
                        break;

                    } else {
                        int fromA = getConsecutive(aPointer, 1, students);
                        int fromB = getConsecutive(bPointer, -1, students);
                        count += fromA * fromB;
                        
                        aPointer += fromA;
                        bPointer -= fromB;
                    }
                }
            }
        }

        return count;
    }

    int getConsecutive(int idx, int incr, int[] students) {
        int first = students[idx];
        int count = 0;
        while (idx < students.length && idx >= 0 && students[idx] == first) {
            count++;
            idx += incr;
        }
        return count;
    }
}