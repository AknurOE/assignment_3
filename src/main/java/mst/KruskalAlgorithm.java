package mst;

import java.util.*;

public class KruskalAlgorithm {

    static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();

        public void makeSet(List<String> nodes) {
            for (String node : nodes) {
                parent.put(node, node);
            }
        }

        public String find(String node) {
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node)));
            }
            return parent.get(node);
        }

        public void union(String a, String b) {
            String rootA = find(a);
            String rootB = find(b);
            if (!rootA.equals(rootB)) {
                parent.put(rootA, rootB);
            }
        }
    }

    public Result run(Graph graph, int graphId) {
        long startTime = System.nanoTime();

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        UnionFind uf = new UnionFind();
        uf.makeSet(graph.getNodes());

        List<Edge> mstEdges = new ArrayList<>();
        long operationCount = 0;

        for (Edge edge : edges) {
            operationCount++;
            String rootFrom = uf.find(edge.from);
            String rootTo = uf.find(edge.to);

            if (!rootFrom.equals(rootTo)) {
                mstEdges.add(edge);
                uf.union(edge.from, edge.to);
            }

            if (mstEdges.size() == graph.getNodes().size() - 1)
                break;
        }

        int totalCost = mstEdges.stream().mapToInt(e -> e.weight).sum();
        long endTime = System.nanoTime();


        return Result.createWithTiming(
                graphId, "Kruskal", mstEdges, totalCost,
                graph.getNodes().size(), graph.getEdges().size(),
                operationCount, startTime, endTime
        );
    }
}