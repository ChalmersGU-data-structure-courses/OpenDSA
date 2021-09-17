#/* *** ODSATag: DFS *** */
def DFS(G, v):
    PreVisit(G, v)
    G.setValue(v, VISITED)
    for n in G.neighbors(v):
        if G.getValue(n) != VISITED:
            DFS(G, n)
    PostVisit(G, v)
#/* *** ODSAendTag: DFS *** */
