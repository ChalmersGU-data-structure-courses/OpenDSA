
/* *** ODSATag: MatrixGraph *** */
class MatrixGraph implements Graph<Integer> {
    private double[][] edgeMatrix;
    private int totalEdges;

    public MatrixGraph(int numVertices) {
        edgeMatrix = new double[numVertices][numVertices];
        totalEdges = 0;
    }

    public int vertexCount() {
        return edgeMatrix.length;
    }

    public int edgeCount() {
        return totalEdges;
    }

    public boolean addVertex(Integer v) {
        throw new UnsupportedOperationException("You cannot add vertices to a MatrixGraph");
    }

    public boolean addEdge(Edge<Integer> e) {
        if (e.weight == 0) throw new IllegalArgumentException("Edges cannot have weight 0");
        boolean isNew = edgeMatrix[e.start][e.end] == 0;
        edgeMatrix[e.start][e.end] = e.weight;
        if (isNew)
            totalEdges++;
        return isNew;
    }

    public Collection<Integer> vertices() {
        Queue<Integer> range = new LinkedQueue<>();
        for (int v=0; v < edgeMatrix.length; v++)
            range.enqueue(v);
        return range;
    }

    public Collection<Edge<Integer>> outgoingEdges(Integer v) {
        Queue<Edge<Integer>> outgoing = new LinkedQueue<>();
        for (int w=0; w < edgeMatrix.length; w++)
            if (edgeMatrix[v][w] != 0)
                outgoing.enqueue(new Edge<Integer>(v, w, edgeMatrix[v][w]));
        return outgoing;
    }

    // For an adjacency matrix, it's much more efficient to get information
    // about known edges, than to search through outgoingEdges,
    // so we add these two as convenience methods.

    public boolean isEdge(Integer v, Integer w) {
        return edgeMatrix[v][w] != 0;
    }

    public double edgeWeight(Integer v, Integer w) {
        return edgeMatrix[v][w];
    }

}
/* *** ODSAendTag: MatrixGraph *** */
