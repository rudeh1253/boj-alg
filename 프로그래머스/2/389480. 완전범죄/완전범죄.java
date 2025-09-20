import java.util.*;

class Solution {
    
    /*
    
    */
    
    public int solution(int[][] info, int n, int m) {
        final int INF = 100000000;
        
        int[][] table = new int[info.length + 1][];
        
        for (int i = 0; i < table.length; i++) {
            table[i] = new int[m];
            Arrays.fill(table[i], INF);
        }
        
        table[0][0] = 0;
        
        for (int i = 1; i < table.length; i++) {
            int prev = i - 1;
            
            int a = info[prev][0];
            int b = info[prev][1];
            
            for (int j = 0; j < table[prev].length; j++) {
                if (j + b < table[i].length) {
                    table[i][j + b] = Math.min(table[i][j + b], table[prev][j]);
                }
                
                if (table[prev][j] + a < n) {
                    table[i][j] = Math.min(table[i][j], table[prev][j] + a);
                }
            }
        }
        
        int min = INF;
        
        for (int i : table[table.length - 1]) {
            min = Math.min(min, i);
            // min += i;
        }
        
        return min == INF ? -1 : min;
    }
}