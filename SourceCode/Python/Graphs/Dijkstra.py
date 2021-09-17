#/* *** ODSATag: MinVertex *** */
# Find the unvisited vertex with the smalled distance
def minVertex(G, D):
    v = 0  # Initialize v to any unvisited vertex
    for i in range(G.nodeCount()):
        if G.getValue(i) != VISITED:
            v = i
            break
    for i in range(G.nodeCount()):   # Now find smallest value
        if G.getValue(i) != VISITED and D[i] < D[v]:
            v = i
    return v
#/* *** ODSAendTag: MinVertex *** */


#/* *** ODSATag: GraphDijk1 *** */
# Compute shortest path distances from s, store them in D
def Dijkstra(G, s, D):
    for i in range(G.nodeCount()):   # Initialize
        D[i] = INFINITY
    D[s] = 0
    for i in range(G.nodeCount()):   # Process the vertices
        v = minVertex(G, D)          # Find next-closest vertex
        G.setValue(v, VISITED)
        if D[v] == INFINITY:
            return   # Unreachable
        for w in G.neighbors(v):
            if D[w] > D[v] + G.weight(v, w):
                D[w] = D[v] + G.weight(v, w)
#/* *** ODSAendTag: GraphDijk1 *** */
