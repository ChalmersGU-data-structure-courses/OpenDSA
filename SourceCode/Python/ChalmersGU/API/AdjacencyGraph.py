
from API import Graph, Edge, Map, Set, Collection
from SeparateChainingHashMap import SeparateChainingHashMap
from LinkedMap import LinkedMap
from MapSet import MapSet

#/* *** ODSATag: AdjacencyGraph *** */
class AdjacencyGraph(Graph):
    _edgesMap    : Map
    _verticesSet : Set
    _totalEdges  : int

    def __init__(self):
        self._edgesMap = SeparateChainingHashMap()
        self._verticesSet = MapSet()
        self._totalEdges = 0

    def vertexCount(self) -> int:
        return self._verticesSet.size()

    def edgeCount(self) -> int:
        return self._totalEdges

    def addVertex(self, v) -> bool:
        return self._verticesSet.add(v)

    def addEdge(self, e : Edge) -> bool:
        self.addVertex(e.start)
        self.addVertex(e.end)
        outgoingEdges : Set = self._edgesMap.get(e.start)
        if outgoingEdges is None:
            outgoingEdges = MapSet(LinkedMap)
            self._edgesMap.put(e.start, outgoingEdges)
        isNew : bool = outgoingEdges.add(e)
        if isNew:
            self._totalEdges += 1
        return isNew

    def vertices(self) -> Collection:
        return self._verticesSet

    def outgoingEdges(self, frm) -> Collection:
        return self._edgesMap.get(frm)
#/* *** ODSAendTag: AdjacencyGraph *** */

    # @Override
    # public String toString() {
    #     StringWriter buffer = new StringWriter();
    #     PrintWriter w = new PrintWriter(buffer);
    #     w.println("Adjacency graph with " + vertexCount() + " vertices, " + edgeCount() + " edges");
    #     int ctr = 0;
    #     for (V v : vertices()) {
    #         Collection<Edge<V>> edges = outgoingEdges(v);
    #         if (edges == null || edges.isEmpty())
    #             continue;
    #         if (ctr++ > 10) {
    #             // only show at most 10 edges
    #             w.println("    (...)");
    #             break; 
    #         }
    #         w.print("    " + v + " --> ");
    #         for (Edge<V> e : edges)
    #             w.print(e.end + "[" + e.weight + "] ");
    #         w.println();
    #     }
    #     return buffer.toString();
    # }

    # /**
    #  * Unit tests the class
    #  * @param args  the command-line arguments
    #  */
    # public static void main(String[] args) {
    #     AdjacencyGraph<String> graph = new AdjacencyGraph<>();
    #     String[] edges = {"ab", "bc", "ca", "bd", "dc", "de", "ef", "eg", "eh", "fg", "fh", "ha"};
    #     for (int i=0; i<edges.length; i++) {
    #         Edge<String> e = new Edge<>(edges[i].substring(0,1), edges[i].substring(1,2), i+3);
    #         graph.addEdge(e);
    #     }
    #     System.out.println(graph);
    # }
