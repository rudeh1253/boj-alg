import java.util.*;
import java.io.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int[] additionalTimes = new int[diffs.length];
        additionalTimes[0] = 0;
        
        for (int i = 1; i < additionalTimes.length; i++) {
            additionalTimes[i] = times[i] + times[i - 1];
        }
        
        long minTime = 0L;
        for (int time : times) {
            minTime += time;
        }
        
        long realLimit = limit - minTime;
        return binarySearch(1, 100000, additionalTimes, diffs, realLimit);
    }
    
    int binarySearch(int lowerLevel,
                     int upperLevel,
                     int[] additionalTimes,
                     int[] diffs,
                     long limit) {
        int curLevel = (lowerLevel + upperLevel) / 2;
        if (upperLevel == lowerLevel) {
            return curLevel;
        }
        
        if (isSolvable(curLevel, additionalTimes, diffs, limit)) {
            return binarySearch(
                lowerLevel,
                curLevel,
                additionalTimes,
                diffs,
                limit
            );
        }
        return binarySearch(
            curLevel + 1,
            upperLevel,
            additionalTimes,
            diffs,
            limit
        );
    }
    
    private boolean isSolvable(int level,
                               int[] additionalTimes,
                               int[] diffs,
                               long limit) {
        long timeRequired = 0;
        for (int i = 0; i < additionalTimes.length; i++) {
            timeRequired += additionalTimes[i] * Math.max(0, diffs[i] - level);
        }
        return timeRequired <= limit;
    }
}