#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_TEST_CASES 3
#define MAX_N 100      // 동전 종류 수 (필요에 따라 조정)
#define MAX_SUM 100000 // 가능한 최대 합 (입력 크기에 따라 조정)

// 중복된 합을 막기 위해 visited 배열 사용
bool visited[MAX_SUM + 1];

int solve(int n, int coins[][2]) {
    int total = 0;
    for (int i = 0; i < n; i++) {
        total += coins[i][0] * coins[i][1];
    }

    // 전체 합이 홀수면 절반으로 나눌 수 없음
    if (total % 2 == 1) return 0;

    int half = total / 2;

    // 가능한 합을 저장할 배열 (가변적인 리스트 역할)
    int possible[MAX_SUM + 1];
    int count = 0;
    possible[count++] = 0; // 0원은 항상 가능
    visited[0] = true;

    for (int i = 0; i < n; i++) {
        int price = coins[i][0];
        int cnt = coins[i][1];

        int newCount = 0;
        int *newPossible = (int *)malloc(sizeof(int) * MAX_SUM);

        // 기존 가능한 모든 합에 대해
        for (int j = 0; j < count; j++) {
            for (int k = 1; k <= cnt; k++) {
                int newSum = possible[j] + price * k;
                if (newSum > half) break; // 절반 초과면 의미 없음
                if (!visited[newSum]) {
                    visited[newSum] = true;
                    newPossible[newCount++] = newSum;
                }
            }
        }

        // 새로운 합들을 기존 배열에 병합
        for (int j = 0; j < newCount; j++) {
            possible[count++] = newPossible[j];
        }

        free(newPossible);
    }

    return visited[half] ? 1 : 0;
}

int main(void) {
    int test_cases[MAX_TEST_CASES][MAX_N][2];
    int n_list[MAX_TEST_CASES];

    for (int t = 0; t < MAX_TEST_CASES; t++) {
        int n;
        scanf("%d", &n);
        n_list[t] = n;
        for (int i = 0; i < n; i++) {
            scanf("%d %d", &test_cases[t][i][0], &test_cases[t][i][1]);
        }
    }

    for (int t = 0; t < MAX_TEST_CASES; t++) {
        // visited 초기화
        for (int i = 0; i <= MAX_SUM; i++) {
            visited[i] = false;
        }
        printf("%d\n", solve(n_list[t], test_cases[t]));
    }

    return 0;
}
