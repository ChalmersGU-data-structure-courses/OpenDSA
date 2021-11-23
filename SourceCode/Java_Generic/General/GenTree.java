/* *** ODSATag: GenTreeADT *** */
// General tree node ADT
public interface GTNode<E> {
    public E value();
    public boolean isLeaf();
    public GTNode<E> parent();
    public GTNode<E> leftmostChild();
    public GTNode<E> rightSibling();
    public void setValue(E value);
    public void setParent(GTNode<E> par);
    public void insertFirst(GTNode<E> n);
    public void insertNext(GTNode<E> n);
    public void removeFirst();
    public void removeNext();
}

// General tree ADT
public interface GenTree<E> {
    public void clear();       // Clear the tree
    public GTNode<E> root();   // Return the root
    // Make the tree have a new root, give first child and sib
    public void newRoot(E value, GTNode<E> first, GTNode<E> sib);
    public void newLeftChild(E value); // Add left child
}
/* *** ODSAendTag: GenTreeADT *** */


/* *** ODSATag: GenTreePrint *** */
// Preorder traversal for general trees
static void preorder(GTNode<E> node) {
    PrintNode(node);
    if (!node.isLeaf()) {
        GTNode<E> temp = node.leftmostChild();
        while (temp != null) {
            preorder(temp);
            temp = temp.rightSibling();
        }
    }
}
/* *** ODSAendTag: GenTreePrint *** */
