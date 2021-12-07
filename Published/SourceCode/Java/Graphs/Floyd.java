/** Compute all-pairs shortest paths */
static void Map<V,Map<V,Double>> Floyd(Graph<V> G) {
    // Initialize D with weights
    Map<V,Map<V,Double>> D = new Map<>();
    for (V i : G.vertices()) {
        Map<V,Double> imap = new Map<>();
        D.put(i, imap);
        for (V j : G.vertices())
            imap.put(j, i.equals(j) ? 0 : Double.POSITIVE_INFINITY);
        for (edge : G.outgoingEdges(i))
            imap.put(edge.end, edge.weight);
    }

    // Compute all k-paths
    for (V k : G.vertices()) {
        Map<V,Double> kmap = D.get(k);
        for (V i : G.vertices()) {
            Map<V,Double> imap = D.get(i);
            for (V j : G.vertices()) {
                double dist = imap.get(k) + kmap.get(j);
                if (imap.get(j) > dist)
                    imap.put(j, dist);
            }
        }
    }

    return D;
}
