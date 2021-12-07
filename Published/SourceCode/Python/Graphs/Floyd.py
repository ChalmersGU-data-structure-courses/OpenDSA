# Compute all-pairs shortest paths
def floyd(G):
    # Initialize D with weights
    D = {}
    for i in G.vertices():
        D[i] = {j : 0 if i==j else float("inf")
                for j in G.vertices()}
        for edge in G.outgoingEdges(i):
            D[i][edge.end] = edge.weight

    # Compute all k-paths
    for k in G.vertices():
        for i in G.vertices():
            for j in G.vertices():
                dist = D[i][k] + D[k][j]
                if D[i][j] > dist:
                    D[i][j] = dist

    return D
