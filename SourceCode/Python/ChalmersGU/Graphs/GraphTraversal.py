
from API import Graph, Edge, Set

#/* *** ODSATag: DFS *** */
def traverseDFS(G, v, visited):
    if v not in visited:
        visited.add(v)
        PreVisit(G, v)
        for edge in G.outgoingEdges(v):
            traverseDFS(G, edge.end, visited)
        PostVisit(G, v)
#/* *** ODSAendTag: DFS *** */


#/* *** ODSATag: DFSStack *** */
def traverseDFS(G, v, visited):
    agenda = Stack()
    agenda.push(v)
    while not agenda.isEmpty()
        v = agenda.pop()
        if not visited.contains(v):
            visited.add(v)
            PreVisit(G, v)
            for edge in G.outgoingEdges(v):
                agenda.push(edge.end)
            # PostVisit is not possible in a stack/queue based version!
#/* *** ODSAendTag: DFSStack *** */


#/* *** ODSATag: BFS *** */
def traverseBFS(G, v, visited):
    agenda = Queue()
    agenda.enqueue(v)
    while not agenda.isEmpty()
        v = agenda.dequeue()
        if not visited.contains(v):
            visited.add(v)
            PreVisit(G, v)
            for edge in G.outgoingEdges(v):
                agenda.enqueue(edge.end)
            # PostVisit is not possible in a stack/queue based version!
#/* *** ODSAendTag: BFS *** */


#/* *** ODSATag: Traverse *** */
def graphTraverse(G):
    Visited = Set()
    for each vertex v in G.vertices():
        if not Visited.contains(v):
            doTraversal(G, v, Visited)
#/* *** ODSAendTag: Traverse *** */