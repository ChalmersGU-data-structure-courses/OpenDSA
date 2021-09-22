#/* *** ODSATag: TopsortDFS *** */
def topsortDFS(G):
    for v in range(G.nodeCount()):  # Initialize
        G.setValue(v, None) 
    for v in range(G.nodeCount()):
        if G.getValue(v) != VISITED:
            tophelp(G, v)

def tophelp(G, v):
    G.setValue(v, VISITED)
    for w in G.neighbors(v):
        if G.getValue(w) != VISITED:
            tophelp(G, w)
    printout(v)
#/* *** ODSAendTag: TopsortDFS *** */
