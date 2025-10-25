
package mst;

import java.util.List;

public class Result {
    public int graphId;
    public String algorithm;
    public List<Edge> mstEdges;
    public int totalCost;
    public int vertexCount;
    public int edgeCount;
    public long operationCount;
    public double executionTimeMs; // double для дробных миллисекунд

    public Result() {}

    public Result(int graphId, String algorithm, List<Edge> mstEdges, int totalCost,
                  int vertexCount, int edgeCount, long operationCount, long executionTimeNs) {
        this.graphId = graphId;
        this.algorithm = algorithm;
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.operationCount = operationCount;
        this.executionTimeMs = executionTimeNs / 1_000_000.0;
    }

    public static Result createWithTiming(int graphId, String algorithm,
                                          List<Edge> mstEdges, int totalCost,
                                          int vertexCount, int edgeCount,
                                          long operationCount, long startTimeNs, long endTimeNs) {
        long durationNs = endTimeNs - startTimeNs;
        return new Result(graphId, algorithm, mstEdges, totalCost, vertexCount, edgeCount, operationCount, durationNs);
    }

    @Override
    public String toString() {
        return "Graph ID: " + graphId +
                "\nAlgorithm: " + algorithm +
                "\nMST edges: " + mstEdges +
                "\nTotal cost: " + totalCost +
                "\nVertices: " + vertexCount +
                "\nEdges: " + edgeCount +
                "\nOperations: " + operationCount +
                "\nExecution time: " + String.format("%.3f ms", executionTimeMs);
    }
}
