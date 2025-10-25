# Minimum Spanning Tree Algorithms - City Transportation Optimization

## Project Structure

src/main/java/mst/

├──Main.java

├──Graph.java

├──Edge.java

├──Result.java

├──PrimsAlgorithm.java

├──KruskalAlgorithm.java

└──IOHandler.java

src/main/resources/

├── input.json 

└── output.json

src/test/java/mst/

└──MSTTest.java


## How to Run

1. Make sure you have **Java 23+** installed.
2. Clone the repository.
3. Place your input JSON file at `src/main/resources/input.json`.
4. Run the project from Main.java file.
5. The program will print MST details and execution time for each graph and save all results to src/main/resources/output.json.

## Running Tests

This project includes JUnit tests MSTTest.java to ensure that:


Prim's and Kruskal's algorithms produce MSTs with the same total cost.

The MSTs are acyclic.

MSTs connect all vertices.


## To run tests:

Make sure JUnit 5 is configured in your project.

Run tests from MSTTest.java file.


## Input Format

The input file should be a JSON array of graphs, where each graph contains:

id: Graph identifier

nodes: List of vertex names

edges: List of edges with from, to, and weight


## Output

The program generates a JSON file containing results for both Prim's and Kruskal's algorithms:

MST edges

Total cost

Execution time in milliseconds

