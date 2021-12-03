
// Prims MCST algorithm: priority queue version
static void <V> PrimPQ(Graph G, V s, Map<V,Double> D, Map<V,V> Parent) {
    MinHeap H = new MinHeap();
    H.add(new KVPair(0, s));   // Initial vertex

    Set<V> visited = new Set<>();

    for (V v : G.vertices())  // Initialize distance
        D.put(v, Double.POSITIVE_INFINITY);
    D.put(s, 0);

    while (!H.isEmpty()) {
        V v = H.removeMin().value();
        if (!visited.contains(v)) {
            visited.add(v);
            if (D.get(v) == Double.POSITIVE_INFINITY)
                return;     // Unreachable
            if (!v.equals(s))
                AddEdgetoMST(Parent.get(v), v);
            for (Edge<V> e : G.outgoingEdges) {
                V w = e.end;
                if (D.get(w) > e.weight) { // Update D
                    D.put(w, e.weight);
                    Parent.put(w, v);
                    H.add(new KVPair(D.get(W), w));
                }
            }
        }
    }
}
