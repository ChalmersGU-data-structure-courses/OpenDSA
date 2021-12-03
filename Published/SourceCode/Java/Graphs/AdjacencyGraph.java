
import java.util.stream.Collectors;
import java.io.PrintWriter;
import java.io.StringWriter;

class AdjacencyGraph<V> implements Graph<V> {
    private Map<V, Set<Edge<V>>> edgesMap;
    private Set<V> verticesSet;
    private int totalEdges;

    public AdjacencyGraph() {
        edgesMap = new SeparateChainingHashMap<>();
        verticesSet = new MapSet<>();
        totalEdges = 0;
    }

    public int vertexCount() {
        return verticesSet.size();
    }

    public int edgeCount() {
        return totalEdges;
    }

    public boolean addVertex(V v) {
        return verticesSet.add(v);
    }

    public boolean addEdge(Edge<V> e) {
        addVertex(e.start);
        addVertex(e.end);
        Set<Edge<V>> outgoingEdges = edgesMap.get(e.start);
        if (outgoingEdges == null) {
            outgoingEdges = new MapSet<>(new LinkedMap<>());
            edgesMap.put(e.start, outgoingEdges);
        }
        boolean isNew = outgoingEdges.add(e);
        if (isNew) 
            totalEdges++;
        return isNew;
    }

    public Collection<V> vertices() {
        return verticesSet;
    }

    public Collection<Edge<V>> outgoingEdges(V from) {
        return edgesMap.get(from);
    }

    @Override
    public String toString() {
        StringWriter buffer = new StringWriter();
        PrintWriter w = new PrintWriter(buffer);
        w.println("Adjacency graph with " + vertexCount() + " vertices, " + edgeCount() + " edges");
        int ctr = 0;
        for (V v : vertices()) {
            Collection<Edge<V>> edges = outgoingEdges(v);
            if (edges == null || edges.isEmpty())
                continue;
            if (ctr++ > 10) {
                // only show at most 10 edges
                w.println("    (...)");
                break; 
            }
            w.print("    " + v + " --> ");
            for (Edge<V> e : edges)
                w.print(e.end + "[" + e.weight + "] ");
            w.println();
        }
        return buffer.toString();
    }

    /**
     * Unit tests the class
     * @param args  the command-line arguments
     */
    public static void main(String[] args) {
        AdjacencyGraph<String> graph = new AdjacencyGraph<>();
        String[] edges = {"ab", "bc", "ca", "bd", "dc", "de", "ef", "eg", "eh", "fg", "fh", "ha"};
        for (int i=0; i<edges.length; i++) {
            Edge<String> e = new Edge<>(edges[i].substring(0,1), edges[i].substring(1,2), i+3);
            graph.addEdge(e);
        }
        System.out.println(graph);
    }

}
