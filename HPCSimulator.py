import random
from collections import deque

class Task:
    def __init__(self, id, duration, size):
        self.id = id
        self.duration = duration
        self.size = size

class Node:
    def __init__(self):
        self.queue = deque()

    def assign(self, task):
        self.queue.append(task)

class HPCSimulator:
    def __init__(self, num_nodes):
        self.nodes = [Node() for _ in range(num_nodes)]

    def schedule(self, task):
        best_node = min(self.nodes, key=lambda n: sum(t.duration for t in n.queue))
        best_node.assign(task)

    def simulate(self, tasks):
        for task in tasks:
            self.schedule(task)

        for i, node in enumerate(self.nodes):
            total = sum(t.duration for t in node.queue)
            print(f"Node {i}: {len(node.queue)} tasks, total load = {total}")

# Test
tasks = [Task(i, random.randint(1, 10), 1) for i in range(30)]
sim = HPCSimulator(num_nodes=4)
sim.simulate(tasks)

