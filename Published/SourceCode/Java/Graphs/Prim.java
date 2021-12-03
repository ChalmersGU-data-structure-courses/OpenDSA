
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


// Compute shortest distances to the MCST, store them in D.
// Parent[i] will hold the index for the vertex that is i's parent in the MCST
static void <V> Prim(Graph<V> G, V s, Map<V,Double> D, Map<V,V> Parent) {
    Set<V> visited = new Set<>();
    for (V v : G.vertices())     // Initialize
        D.put(v, Double.POSITIVE_INFINITY);
    D.put(s, 0);
    for (int i=0; i < G.vertexCount(); i++) {   // Process the vertices
        V v = minVertex(G, D);   // Find next-closest vertex
        visited.add(v);
        if (D.get(v) == Double.POSITIVE_INFINITY)
            return;              // Unreachable
        if (!v.equals(s))
            AddEdgetoMST(Parent.get(v), v);
        for (Edge<V> e : G.outgoingEdges(v)) {
            V w = e.end;
            if (D.get(w) > e.weight) {
                D.put(w, e.weight);
                Parent.put(w, v);
            }
        }
    }
}
