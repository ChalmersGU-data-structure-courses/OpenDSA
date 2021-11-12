# /* *** ODSATag: preorder *** */
def preorder(node):
    if node is None: return # Empty subtree - do nothing
    visit(rt)               # Visit root node
    preorder(node.left())   # Process all nodes in left
    preorder(node.right())  # Process all nodes in right
# /* *** ODSAendTag: preorder *** */

# /* *** ODSATag: preorder2 *** */
# This is a bad idea
def preorder2(node):
    visit(rt)
    if rt.left() is not None: preorder2(rt.left())
    if rt.right() is not None: preorder2(rt.right())
# /* *** ODSAendTag: preorder2 *** */
