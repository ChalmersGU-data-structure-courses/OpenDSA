// Assumes that the tree is not allowed to contain duplicates
# /* *** ODSATag: checkBST *** */
def checkBST(node, low, high):
    if node is None: return True # Empty subtree
    rootval = node.value()
    if rootval <= low || rootval >= high:
        return False # Out of range
    if not checkBST(node.left(), low, rootval):
        return False # Left side failed
    return checkBST(node.right(), rootval, high)
# /* *** ODSAendTag: checkBST *** */
