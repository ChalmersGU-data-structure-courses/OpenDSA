// Assumes that the tree is not allowed to contain duplicates
/* *** ODSATag: checkBST *** */
static <E extends Comparable<E>> boolean checkBST(BinNode<E> node, E low, E high) {
    if (node == null) return true;  // Empty subtree

    E rootval = node.value();
    if (rootval.compareTo(low) <= 0 || rootval.compareTo(high) >= 0)
        return false;  // Out of range

    return checkBST(node.left(), low, rootval)    // Check left subtree
        && checkBST(node.right(), rootval, high); // Check right subtree
}
/* *** ODSAendTag: checkBST *** */
