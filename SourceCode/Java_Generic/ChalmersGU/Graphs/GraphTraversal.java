
/* *** ODSATag: DFS *** */
static void <V> traverseDFS(Graph<V> G, V v, Set<V> Visited) {
    PreVisit(G, v);
    Visited.add(v);
    for (Edge<V> e : G.outgoingEdges(v)) {
        V w = e.end;
        if (!Visited.contains(w))
            traverseDFS(G, w, Visited);
    }
    PostVisit(G, v);
}
/* *** ODSAendTag: DFS *** */

/* *** ODSATag: BFS *** */
static void <V> traverseBFS(Graph<V> G, V v, Set<V> Visited) {
    Queue<V> Q = new Queue<>();
    Q.enqueue(v);
    Visited.add(v);
    while (!Q.isEmpty()) { // Process each vertex on Q
        V v = Q.dequeue();
        PreVisit(G, v);
        for (Edge<V> e : G.outgoingEdges(v)) {
            V w = e.end;
            if (!Visited.contains(w)) { // Put neighbors on Q
                Visited.add(w);
                Q.enqueue(w);
            }
        }
        PostVisit(G, v);
    }
}
/* *** ODSAendTag: BFS *** */

/* *** ODSATag: Traverse *** */
static void <V> graphTraverse(Graph<V> G) {
    Set<V> Visited = new Set<V>();
    for (V v : G.vertices())
        if (!Visited.contains(v))
            doTraversal(G, v, Visited);
/* *** ODSAendTag: Traverse *** */
