#include <stdio.h>
#include <string.h>

#define MAX_N 2000

int n;
int rices[MAX_N];
int dp[MAX_N][MAX_N]; // 메모이제이션 테이블

int recur(int left, int right) {
    if (left == right) {
        return rices[left] * n;
    }

    if (dp[left][right] != -1) {
        return dp[left][right];
    }

    int year = n - (right - left);
    int leftPick = recur(left + 1, right) + year * rices[left];
    int rightPick = recur(left, right - 1) + year * rices[right];

    int res = leftPick > rightPick ? leftPick : rightPick;
    dp[left][right] = res;
    return res;
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &rices[i]);
    }

    memset(dp, -1, sizeof(dp));

    for (int i = 0; i < n; i++) {
        dp[i][i] = rices[i] * n;
    }

    printf("%d\n", recur(0, n - 1));

    return 0;
}
