# /* *** ODSATag: inorder *** */
def inorder(node):
    if node is None: return
    inorder(node.left())
    visit(node)
    inorder(node.right())
# /* *** ODSAendTag: inorder *** */

# /* *** ODSATag: postorder *** */
def postorder(node):
    if node is None: return
    postorder(node.left())
    postorder(node.right())
    visit(node)
# /* *** ODSAendTag: postorder *** */

# /* *** ODSATag: preorder *** */
def preorder(node):
    if node is None: return
    preorder(node.left())
    preorder(node.right())
    visit(node)
# /* *** ODSAendTag: preorder *** */

# /* *** ODSATag: count *** */
def count(node):
    if node is None: return 0 # No nodes to count
    return 1 + count(node.left()) + count(node.right())
# /* *** ODSAendTag: count *** */
