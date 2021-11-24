static boolean SUCCESS = true;

// Visit nodes via inorder traversal
/* *** ODSATag: inorder *** */
static <E> void inorder(BinNode<E> node) {
    if (node == null) return;  // Empty subtree - do nothing
    preorder(node.left());     // Process all nodes in left
    visit(node);               // Visit root node
    preorder(node.right());    // Process all nodes in right
}
/* *** ODSAendTag: inorder *** */

// Visit nodes via postorder traversal
/* *** ODSATag: postorder *** */
static <E> void postorder(BinNode<E> node) {
    if (node == null) return;  // Empty subtree - do nothing
    preorder(node.left());     // Process all nodes in left
    preorder(node.right());    // Process all nodes in right
    visit(node);               // Visit root node
}
/* *** ODSAendTag: postorder *** */

// Visit nodes via preorder traversal
/* *** ODSATag: preorder *** */
static <E> void preorder(BinNode<E> node) {
    if (node == null) return;  // Empty subtree - do nothing
    visit(node);               // Visit root node
    preorder(node.left());     // Process all nodes in left
    preorder(node.right());    // Process all nodes in right
}
/* *** ODSAendTag: preorder *** */

/* *** ODSATag: preorder2 *** */
// This is a bad idea
static <E> void preorder2(BinNode<E> node) {
    visit(node);
    if (node.left()  != null) preorder2(node.left());
    if (node.right() != null) preorder2(node.right());
}
/* *** ODSAendTag: preorder2 *** */

static <E> void visit(BinNode<E> node) {
    System.out.print(node.value() + " ");
}

public static void main(String args[]) throws IOException {
  BSTNode<Integer> node1 = null;
  int temp = count(node1);

  node1 = new BSTNode<Integer>(new Integer(5));
  preorder(node1);
  System.out.println();
  node1.setLeft(new BSTNode<Integer>(new Integer(3)));
  node1.setRight(new BSTNode<Integer>(new Integer(6)));
  preorder(node1);
  System.out.println();
  preorder2(node1);
  System.out.println();
  inorder(node1);
  System.out.println();
  postorder(node1);
  System.out.println();

  BSTNode<KVPair<Integer,String>> node2 =
    new BSTNode<KVPair<Integer,String>>(new KVPair<Integer,String>(new Integer(5), "John"));

  if (!checkBST(node2, new KVPair<Integer,String>(new Integer(-1), ""),
		new KVPair<Integer,String>(new Integer(999999), "")))
    SUCCESS = false;
  node2.setLeft(new BSTNode<KVPair<Integer,String>>(new KVPair<Integer,String>(new Integer(10), "Jack")));
  if (checkBST(node2, new KVPair<Integer,String>(new Integer(-1), ""),
	       new KVPair<Integer,String>(new Integer(999999), "")))
    SUCCESS = false;

  Integer myi = new Integer(10);
  Integer myj = new Integer(12);
  KVPair<Integer,String> kv = new KVPair<Integer,String>(myi, "John");

  if (kv.compareTo(myi) != 0)
    SUCCESS = false;
  if (kv.compareTo(myj) == 0)
    SUCCESS = false;

  if (SUCCESS) {
    PrintWriter output = new PrintWriter("success");
    output.println("Success");
    output.flush();
    output.close();
    System.out.println("Success!");
  } else {
    System.out.println("Testing failed");
  }
}

}
