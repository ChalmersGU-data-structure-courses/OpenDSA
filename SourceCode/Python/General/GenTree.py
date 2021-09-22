#/* *** ODSATag: GenTreeADT *** */
# General tree node ADT
class GTNode:
    def value(self): raise NotImplementedError
    def isLeaf(self): raise NotImplementedError
    def parent(self): raise NotImplementedError
    def leftmostChild(self): raise NotImplementedError
    def rightSibling(self): raise NotImplementedError
    def setValue(self, value): raise NotImplementedError
    def setParent(self, par): raise NotImplementedError
    def insertFirst(self, n): raise NotImplementedError
    def insertNext(self, n): raise NotImplementedError
    def removeFirst(self): raise NotImplementedError
    def removeNext(self): raise NotImplementedError

# General tree ADT
class GenTree:
    def clear(self): raise NotImplementedError
    def root(self): raise NotImplementedError
    def newroot(self, value, first, sib): raise NotImplementedError
    def newleftchild(self, value): raise NotImplementedError
}
#/* *** ODSAendTag: GenTreeADT *** */


#/* *** ODSATag: GenTreePrint *** */
# Preorder traversal for general trees
def preorder(rt):
    PrintNode(rt)
    if not rt.isLeaf():
        temp = rt.leftmostChild()
        while temp is not None
            preorder(temp)
            temp = temp.rightSibling()
#/* *** ODSAendTag: GenTreePrint *** */
