// Kruskal's MST algorithm
static void <V> Kruskal(Graph<V> G) {
    ParPtrTree A = new ParPtrTree();
    for (V v : G.vertices())
        A.MAKE_SET(v);   // Create one singleton set for each vertex

    Edge<V>[] E = new Edge<>[edgeCount];
    for (V v : G.vertices())
        for (Edge<V> edge : G.outgoingEdges(v))
            E.append(edge);
    Arrays.sort(E, weightComparator);       // Sort the edges by increasing weight

    int numEdgesInMST = 0;
    for (Edge<V> edge : E) {
        if (A.FIND(edge.start) != A.FIND(edge.end)) {  // If the vertices are not connected
            AddEdgetoMST(edge);             // Add this edge to the MCST
            numEdgesInMST++;
            if (numEdgesInMST >= G.vertexCount()-1)
                return;                     // Stop when the MST has |V|-1 edges
            A.UNION(edge.start, edge.end);  // Connect the two vertices
        }
    }
}

// KRUSKAL(G):
// A = ∅
// For each vertex v ∈ G.V:
//     MAKE-SET(v)
// For each edge (u, v) ∈ G.E ordered by increasing order by weight(u, v):
//     if FIND-SET(u) ≠ FIND-SET(v):       
//     A = A ∪ {(u, v)}
//     UNION(u, v)
// return A
