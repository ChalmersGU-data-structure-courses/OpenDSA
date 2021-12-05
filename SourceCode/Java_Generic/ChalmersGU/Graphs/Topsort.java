
class Topsort {

/* *** ODSATag: TopsortDFS *** */
static <V> Stack<V> topsortDFS(Graph<V> G) {
    Set<V> visited = new MapSet<V>();
    Stack<V> sortedVertices = new LinkedStack<V>();
    for (V v : G.vertices()) {
        if (!visited.contains(v))
            topsortHelperDFS(G, v, sortedVertices, visited);
    }
    return sortedVertices;
}

static <V> void topsortHelperDFS(Graph<V> G, V v, Stack<V> sortedVertices, Set<V> visited) {
    if (!visited.contains(v)) {
        visited.add(v);
        for (Edge<V> edge : G.outgoingEdges(v)) {
            V w = edge.end;
            topsortHelperDFS(G, w, sortedVertices, visited);
        }
        sortedVertices.push(v); // PostVisit
    }
}
/* *** ODSAendTag: TopsortDFS *** */


/* *** ODSATag: TopsortBFS *** */
static <V> Queue<V> topsortBFS(Graph<V> G) {
    // Initialize the prerequisite counts
    Map<V, Integer> Count = new SeparateChainingHashMap<>();
    for (V v : G.vertices())
        Count.put(v, 0);
    for (V v : G.vertices()) {
        for (Edge<V> edge : G.outgoingEdges(v))
            // Add one to v's prereq count
            Count.put(edge.end, Count.get(edge.end) + 1);
    }

    // Initialize the queue
    Queue<V> Q = new LinkedQueue<>();
    for (V v : G.vertices()) {
        // V has no prerequisites
        if (Count.get(v) == 0)
            Q.enqueue(v);
    }

    // Process the vertices
    Queue<V> sortedVertices = new LinkedQueue<V>();
    while (!Q.isEmpty()) {
        V v = Q.dequeue();
        // PreVisit for vertex v
        sortedVertices.enqueue(v);
        for (Edge<V> edge : G.outgoingEdges(v)) {
            Count.put(edge.end, Count.get(edge.end) - 1);
            if (Count.get(edge.end) == 0)
                Q.enqueue(edge.end);
        }
    }
    return sortedVertices;
}
/* *** ODSAendTag: TopsortBFS *** */

    public static void main(String[] args) {
        Graph<String> G = new AdjacencyGraph<>();
        G.addEdge(new Edge<>("A0", "B1"));
        G.addEdge(new Edge<>("A0", "C2"));
        G.addEdge(new Edge<>("A0", "F5"));
        G.addEdge(new Edge<>("B1", "E4"));
        G.addEdge(new Edge<>("D3", "C2"));
        G.addEdge(new Edge<>("D3", "E4"));
        G.addEdge(new Edge<>("D3", "F5"));
        G.addEdge(new Edge<>("D3", "G6"));
        G.addEdge(new Edge<>("F5", "C2"));
        G.addEdge(new Edge<>("G6", "A0"));
        G.addEdge(new Edge<>("G6", "E4"));
        System.out.println(G);

        Stack<String> sort1 = topsortDFS(G);
        System.out.println("---- Topological sort (DFS) ----");
        for (String v : sort1)
            System.out.println(v);

        Queue<String> sort2 = topsortBFS(G);
        System.out.println("---- Topological sort (BFS) ----");
        for (String v : sort2)
            System.out.println(v);
    }

}
