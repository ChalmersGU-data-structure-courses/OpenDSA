
import java.util.stream.Collectors;
import java.io.PrintWriter;
import java.io.StringWriter;

abstract class AdjacencyGraph<V> implements Graph<V> {

    private Map<V, Set<Edge<V>>> edgesMap;
    private Set<V> verticesSet;
    private int totalEdges;

    // public AdjacencyGraph() {
    //     edgesMap = new SeparateChainingHashMap<>();
    //     verticesSet = new SeparateChainingHashSet<>();
    //     totalEdges = 0;
    // }

    // public int vertexCount() {
    //     return verticesSet.size();
    // }

    // public int edgeCount() {
    //     return totalEdges;
    // }

    // public boolean addVertex(V v) {
    //     return verticesSet.add(v);
    // }

    // public boolean addEdge(Edge<V> e) {
    //     addVertex(e.start);
    //     addVertex(e.end);
    //     Set<Edge<V>> outgoingEdges = edgesMap.get(e.start);
    //     if (outgoingEdges == null) {
    //         outgoingEdges = new LinkedListSet<>();
    //         edgesMap.put(e.start, outgoingEdges);
    //     }
    //     if (outgoingEdges.add(e))
    //         totalEdges++;
    // }
 
    // public Collection<V> vertices() {
    //     return verticesSet;
    // }

    // public Collection<Edge<V>> outgoingEdges(V from) {
    //     return edgesMap.get(from);
    // }

    // @Override
    // public String toString() {
    //     StringWriter buffer = new StringWriter();
    //     PrintWriter w = new PrintWriter(buffer);
    //     w.println("Adjacency graph with " + vertexCount() + " vertices, " + edgeCount() + " edges");
    //     w.println();
    //     int ctr = 0;
    //     for (V v : vertices()) {
    //         Collection<Edge<V>> edges = outgoingEdges(v);
    //         if (edges == null || edges.isEmpty())
    //             continue;
    //         if (ctr++ > 10) {
    //             // only show at most 10 edges
    //             w.println("(...)");
    //             break; 
    //         }
    //         w.println(v + " --> " + edges.stream()
    //             .map(e -> e.end + "[" + e.weight + "]")
    //             .collect(Collectors.joining(", ")));
    //     }
    //     return buffer.toString();
    // }

    // /**
    //  * Unit tests the class
    //  * @param args  the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     try {
    //         System.out.println(new AdjacencyGraph(args[0]));
    //     } catch (Exception e) {
    //         // If there is an error, print it and a little command-line help
    //         e.printStackTrace();
    //         System.err.println();
    //         System.err.println("Usage: java AdjacencyGraph graphfile");
    //         System.exit(1);
    //     }
    // }

}
