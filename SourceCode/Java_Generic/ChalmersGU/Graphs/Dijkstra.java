
/* *** ODSATag: MinVertex *** */
// Find the unvisited vertex with the smalled distance
static V minVertex(Graph G<V>, Map<V,Double> D, Set<V> visited) {
    V minV = null;
    for (v : G.vertices()) {
        if (!visited.contains(v)) {
            if (minV == null)
                minV = v;
            else if (D.get(v) < D.get(minV))
                minV = v;
        }
    }
    return minV;
}
/* *** ODSAendTag: MinVertex *** */


/* *** ODSATag: GraphDijk1 *** */
// Compute shortest path distances from s, store them in D
static void <V> Dijkstra(Graph<V> G, V s, Map<V,Double> D) {
    Set<V> visited = new Set<>();
    for (V v : G.vertices())
        D.put(v, Double.POSITIVE_INFINITY);
    D.put(s, 0);
    for (int i=0; i < G.vertexCount(); i++) {   // Process the vertices
        V v = minVertex(G, D);   // Find next-closest vertex
        visited.add(v);
        if (D.get(v) == Double.POSITIVE_INFINITY)
            return;              // Unreachable
        for (Edge<V> e : G.outgoingEdges(v)) {
            V w = e.end;
            if (D.get(w) > D.get(v) + e.weight)
                D.put(w, D.get(v) + e.weight);
        }
    }
}
/* *** ODSAendTag: GraphDijk1 *** */
