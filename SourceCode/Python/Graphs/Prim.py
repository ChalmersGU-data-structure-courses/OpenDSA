#/* *** ODSATag: MinVertex *** */
# Find the unvisited vertex with the smalled distance
def minVertex(G, D):
    v = 0   # Initialize v to any unvisited vertex;
    for i in range(G.nodeCount()):
        if G.getValue(i) != VISITED
            v = i
            break
    for i in range(G.nodeCount()):  # Now find smallest value
        if G.getValue(i) != VISITED and D[i] < D[v]:
            v = i
    return v
#/* *** ODSAendTag: MinVertex *** */


#/* *** ODSATag: Prims *** */
# Compute shortest distances to the MCST, store them in D.
# V[i] will hold the index for the vertex that is i's parent in the MCST
def Prim(G, s, D, V):
    for i in range(G.nodeCount()):  # Initialize
        D[i] = math.inf
    D[s] = 0
    for i in range(G.nodeCount()):  # Process the vertices
        v = minVertex(G, D)         # Find next-closest vertex
        G.setValue(v, VISITED)
        if D[v] == INFINITY:
            return                  # Unreachable
        if v != s:
            AddEdgetoMST(V[v], v)
        for w in G.neighbors(v):
            if D[w] > G.weight(v, w):
                D[w] = G.weight(v, w)
                V[w] = v
#/* *** ODSAendTag: Prims *** */
