
from API import Graph, Edge, Set

#/* *** ODSATag: DFS *** */
def traverseDFS(G, v, visitedVertices):
    PreVisit(G, v)
    visitedVertices.add(v)
    for each edge e in G.outgoingEdges(v)
        w = e.end
        if not visitedVertices.contains(w)
            traverseDFS(G, w, visitedVertices)
    PostVisit(G, v)
#/* *** ODSAendTag: DFS *** */

#/* *** ODSATag: BFS *** */
def traverseBFS(G, v, visitedVertices):
    Q = Queue()
    Q.enqueue(v)
    visitedVertices.add(v)
    while not Q.isEmpty()  # Process each vertex on Q
        v = Q.dequeue()
        PreVisit(G, v)
        for each edge e in G.outgoingEdges(v):
            w = e.end
            if not visitedVertices.contains(w):  # Put neighbors on Q
                visitedVertices.add(w)
                Q.enqueue(w)
        PostVisit(G, v)
#/* *** ODSAendTag: BFS *** */

#/* *** ODSATag: Traverse *** */
def graphTraverse(G):
    visitedVertices = Set()
    for each vertex v in G.vertices():
        if not visitedVertices.contains(v):
            doTraversal(G, v, visitedVertices)
#/* *** ODSAendTag: Traverse *** */
