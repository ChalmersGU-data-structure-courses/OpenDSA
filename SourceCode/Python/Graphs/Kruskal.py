#/* *** ODSATag: Kruskal *** */
# Kruskal's MST algorithm
def Kruskal(G):
    A = ParPtrTree(G.nodeCount())   # Equivalence array
    E = [None] * G.edgeCount()      # Minheap array
    edgecnt = 0                     # Count of edges

    for i in range(G.nodeCount()):  # Put edges in the array
        for w in G.neighbors(i):
            E[edgecnt] = (G.weight(i, w), i, w)
            edgecnt += 1

    H = MinHeap(E, edgecnt, edgecnt)
    numMST = G.nodeCount()         # Initially n disjoint classes
    while numMST>1:                # Combine equivalence classes
        temp = H.removemin()       # Next cheapest edge
        if temp is None:
            return                 # Must have disconnected vertices
        _w, v, u = temp
        if A.differ(v, u):         # If in different classes
            A.UNION(v, u)          # Combine equiv classes
            AddEdgetoMST(v, u)     # Add this edge to MST
            numMST -= 1            # One less MST
#/* *** ODSAendTag: Kruskal *** */
