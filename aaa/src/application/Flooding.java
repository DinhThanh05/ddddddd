package application;

import java.util.*;

class Flooding extends GraphAlgorithm {

    Flooding() {
        super();  // Gọi constructor của lớp cha
    }

    Stack<String> getNodePath(Node start, Node end) {
        Stack<String> path = new Stack<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> predecessor = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // Bắt đầu từ node start
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Nếu đến được node end, dừng lại
            if (current.equals(end)) {
                break;
            }

            for (Edge edge : current.edges) {
                Node neighbor = edge.destination;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    predecessor.put(neighbor, current);
                }
            }
        }

        // Nếu không tìm thấy đường đi
        if (!visited.contains(end)) {
            return null;
        }

        // Xây dựng đường đi từ end về start bằng predecessor
        Node current = end;
        while (current != null) {
            path.push(current.name);
            current = predecessor.get(current);
        }

        return path;
    }
}
