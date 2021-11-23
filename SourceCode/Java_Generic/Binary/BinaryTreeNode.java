/* *** ODSATag: BSTNode *** */
// Binary tree node implementation
class BinaryTreeNode<E> {
    public E element;               // Element for this node.
    public BinaryTreeNode<E> left;  // Pointer to left child.
    public BinaryTreeNode<E> right; // Pointer to right child.

    // Constructors
    BinaryTreeNode() {
        left = right = null;
    }
    BinaryTreeNode(E val) {
        left = right = null; element = val;
    }
    BinaryTreeNode(E val, BinaryTreeNode<E> l, BinaryTreeNode<E> r) {
        left = l; right = r; element = val;
    }

    // Return TRUE if a leaf node, FALSE otherwise.
    public boolean isLeaf() {
        return left == null && right == null;
    }
}
/* *** ODSAendTag: BSTNode *** */
