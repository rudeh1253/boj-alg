import sys

n = int(sys.stdin.readline())
tasks = [[int(j) for j in i.split()] for i in sys.stdin.readlines()]

task_end_time = [0] * (n + 1)

idx = 1
for task in tasks:
    max_time = 0
    for t in range(2, 2 + task[1]):
        max_time = max(max_time, task_end_time[task[t]])
    task_end_time[idx] = max_time + task[0]
    idx += 1

max_time = 0
for end_time in task_end_time:
    max_time = max(max_time, end_time)

print(max_time)