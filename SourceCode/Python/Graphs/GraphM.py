#/* *** ODSATag: GraphM *** */
class GraphM(Graph):
    def __init__(self, n):
        self.matrix = [[0] * n for _k in range(n)]
        self.nodeValues = [None] * n
        self.numEdge = 0

    def nodeCount(self):
        return self.nodeValues.length

    def edgeCount(self):
        return self.numEdge
    
    def getValue(self, v):
        return self.nodeValues[v]
    
    def setValue(self, v, val):
        self.nodeValues[v] = val

    def addEdge(self, v, w, wgt):
        if wgt == 0:
            raise ValueError("Can't store weight of 0")
        if self.matrix[v][w] == 0:
            numEdge += 1
        self.matrix[v][w] = wgt

    def weight(self, v, w):
        return self.matrix[v][w]

    def removeEdge(self, v, w):
        if self.matrix[v][w] != 0:
            self.matrix[v][w] = 0
            numEdge -= 1
    
    def hasEdge(self, v, w):
        return self.matrix[v][w] != 0

    def neighbors(self, v):
        count = 0;
        for i in range(self.nodeValues.length):
            if self.matrix[v][i] != 0:
                count += 1
        temp = [0] * count
        count = 0
        for i in range(self.nodeValues.length):
            if matrix[v][i] != 0:
                temp[count] = i
                count += 1
        return temp
#/* *** ODSAendTag: GraphM *** */
