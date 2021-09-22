#/* *** ODSATag: DijkstraPQ *** */
# Dijkstra's shortest-paths: priority queue version
def DijkstraPQ(G, s, D):
    int v                                     # The current vertex
    E = [None] * G.edgeCount()                # Heap for edges
    E[0] = (0, s)                             # Initial vertex
    H = MinHeap(E, 1, G.edgeCount())
    for i in range(G.nodeCount()):            # Initialize distance
        D[i] = INFINITY
    D[s] = 0

    for i in range(G.nodeCount()):            # For each vertex
        temp = H.removemin()
        if temp is None:
            return                            # Unreachable nodes exist

        v = temp.value()
        while G.getValue(v) == VISITED:
            temp = H.removemin()
            if temp is None:
                return                        # Unreachable nodes exist
            v = temp.value()

        G.setValue(v, VISITED)
        if D[v] == INFINITY:
            return                            # Unreachable
        for w in G.neighbors(v):
            if D[w] > D[v] + G.weight(v, w):  # Update D
                D[w] = D[v] + G.weight(v, w)
                H.insert((D[w], w))
#/* *** ODSAendTag: DijkstraPQ *** */
