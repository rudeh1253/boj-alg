import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for (int i = 0; i < n; i ++) {
            words[i] = br.readLine().trim();
        }

        String[] answer = solution(words);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    static String[] solution(String[] words) {
        

        Map<String, Integer> order = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            order.put(words[i], i);
        }

        int max = -1;

        Arrays.sort(words);
        
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            
            int result = getCommonPrefixLength(a, b);
            if (result >= max) {
                int aIdx = order.get(a);
                int bIdx = order.get(b);

                String s = aIdx > bIdx ? b : a;
                String t = aIdx > bIdx ? a : b;

                if (result > max) {
                    set.clear();
                }
                set.add(s);
                set.add(t);
                max = result;
            }
        }
        
        List<String> inOrder = set.stream()
                .sorted((s1, s2) -> order.get(s1) - order.get(s2))
                .collect(Collectors.toList());
            
        Map<String, String> memory = new HashMap<>();
        memory.put(inOrder.get(0).substring(0, max), inOrder.get(0));
        
        for (int i = 1; i < inOrder.size(); i++) {
            String str = inOrder.get(i);
            String prefix = str.substring(0, max);
            
            if (memory.containsKey(prefix)) {
                return new String[] { memory.get(prefix), str };
            }
        }
        
        return new String[] { "", "" };
    }

    static int getCommonPrefixLength(String word1, String word2) {
        int len = 0;
        for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                break;
            }
            len++;
        }
        return len;
    }
}