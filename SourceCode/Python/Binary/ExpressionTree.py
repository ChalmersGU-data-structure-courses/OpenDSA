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
def traverse(node):
    if node is None: return  # Nothing to visit
    if node.isLeaf():        # Process leaf node
        VisitLeafNode(node.value())
    else:                    # Process internal node
        VisitInternalNode(node.value())
        traverse(node.leftchild())
        traverse(node.rightchild())
#/* *** ODSAendTag: ExpressionTree *** */
