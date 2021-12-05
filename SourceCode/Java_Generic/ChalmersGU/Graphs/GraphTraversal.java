
/* *** ODSATag: DFS *** */
static void <V> traverseDFS(Graph<V> G, V v, Set<V> visited) {
    if (!visited.contains(v)) {
        PreVisit(G, v);
        visited.add(v);
        for (Edge<V> edge : G.outgoingEdges(v))
            traverseDFS(G, edge.end, visited);
        PostVisit(G, v);
    }
}
/* *** ODSAendTag: DFS *** */


/* *** ODSATag: DFSStack *** */
static void <V> traverseDFS(Graph<V> G, V start, Set<V> visited) {
    Stack<V> agenda = new Stack<>();
    agenda.push(start);
    while (!agenda.isEmpty()) {
        V v = agenda.pop();
        if (!visited.contains(v)) {
            visited.add(v);
            PreVisit(G, v);
            for (Edge<V> edge : G.outgoingEdges(v))
                agenda.push(edge.end);
            // PostVisit is not possible in a stack/queue based version!
        }
    }
}
/* *** ODSAendTag: DFSStack *** */


/* *** ODSATag: BFS *** */
static void <V> traverseBFS(Graph<V> G, V start, Set<V> visited) {
    Queue<V> agenda = new Queue<>();
    agenda.enqueue(start);
    while (!agenda.isEmpty()) {
        V v = agenda.dequeue();
        if (!visited.contains(v)) {
            visited.add(v);
            PreVisit(G, v);
            for (Edge<V> edge : G.outgoingEdges(v))
                agenda.enqueue(edge.end);
            // PostVisit is not possible in a stack/queue based version!
        }
    }
}
/* *** ODSAendTag: BFS *** */


/* *** ODSATag: Traverse *** */
static void <V> graphTraverse(Graph<V> G) {
    Set<V> visited = new Set<V>();
    for (V v : G.vertices())
        if (!visited.contains(v))
            doTraversal(G, v, visited);
/* *** ODSAendTag: Traverse *** */
