import java.util.*;

class Task {
    int id;
    int duration;
    int size;

    public Task(int id, int duration, int size) {
        this.id = id;
        this.duration = duration;
        this.size = size;
    }
}

class Node {
    Queue<Task> queue = new LinkedList<>();

    public void assign(Task task) {
        queue.add(task);
    }

    public int totalLoad() {
        int total = 0;
        for (Task task : queue) {
            total += task.duration;
        }
        return total;
    }

    public int taskCount() {
        return queue.size();
    }
}

class HPCSimulator {
    List<Node> nodes;

    public HPCSimulator(int numNodes) {
        nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node());
        }
    }

    public void schedule(Task task) {
        Node bestNode = nodes.get(0);
        int minLoad = bestNode.totalLoad();

        for (Node node : nodes) {
            int nodeLoad = node.totalLoad();
            if (nodeLoad < minLoad) {
                minLoad = nodeLoad;
                bestNode = node;
            }
        }

        bestNode.assign(task);
    }

    public void simulate(List<Task> tasks) {
        for (Task task : tasks) {
            schedule(task);
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            System.out.printf("Node %d: %d tasks, total load = %d%n", i, node.taskCount(), node.totalLoad());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 30; i++) {
            tasks.add(new Task(i, rand.nextInt(10) + 1, 1));
        }

        HPCSimulator sim = new HPCSimulator(4);
        sim.simulate(tasks);
    }
}
