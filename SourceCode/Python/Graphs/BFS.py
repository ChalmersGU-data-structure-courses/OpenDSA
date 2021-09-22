#/* *** ODSATag: BFS *** */
def BFS(G, v):
    Q = new LQueue(G.nodeCount())
    Q.enqueue(v)
    G.setValue(v, VISITED)
    while Q.length() > 0:   # Process each vertex on Q
        v = Q.dequeue()
        PreVisit(G, v)
        for n in G.neighbors(v):
            if G.getValue(n) != VISITED:  # Put neighbors on Q
                G.setValue(n, VISITED)
                Q.enqueue(n)
        PostVisit(G, v)
#/* *** ODSAendTag: BFS *** */
