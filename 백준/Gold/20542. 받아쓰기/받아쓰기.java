import java.io.*;
import java.util.*;

public class Main {

    static String myAnswer, correctAnswer;
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        myAnswer = br.readLine().trim();
        correctAnswer = br.readLine().trim();

        System.out.println(recur(0, 0));
    }

    static int recur(int correctIdx, int myIdx) {
        if (myIdx >= myAnswer.length()) {
            return Math.max(correctAnswer.length() - correctIdx, 0);
        }
        if (correctIdx >= correctAnswer.length()) {
            return Math.max(myAnswer.length() - myIdx, 0);
        }

        String key = correctIdx + "," + myIdx;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char myChar = myAnswer.charAt(myIdx);
        char correctChar = correctAnswer.charAt(correctIdx);

        if (myChar == correctChar
                || (myChar == 'i' && (correctChar == 'i' || correctChar == 'j' || correctChar == 'l'))
                || (myChar == 'v' && (correctChar == 'v' || correctChar == 'w'))) {
            int result = recur(correctIdx + 1, myIdx + 1);
            memo.put(key, result);
            return result;
        }

        int insertCase = recur(correctIdx + 1, myIdx) + 1;
        int deleteCase = recur(correctIdx, myIdx + 1) + 1;
        int updateCase = recur(correctIdx + 1, myIdx + 1) + 1;

        int minScore = Math.min(insertCase, Math.min(deleteCase, updateCase));
        memo.put(key, minScore);
        return minScore;
    }
}
