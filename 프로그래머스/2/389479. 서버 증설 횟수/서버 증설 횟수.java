class Solution {
    public int solution(int[] players, int m, int k) {
        int[] serverNeeded = new int[players.length];
        
        for (int i = 0; i < serverNeeded.length; i++) {
            serverNeeded[i] = players[i] / m;
        }
        
        int serverCount = 0;
        for (int i = 0; i < serverNeeded.length; i++) {
            int servers = serverNeeded[i];
            serverCount += servers;
            for (int j = i; j < Math.min(i + k, serverNeeded.length); j++) {
                serverNeeded[j] = serverNeeded[j] - servers < 0 ? 0 : serverNeeded[j] - servers;
            }
        }
        
        return serverCount;
    }
}