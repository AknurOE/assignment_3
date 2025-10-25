package mst;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    private Map<String, String> parent;


    private void makeSet(List<String> nodes) {
        parent = new HashMap<>();
        for (String node : nodes) parent.put(node, node);
    }

    private String find(String n) {
        if (!parent.get(n).equals(n)) {
            parent.put(n, find(parent.get(n)));
        }
        return parent.get(n);
    }

    private void union(String a, String b) {
        parent.put(find(a), find(b));
    }

    private boolean hasCycle(List<Edge> edges, List<String> nodes) {
        makeSet(nodes);
        for (Edge e : edges) {
            String rootA = find(e.from);
            String rootB = find(e.to);
            if (rootA.equals(rootB)) return true; // цикл найден
            union(rootA, rootB);
        }
        return false;
    }


    @Test
    public void testPrimAndKruskalHaveSameCostAndValidMST() {
        List<Graph> graphs = IOHandler.readGraphsFromJson("src/main/resources/input.json");
        assertNotNull(graphs, "Graphs should not be null");
        assertFalse(graphs.isEmpty(), "There should be at least one graph");

        PrimsAlgorithm prim = new PrimsAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        for (Graph g : graphs) {
            // Прим
            Result rPrim = prim.run(g, g.id);
            assertEquals(g.getNodes().size() - 1, rPrim.mstEdges.size(), "Prim MST must have V-1 edges");
            assertFalse(hasCycle(rPrim.mstEdges, g.getNodes()), "Prim MST must be acyclic");

            // Крускал
            Result rKruskal = kruskal.run(g, g.id);
            assertEquals(g.getNodes().size() - 1, rKruskal.mstEdges.size(), "Kruskal MST must have V-1 edges");
            assertFalse(hasCycle(rKruskal.mstEdges, g.getNodes()), "Kruskal MST must be acyclic");

            // Сравнение стоимости
            assertEquals(rPrim.totalCost, rKruskal.totalCost, "MST cost must be the same for both algorithms");
        }
    }

    @Test
    public void testPrimMSTConnectsAllVertices() {
        List<Graph> graphs = IOHandler.readGraphsFromJson("src/main/resources/input.json");
        assertNotNull(graphs, "Graphs should not be null");
        assertFalse(graphs.isEmpty(), "There should be at least one graph");
        PrimsAlgorithm prim = new PrimsAlgorithm();

        for (Graph g : graphs) {
            Result rPrim = prim.run(g, g.id);
            assertEquals(g.getNodes().size() - 1, rPrim.mstEdges.size(), "Prim MST should connect all vertices");
        }
    }
}
