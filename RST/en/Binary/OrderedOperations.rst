Ordered sets and maps: All sets support the operation of checking if an item is present in the set. Similarly, all maps support checking if a key is present and returning the associated value. Along with operations to add and remove items, these are the core operations of sets and maps.

Some implementations of sets and maps support more operations besides these. In particular, binary search trees support many operations based on the *order* of the elements. In this section we will see some of these operations.

Minimum and maximum: We can easily find the minimum or maximum element of a binary search tree. To find the minimum, we start at the root of the tree and @go as far left as possible@. That is, we descend into the root's left child, then into that node's left child, and so on. We stop once we find a node whose left child is null - that node contains the smallest element. For a map, this returns the smallest key. To find the maximum, we do the same algorithm, but going right instead of left.

<<code>> - <<demo?>> - <<exercise?>>

Successor and predecessor:

Suppose that we have a set of items, and a value *x* which may or may not be in the set. The *successor* of x is the *least* item *greater* than x in the set. Similarly, the *predecessor* of x is the *greatest* item *less* than x. If you imagine the set written out in ascending order, then the successor of x is the item that comes after x, and the predecessor is the item that comes before x. For example, in a set containing the items {1, 3, 6}:

* The successor of 3 is 6.
* The predecessor of 3 is 1.
* The successor of 2 is 3.
* The predecessor of 5 is 3.
* 1 has no predecessor.
* 8 has no successor.

Because binary search trees are ordered, we can efficiently compute the successor or predecessor of any element. As usual, if the BST implements a map then the successor and predecessor operations operate on keys.

As usual, we will start at the root node and recurse into one of the children. So let us suppose we have a BST implementing a set, and we want to find the successor of an item x. If the BST is null, then x has no successor. Otherwise, by looking at the item stored in the root, we can tell where to search for the successor:

* If the item at the root is less than x, then everything in the left subtree is also less than x. Therefore the successor is in the right subtree.
* If the item at the root is equal to x, then the successor is in the right subtree.
* If the item at the root is greater than x, then the successor *could* be in the left subtree. However, the root itself could also be the successor, depending on what the left subtree contains.

By coding up these rules we get an algorithm for computing the successor. The algorithm returns null if the item has no successor.

Item successor(Item x):
  return successor(root, x)

Item successor(Node node, Item x):
  if node == null: return null
  if node.value < x:
    return successor(node.right, x)
  elif node.value == x:
    return successor(node.left, x)
  else: £ here node.value > x
    £ the successor is either in the left subtree,
    £ or else it's this node
    Node succ = successor(node.left, x)
    if succ == null: £ no successor in the left subtree
      return node
    else: £ successor is in the left subtree
      return succ

<<demo>>
