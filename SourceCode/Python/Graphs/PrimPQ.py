#/* *** ODSATag: PrimsPQ *** */
# Prim's MCST algorithm: priority queue version
def PrimPQ(G, s, D, V):
    E = [None] * G.edgeCount()        # Heap for edges
    E[0] = (0, s)                     # Initial vertex
    H = MinHeap(E, 1, G.edgeCount())
    for i in range(G.nodeCount()):    # Initialize distance
        D[i] = math.inf
    D[s] = 0
    for i in range(G.nodeCount()):    # For each vertex
        temp = H.removemin()
        if temp is None:
            return                    # Unreachable nodes exist
        v = temp.value()
        while G.getValue(v) == VISITED:
            temp = H.removemin()
            if temp is None:
                return                # Unreachable nodes exist
                v = temp.value()
        G.setValue(v, VISITED)
        if D[v] == math.inf
            return                    # Unreachable
        if v != s:
            AddEdgetoMST(V[v], v)     # Add edge to MST
        for w in G.neighbors(v):
            if D[w] > G.weight(v, w):
                D[w] = G.weight(v, w)
                V[w] = v              # Where it came from
                H.insert(D[w], w)
#/* *** ODSAendTag: PrimsPQ *** */
