package mst;

import java.util.*;

public class PrimsAlgorithm {

    public Result run(Graph graph, int graphId) {
        long startTime = System.nanoTime();


        List<String> nodes = new ArrayList<>(graph.getNodes());
        List<Edge> edges = new ArrayList<>(graph.getEdges());

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        long operationCount = 0;


        String startNode = nodes.get(0);
        visited.add(startNode);


        for (Edge e : edges) {
            if (e.from.equals(startNode) || e.to.equals(startNode)) {
                pq.add(e);
            }
        }


        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            Edge edge = pq.poll();
            operationCount++;

            String nextNode = null;
            if (!visited.contains(edge.from)) nextNode = edge.from;
            else if (!visited.contains(edge.to)) nextNode = edge.to;

            if (nextNode != null) {
                visited.add(nextNode);
                mstEdges.add(edge);


                for (Edge e : edges) {
                    if ((e.from.equals(nextNode) && !visited.contains(e.to)) ||
                            (e.to.equals(nextNode) && !visited.contains(e.from))) {
                        pq.add(e);
                    }
                }
            }
        }

        int totalCost = mstEdges.stream().mapToInt(e -> e.weight).sum();
        long endTime = System.nanoTime();

        return Result.createWithTiming(
                graphId, "Prim", mstEdges, totalCost,
                nodes.size(), edges.size(),
                operationCount, startTime, endTime
        );
    }
}