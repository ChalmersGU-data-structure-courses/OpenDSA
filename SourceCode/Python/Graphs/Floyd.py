#/* *** ODSATag: Floyd *** */
# Compute all-pairs shortest paths 
def Floyd(G, D):
    for i in range(G.n()):  # Initialize D with weights
        for j in range(G.n()):
            if G.weight(i, j) != 0:
                D[i][j] = G.weight(i, j)
    for k in range(G.n()):  # Compute all k paths
        for i in range(G.n()):
            for j in range(G.n()):
                if (D[i][k] != math.inf and
                    D[k][j] != math.inf and
                    D[i][j] > D[i][k] + D[k][j]
                    ):
                    D[i][j] = D[i][k] + D[k][j]
#/* *** ODSAendTag: Floyd *** */
