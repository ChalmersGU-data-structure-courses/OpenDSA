
from API import Graph, Edge, Set

def traverseDFS(G, v, Visited):
    PreVisit(G, v)
    Visited.add(v)
    for each edge e in G.outgoingEdges(v)
        w = e.end
        if not Visited.contains(w)
            traverseDFS(G, w, Visited)
    PostVisit(G, v)

def traverseBFS(G, v, Visited):
    Q = Queue()
    Q.enqueue(v)
    Visited.add(v)
    while not Q.isEmpty()  # Process each vertex on Q
        v = Q.dequeue()
        PreVisit(G, v)
        for each edge e in G.outgoingEdges(v):
            w = e.end
            if not Visited.contains(w):  # Put neighbors on Q
                Visited.add(w)
                Q.enqueue(w)
        PostVisit(G, v)

def graphTraverse(G):
    Visited = Set()
    for each vertex v in G.vertices():
        if not Visited.contains(v):
            doTraversal(G, v, Visited)
