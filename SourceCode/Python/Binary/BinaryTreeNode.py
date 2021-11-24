# /* *** ODSATag: BSTNode *** */
# Binary tree node implementation
class BinaryTreeNode:
    def __init__(self, element=None, left=None, right=None):
        self.element = element   # Element for this node.
        self.left = left         # Pointer to left child.
        self.right = right       # Pointer to right child.

    def isLeaf(self):
        """Return True if a leaf node, False otherwise."""
        return self.left is None and self.right is None
# /* *** ODSAendTag: BSTNode *** */
