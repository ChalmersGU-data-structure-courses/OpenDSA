#/* *** ODSATag: GraphTrav *** */
def graphTraverse(G):
    for v in range(G.nodeCount()):   # Initialize
        G.setValue(v, None)
    for v in range(G.nodeCount()):   # Traverse
        if G.getValue(v) != VISITED:
            doTraversal(G, v)
#/* *** ODSAendTag: GraphTrav *** */
