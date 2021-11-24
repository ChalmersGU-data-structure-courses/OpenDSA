# /* *** ODSATag: inorder *** */
def inorder(node):
    if node is None: return  # Empty subtree - do nothing
    preorder(node.left())    # Process all nodes in left
    visit(node)                # Visit root node
    preorder(node.right())   # Process all nodes in right
# /* *** ODSAendTag: inorder *** */

# /* *** ODSATag: postorder *** */
def postorder(node):
    if node is None: return  # Empty subtree - do nothing
    preorder(node.left())    # Process all nodes in left
    preorder(node.right())   # Process all nodes in right
    visit(node)                # Visit root node
# /* *** ODSAendTag: postorder *** */

# /* *** ODSATag: preorder *** */
def preorder(node):
    if node is None: return  # Empty subtree - do nothing
    visit(node)                # Visit root node
    preorder(node.left())    # Process all nodes in left
    preorder(node.right())   # Process all nodes in right
# /* *** ODSAendTag: preorder *** */

# /* *** ODSATag: preorder2 *** */
# This is a bad idea
def preorder2(node):
    visit(node)
    if node.left() is not None: preorder2(node.left())
    if node.right() is not None: preorder2(node.right())
# /* *** ODSAendTag: preorder2 *** */
