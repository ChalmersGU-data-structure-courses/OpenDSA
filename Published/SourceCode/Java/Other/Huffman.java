/** Huffman tree node implementation: Base class */
interface HuffBaseNode {
    boolean isLeaf(); 
    int weight();
}


/** Huffman tree node: Leaf class */
class HuffLeafNode implements HuffBaseNode {
    private char element;   // Element for this node
    private int weight;     // Weight for this node

    HuffLeafNode(char el, int wt) {
        element = el; weight = wt;
    }

    char value() {
        return element;
    }

    int weight() {
        return weight;
    }

    boolean isLeaf() {
        return true;
    }
}


/** Huffman tree node: Internal class */
class HuffInternalNode implements HuffBaseNode {
    private int weight;          // Weight for this node
    private HuffBaseNode left;   // Left child
    private HuffBaseNode right;  // Right child

    HuffInternalNode(HuffBaseNode l, HuffBaseNode r, int wt) {
        left = l; right = r; weight = wt;
    }

    HuffBaseNode left() {
        return left;
    }

    HuffBaseNode right() {
        return right;
    }

    int weight() {
        return weight;
    }

    boolean isLeaf() {
        return false;
    }
}

/** A Huffman coding tree */
class HuffTree implements Comparable {
    private HuffBaseNode root;  

    HuffTree(char el, int wt) {
        root = new HuffLeafNode(el, wt);
    }
    HuffTree(HuffBaseNode l, HuffBaseNode r, int wt) {
        root = new HuffInternalNode(l, r, wt);
    }

    HuffBaseNode root() {
        return root;
    }

    int weight() {
        return root.weight();  // Weight of tree is weight of root
    }

    int compareTo(Object t) {
        HuffTree that = (HuffTree)t;
        if (root.weight() < that.weight())
            return -1;
        else if (root.weight() == that.weight())
            return 0;
        else
            return 1;
    }
}

static HuffTree buildTree(MinHeap<HuffTree> huffHeap) {
    while (huffHeap.heapsize() > 1) { // While two items left
        HuffTree tmp1 = huffHeap.removeMin();
        HuffTree tmp2 = huffHeap.removeMin();
        HuffTree tmp3 = new HuffTree(tmp1.root(), tmp2.root(),
                                     tmp1.weight() + tmp2.weight());
        huffHeap.insert(tmp3);   // Return new tree to heap
    }
    return huffHeap.getmin();    // Return the tree
}
