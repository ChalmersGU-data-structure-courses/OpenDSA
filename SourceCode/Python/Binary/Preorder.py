# /* *** ODSATag: preorder *** */
def preorder(node):
    if node is None: return # Empty subtree - do nothing
    visit(rt)               # Visit root node
    preorder(node.left())   # Process all nodes in left
    preorder(node.right())  # Process all nodes in right
# /* *** ODSAendTag: preorder *** */
