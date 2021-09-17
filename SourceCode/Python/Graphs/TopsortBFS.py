#/* *** ODSATag: TopsortBFS *** */
# Topological sort: Queue
def topsortBFS(G):
    Q = LQueue(G.nodeCount()):
    Count = [0] * G.nodeCount()
    for v in range(G.nodeCount()):  # Process every edge
        for w in G.neighbors(v):
            Count[w] += 1           # Add to v's prereq count
    for v in range(G.nodeCount())   # Initialize Queue
        if Count[v] == 0            # V has no prerequisites
            Q.enqueue(v)
    while Q.length() > 0            # Process the vertices
        v = Q.dequeue()
        printout(v)                 # PreVisit for Vertex V
        for w in G.neighbors(v):
            Count[w] -= 1           # One less prerequisite
            if Count[w] == 0:       # This vertex is now free
                Q.enqueue(w)
#/* *** ODSAendTag: TopsortBFS *** */
