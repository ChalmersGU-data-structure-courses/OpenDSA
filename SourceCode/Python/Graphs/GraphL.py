#/* *** ODSATag: GraphL *** */
class Edge:
    # Doubly linked list node
    def __init__(self, v, w, p, n):
        self.vertex = v
        self.weight = w
        self.prev = p
        self.next = n


class GraphL(Graph):
    def __init__(self, n):
        self.nodeArray = [Edge(-1, -1, None, None) for i in range(n)]
        self.nodeValues = [None] * n
        self.numEdge = 0

    def nodeCount(self):
        return self.nodeArray.length

    def edgeCount(self):
        return self.numEdge

    def getValue(self, v):
        return self.nodeValues[v]

    def setValue(self, v, val):
        self.nodeValues[v] = val
    
    def find (self, v, w):
        curr = self.nodeArray[v]
        while curr.next is not None and curr.next.vertex < w:
            curr = curr.next
        return curr

    def addEdge(self, v, w, wgt):
        if wgt == 0:
            raise ValueError("Can't store weight of 0")
        curr = self.find(v, w)
        if curr.next is not None and curr.next.vertex == w:
            curr.next.weight = wgt
        else:
            curr.next = Edge(w, wgt, curr, curr.next)
            if curr.next.next is not None:
                curr.next.next.prev = curr.next
        self.numEdge += 1

    def weight(self, v, w):
        curr = self.find(v, w)
        if curr.next is None or curr.next.vertex != w:
            return 0
        else:
            return curr.next.weight

    def removeEdge(self, v, w):
        curr = self.find(v, w)
        if curr.next is None or curr.next.vertex != w:
            return
        curr.next = curr.next.next
        if curr.next is not None:
            curr.next.prev = curr
        self.numEdge -= 1

    def hasEdge(self, v, w):
        return self.weight(v, w) != 0

    def neighbors(self, v):
        cnt = 0
        curr = self.nodeArray[v].next
        while curr is not None:
            cnt += 1
            curr = curr.next
        temp = [0] * cnt
        cnt = 0
        curr = self.nodeArray[v].next
        while curr is not None:
            temp[cnt] = curr.vertex
            cnt += 1
            curr = curr.next
        return temp
#/* *** ODSAendTag: GraphL *** */
