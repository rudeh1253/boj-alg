import sys
from queue import PriorityQueue

n, k = [int(i) for i in sys.stdin.readline().split()]

jewelry = []
for _ in range(n):
    jewelry.append([int(i) for i in sys.stdin.readline().split()])

bags = []
for _ in range(k):
    bags.append(int(sys.stdin.readline()))

jewelry.sort()
bags.sort()

j_pointer = 0

j_pq = PriorityQueue()

price_sum = 0
for b_pointer in range(len(bags)):
    bag = bags[b_pointer]

    while j_pointer < len(jewelry) and bag >= jewelry[j_pointer][0]:
        jewel = jewelry[j_pointer]
        j_pq.put(-jewel[1])
        j_pointer += 1
    if not j_pq.empty():
        price_sum += -j_pq.get()

print(price_sum)