import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        List<int[]> availableCombs = initCombs(n);
        
        for (int i = 0; i < q.length; i++) {
            int[] attempt = q[i];
            int a = ans[i];
            
            availableCombs = availableCombs.stream()
                    .filter((comb) -> {
                        int matchCount = 0;
                        for (int j = 0; j < attempt.length; j++) {
                            for (int k = 0; k < comb.length; k++) {
                                if (attempt[j] == comb[k]) {
                                    matchCount++;
                                    break;
                                }
                            }
                        }
                        return matchCount == a;
                    })
                    .collect(Collectors.toList());
        }
        return availableCombs.size();
    }
    
    List<int[]> initCombs(int n) {
        List<int[]> combs = new LinkedList<>();
        for (int i1 = 1; i1 <= n - 4; i1++) {
            for (int i2 = i1 + 1; i2 <= n - 3; i2++) {
                for (int i3 = i2 + 1; i3 <= n - 2; i3++) {
                    for (int i4 = i3 + 1; i4 <= n - 1; i4++) {
                        for (int i5 = i4 + 1; i5 <= n; i5++) {
                            combs.add(new int[] { i1, i2, i3, i4, i5 });
                        }
                    }
                }
            }
        }
        return combs;
    }
}