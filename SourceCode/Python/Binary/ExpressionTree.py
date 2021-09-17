#/* *** ODSATag: ExpressionTree *** */
# Base class for expression tree nodes
class VarBinNode:
    def isLeaf(self):  # All subclasses must implement
        raise NotImplementedError

# Leaf node
class VarLeafNode(VarBinNode):
    def __init__(self, val):
        self.operand = val
    def isLeaf(self):
        return True
    def value(self):
        return self.operand

# Internal node
class VarIntlNode(VarBinNode):
    def __init__(self, op, l, r):
        self.operator = op
        self.left = l
        self.right = r
    def isLeaf(self):
        return False
    def leftchild(self):
        return self.left
    def rightchild(self):
        return self.right
    def value(self):
        return self.operator

# Preorder traversal
def traverse(rt):
    if rt is None:
        return       # Nothing to visit
    if rt.isLeaf():  # Process leaf node
        VisitLeafNode(rt.value())
    else:            # Process internal node
        VisitInternalNode(rt.value())
        traverse(rt.leftchild())
        traverse(rt.rightchild())
#/* *** ODSAendTag: ExpressionTree *** */
