// Assumes that the tree is not allowed to contain duplicates
# /* *** ODSATag: checkBST *** */
def checkBST(node, low, high):
    if node is None: return True  # Empty subtree

    rootval = node.value()
    if rootval <= low or rootval >= high:
        return False  # Out of range

    return (checkBST(node.left(), low, rootval) and  # Check left subtree
            checkBST(node.right(), rootval, high))   # Check right subtree
# /* *** ODSAendTag: checkBST *** */
