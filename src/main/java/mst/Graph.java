package mst;

import java.util.*;

public class Graph {
    public int id;
    public String description;
    public List<String> nodes;
    public List<Edge> edges;

    public Graph(int id, String description, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.description = description;
        this.nodes = nodes;
        this.edges = edges;
    }

    public Graph(Graph other) {
        this.id = other.id;
        this.description = other.description;
        this.nodes = new ArrayList<>(other.nodes);
        this.edges = new ArrayList<>();
        for (Edge e : other.edges) {
            this.edges.add(new Edge(e.from, e.to, e.weight));
        }
    }


    public Graph() {}

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getNeighbors(String node) {
        List<Edge> neighbors = new ArrayList<>();
        for (Edge e : edges) {
            if (e.from.equals(node) || e.to.equals(node)) {
                neighbors.add(e);
            }


        }
        return neighbors;
    }
    @Override
    public String toString() {
        return "Graph{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}