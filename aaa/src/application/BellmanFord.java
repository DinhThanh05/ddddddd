package application;
import java.util.*;

//class BellmanFord {
//    private Set<Node> nodes;
//
//    BellmanFord() {
//        nodes = new HashSet<>();
//    }
//
//    void addNode(Node... n) {
//        nodes.addAll(Arrays.asList(n));
//    }
//
//    void addEdge(Node source, Node destination, double weight) {
//        source.edges.add(new Edge(source, destination, weight));
//        nodes.add(source);
//        nodes.add(destination);
//    }
//
//    String shortestPath(Node source, Node destination) {
//        Map<Node, Double> distances = new HashMap<>();
//        Map<Node, Node> predecessors = new HashMap<>();
//
//        // Initialize distances
//        for (Node node : nodes) {
//            distances.put(node, Double.POSITIVE_INFINITY);
//        }
//        distances.put(source, 0.0);
//
//        // Relax edges |V|-1 times
//        for (int i = 0; i < nodes.size() - 1; i++) {
//            for (Node node : nodes) {
//                for (Edge edge : node.edges) {
//                    if (distances.get(node) + edge.weight < distances.get(edge.destination)) {
//                        distances.put(edge.destination, distances.get(node) + edge.weight);
//                        predecessors.put(edge.destination, node);
//                    }
//                }
//            }
//        }
//
//        // Check for negative-weight cycles
//        for (Node node : nodes) {
//            for (Edge edge : node.edges) {
//                if (distances.get(node) + edge.weight < distances.get(edge.destination)) {
//                    return "Graph contains a negative weight cycle.";
//                }
//            }
//        }
//
//        // Build the shortest path
//        StringBuilder path = new StringBuilder(destination.name);
//        Node step = destination;
//        while (predecessors.get(step) != null) {
//            step = predecessors.get(step);
//            path.insert(0, step.name + " -> ");
//        }
//
//        return "Shortest Path: " + path + "\nCost: " + distances.get(destination);
//    }
//    
//}
//class BellmanFord extends GraphAlgorithm{
//    private Set<Node> nodes;
//
//    BellmanFord() {
//        nodes = new HashSet<>();
//    }
//
////    void addNode(Node... n) {
////        nodes.addAll(Arrays.asList(n));
////    }
////
////    void addEdge(Node source, Node destination, double weight) {
////        source.edges.add(new Edge(source, destination, weight));
////        nodes.add(source);
////        nodes.add(destination);
////    }
//
//    Stack<String> getNodePath(Node start, Node end) {
//        Stack<String> path = new Stack<>();
//        Map<Node, Double> distance = new HashMap<>();
//        Map<Node, Node> predecessor = new HashMap<>();
//
//        for (Node node : nodes) {
//            distance.put(node, Double.POSITIVE_INFINITY);
//        }
//        distance.put(start, 0.0);
//
//        // Relax edges |V|-1 times
//        for (int i = 0; i < nodes.size() - 1; i++) {
//            for (Node node : nodes) {
//                for (Edge edge : node.edges) {
//                    if (distance.get(node) + edge.weight < distance.get(edge.destination)) {
//                        distance.put(edge.destination, distance.get(node) + edge.weight);
//                        predecessor.put(edge.destination, node);
//                    }
//                }
//            }
//        }
//
//        // Check for negative weight cycles
//        for (Node node : nodes) {
//            for (Edge edge : node.edges) {
//                if (distance.get(node) + edge.weight < distance.get(edge.destination)) {
//                    return null; // Cycle detected
//                }
//            }
//        }
//
//        // Build path stack
//        Node current = end;
//        while (current != null) {
//            path.push(current.name); // Push tên thay vì đối tượng Node
//            current = predecessor.get(current);
//        }
//
//        return path;
//    }
//}
class BellmanFord extends GraphAlgorithm {
    
    BellmanFord() {
        super();  // Gọi constructor của lớp cha
    }

    Stack<String> getNodePath(Node start, Node end) {
        Stack<String> path = new Stack<>();
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> predecessor = new HashMap<>();

        // Đặt khoảng cách ban đầu cho tất cả các node là vô cùng
        for (Node node : nodes) {
            distance.put(node, Double.POSITIVE_INFINITY);
        }
        distance.put(start, 0.0);  // Khoảng cách từ node bắt đầu là 0

        // Relax tất cả các cạnh |V| - 1 lần
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Node node : nodes) {
                for (Edge edge : node.edges) {
                    // Nếu tìm được đường đi ngắn hơn, cập nhật khoảng cách
                    if (distance.get(node) + edge.weight < distance.get(edge.destination)) {
                        distance.put(edge.destination, distance.get(node) + edge.weight);
                        predecessor.put(edge.destination, node);
                    }
                }
            }
        }

        // Kiểm tra chu trình âm (Negative Weight Cycle)
        for (Node node : nodes) {
            for (Edge edge : node.edges) {
                if (distance.get(node) + edge.weight < distance.get(edge.destination)) {
                    // Nếu có thể giảm thêm khoảng cách, chứng tỏ có chu trình âm
                    return null;  // Phát hiện chu trình âm, trả về null
                }
            }
        }

        // Xây dựng đường đi từ đích (end) đến nguồn (start) bằng cách sử dụng predecessor
        Node current = end;
        while (current != null) {
            path.push(current.name);  // Push tên node thay vì đối tượng Node
            current = predecessor.get(current);  // Di chuyển theo predecessor
        }

        return path;
    }
}
