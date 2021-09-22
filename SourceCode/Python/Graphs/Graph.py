#/* *** ODSATag: GraphADT *** */
class Graph:
    def nodeCount(self): raise NotImplementedError
    def edgeCount(self): raise NotImplementedError
    def getValue(self, v): raise NotImplementedError
    def setValue(self, v, val): raise NotImplementedError
    def addEdge(self, v, w, wgt): raise NotImplementedError
    def weight(self, v, w): raise NotImplementedError
    def removeEdge(self, v, w): raise NotImplementedError
    def hasEdge(self, v, w): raise NotImplementedError
    def neighbors(self, v): raise NotImplementedError
#/* *** ODSAendTag: GraphADT *** */
