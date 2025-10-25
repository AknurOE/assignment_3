package mst;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        String inputPath = "src/main/resources/input.json";
        String outputPath = "src/main/resources/output.json";


        List<Graph> graphs = IOHandler.readGraphsFromJson(inputPath);
        if (graphs == null || graphs.isEmpty()) {
            System.out.println("No graphs found in input file!");
            return;
        }


        PrimsAlgorithm prim = new PrimsAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();


        List<Result> allResults = new ArrayList<>();


        for (Graph graph : graphs) {
            System.out.println("\n=============================");
            System.out.println("Graph ID: " + graph.id);
            System.out.println("Vertices: " + graph.getNodes().size());
            System.out.println("Edges: " + graph.getEdges().size());


            Graph primGraph = new Graph(graph);
            Graph kruskalGraph = new Graph(graph);

            //PRIM
            long startPrim = System.nanoTime();
            Result primResult = prim.run(primGraph, graph.id);
            long endPrim = System.nanoTime();

            primResult.executionTimeMs = (endPrim - startPrim) / 1_000_000.0;
            allResults.add(primResult);

            //KRUSKAL
            long startKruskal = System.nanoTime();
            Result kruskalResult = kruskal.run(kruskalGraph, graph.id);
            long endKruskal = System.nanoTime();
            kruskalResult.executionTimeMs = (endKruskal - startKruskal) / 1_000_000.0;
            allResults.add(kruskalResult);

            System.out.println("\nPrim's Algorithm");
            System.out.println("MST edges: " + primResult.mstEdges);
            System.out.println("Total cost: " + primResult.totalCost);
            System.out.println("Execution time (ms): " + String.format("%.3f", primResult.executionTimeMs));

            System.out.println("\nKruskal's Algorithm");
            System.out.println("MST edges: " + kruskalResult.mstEdges);
            System.out.println("Total cost: " + kruskalResult.totalCost);
            System.out.println("Execution time (ms): " + String.format("%.3f", kruskalResult.executionTimeMs));
            System.out.println("=============================");
        }


        IOHandler.writeResultsToJson(outputPath, allResults);

        System.out.println("\n All results saved to: " + outputPath);
    }
}