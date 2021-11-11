# /* *** ODSATag: BSTNode *** */
# Binary tree node implementation
class BinaryTreeNode:
    def __init__(self, element=None, left=None, right=None):
        # Element for this node
        self.element = element

        # Pointer to left child
        self.left = left

        # Pointer to right child
        self.right = right

    def isLeaf(self):
        """Return True if a leaf node, False otherwise."""

        return self.left is not None or self.right is not None
# /* *** ODSAendTag: BSTNode *** */
