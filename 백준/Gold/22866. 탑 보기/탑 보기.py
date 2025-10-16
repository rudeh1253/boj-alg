import sys
from queue import PriorityQueue

n = int(input())

buildings = [int(i) for i in sys.stdin.readline().split()]

pq = PriorityQueue()

result = [0 for _ in range(n)]

for i in range(n - 1, -1, -1):
    building = buildings[i]

    if pq.empty():
        pq.put((building, i, 0))
        result[i] = [0]
        continue

    local_result = 0
    while True:
        if pq.empty():
            local_result = (building, i, 0)
            pq.put(local_result)
            break
        lowest = pq.get()
        if building < lowest[0]:
            local_result = (building, i, lowest[2] + 1, lowest[1])
            pq.put(lowest)
            pq.put(local_result)
            break
    
    a = [local_result[2]]
    if local_result[2] != 0:
        a.append(local_result[3])
    result[i] = a

pq = PriorityQueue()

for i in range(n):
    building = buildings[i]

    if pq.empty():
        pq.put((building, i, 0))
        continue

    local_result = 0
    while True:
        if pq.empty():
            local_result = (building, i, 0)
            pq.put(local_result)
            break
        lowest = pq.get()
        if building < lowest[0]:
            local_result = (building, i, lowest[2] + 1, lowest[1])
            pq.put(lowest)
            pq.put(local_result)
            break
    
    a = [result[i][0] + local_result[2]]

    if local_result[2] != 0:
        if result[i][0] != 0:
            if i - local_result[3] <= result[i][1] - i:
                a.append(local_result[3])
            else:
                a.append(result[i][1])
        else:
            a.append(local_result[3])
    elif result[i][0] != 0:
        a.append(result[i][1])

    result[i] = a

for i in result:
    if i[0] == 0:
        print(0)
    else:
        print(str(i[0]) + ' ' + str(i[1] + 1))