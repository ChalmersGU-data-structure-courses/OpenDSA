/* *** ODSATag: BinNode *** */
interface BinNode<E> {
    // Binary tree node ADT

    public E value();           // Get and set the element value.
    public void setValue(E v);  // Set the element value.

    public BinNode<E> left();   // Return the left child.
    public BinNode<E> right();  // Return the right child.

    public boolean isLeaf();    // return TRUE if a leaf node, FALSE otherwise.
}
/* *** ODSAendTag: BinNode *** */
