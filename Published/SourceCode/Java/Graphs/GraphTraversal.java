
static void <V> traverseDFS(Graph<V> G, V v, Set<V> visitedVertices) {
    PreVisit(G, v);
    visitedVertices.add(v);
    for (Edge<V> e : G.outgoingEdges(v)) {
        V w = e.end;
        if (!visitedVertices.contains(w))
            traverseDFS(G, w, visitedVertices);
    }
    PostVisit(G, v);
}

static void <V> traverseBFS(Graph<V> G, V v, Set<V> visitedVertices) {
    Queue<V> Q = new Queue<>();
    Q.enqueue(v);
    visitedVertices.add(v);
    while (!Q.isEmpty()) { // Process each vertex on Q
        V v = Q.dequeue();
        PreVisit(G, v);
        for (Edge<V> e : G.outgoingEdges(v)) {
            V w = e.end;
            if (!visitedVertices.contains(w)) { // Put neighbors on Q
                visitedVertices.add(w);
                Q.enqueue(w);
            }
        }
        PostVisit(G, v);
    }
}

static void <V> graphTraverse(Graph<V> G) {
    Set<V> visitedVertices = new Set<V>();
    for (V v : G.vertices())
        if (!visitedVertices.contains(v))
            doTraversal(G, v, visitedVertices);
