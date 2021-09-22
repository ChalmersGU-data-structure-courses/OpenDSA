#/* *** ODSATag: GraphNeighbor *** */
for n in G.neighbors(v):
    if G.getValue(n) != VISITED:
        DoSomething(G, n)
#/* *** ODSAendTag: GraphNeighbor *** */
