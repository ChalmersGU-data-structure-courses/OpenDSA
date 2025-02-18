/* *** ODSATag: BST *** */
/* *** ODSATag: BSTa *** */
// Binary Search Tree implementation
class BST {
  private BSTNode root; // Root of the BST
  private int nodecount; // Number of nodes in the BST

  // constructor
  BST() { root = null; nodecount = 0; }

  // Reinitialize tree
  public void clear() { root = null; nodecount = 0; }

  // Insert a record into the tree.
  // Records can be anything, but they must be Key
  // e: The record to insert.
  public void insert(Key e) {
    root = putHelper(root, e);
    nodecount++;
  }
/* *** ODSAendTag: BSTa *** */

/* *** ODSATag: BSTb *** */
  // Remove a record from the tree
  // key: The key value of record to remove
  // Returns the record removed, null if there is none.
  public Key remove(Key key) {
    Key temp = getHelper(root, key); // First find it
    if (temp != null) {
      root = removehelp(root, key); // Now remove it
      nodecount--;
    }
    return temp;
  }

  // Return the record with key value k, null if none exists
  // key: The key value to find
  public Key find(Key key) { return getHelper(root, key); }

  // Return the number of records in the dictionary
  public int size() { return nodecount; }
/* *** ODSAendTag: BSTb *** */
/* *** ODSAendTag: BST *** */

  // Return a record that matches the key value
/* *** ODSATag: findhelp *** */
  private Value getHelper(Node rt, Key key) {
    if (rt == null) return null;
    if (rt.key.compareTo(key) > 0)
      return getHelper(rt.left, key);
    else if (rt.key.compareTo(key) == 0)
      return rt.value;
    else return getHelper(rt.right, key);
  }
/* *** ODSAendTag: findhelp *** */


  // Return the current subtree,
  // modified to contain the new item
/* *** ODSATag: inserthelp *** */
  private Node putHelper(Node rt, Key key, Value value) {
    if (rt == null) return new Node(key, value);
    if (rt.key.compareTo(key) >= 0)
      rt.left = putHelper(rt.left, key, value);
    else
      rt.right = putHelper(rt.right, key, value);
    return rt;
  }
/* *** ODSAendTag: inserthelp *** */

/* *** ODSATag: deleteMax *** */
  // Delete the maximum valued element in a subtree
  private Node deleteMax(Node rt) {
      if (rt.right == null) return rt.left();
      rt.right = deleteMax(rt.right);
      return rt;
  }
/* *** ODSAendTag: deleteMax *** */

/* *** ODSATag: getmax *** */
  // Get the maximum valued element in a subtree
  private BSTNode getmax(BSTNode rt) {
    if (rt.right() == null) return rt;
    return getmax(rt.right());
  }
/* *** ODSAendTag: getmax *** */

  // Remove a node with the key value
  // Return the tree with the node removed
/* *** ODSATag: removehelp *** */
  private Node removeHelper(Node rt, Key key) {
    if (rt == null) return null;
    if (rt.key.compareTo(key) > 0)
      rt.left = removeHelper(rt.left, key);
    else if (rt.key.compareTo(key) < 0)
      rt.right = removeHelper(rt.right, key);
    else { // Found it
      if (rt.left == null) return rt.right;
      else if (rt.right == null) return rt.left;
      else { // Two children
        Node temp = lastNode(rt.left);
        rt.key = temp.key; rt.value = temp.value;
        rt.left = deleteMax(rt.left);
      }
    }
    return rt;
  }
/* *** ODSAendTag: removehelp *** */

/* *** ODSATag: printhelp *** */
  private void printhelp(BSTNode rt) {
    if (rt == null) return;
    printhelp(rt.left());
    printVisit(rt.value());
    printhelp(rt.right());
  }
/* *** ODSAendTag: printhelp *** */

// Used for testing
  private void printVisit(Key e) { System.out.print(e + " "); }

// Used for testing
public BSTNode root() {
  return root;
}
}
