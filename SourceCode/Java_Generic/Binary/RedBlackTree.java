import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: RedBlackTree *** */
// A dictionary implemented using an red-black tree.
public class RedBlackTree<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    // A node in an red-black tree.
    class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean isRed;

        Node(boolean isRed, Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.isRed = isRed;
        }
    }

    // Check if a node is red. 'null' is always black.
    boolean isRed(Node node) {
        if (node == null) return false;
        return node.isRed;
    }

    // The root of the binary search tree.
    Node root = null;

    // Check that the invariant holds.
    void checkInvariant() {
        if (isRed(root))
            throw new AssertionError("red root");

        checkInvariantHelper(root, null, null);
    }

    // Helper method for 'check_invariant'.
    //
    // Checks that the node is the root of a valid red-black tree, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    //
    // Returns the "black height" of the tree.
    int checkInvariantHelper(Node node, Key lo, Key hi) {
        if (node == null) return 0;

        if (isRed(node.right))
            throw new AssertionError("red right child");

        if (isRed(node) && isRed(node.left))
            throw new AssertionError("red node with red left child");

        if (lo != null && node.key.compareTo(lo) <= 0)
            throw new AssertionError("key too small");

        if (hi != null && node.key.compareTo(hi) >= 0)
            throw new AssertionError("key too big");

        // Keys in the left subtree should be < node.key
        int h1 = checkInvariantHelper(node.left, lo, node.key);
        // Keys in the right subtree should be > node.key
        int h2 = checkInvariantHelper(node.right, node.key, hi);

        if (h1 != h2)
            throw new AssertionError("unbalanced tree");

        return h1 + (isRed(node) ? 0 : 1);
    }

    // Add a key-value pair, or update the value associated with an existing key.
    public void add(Key key, Value value) {
        root = addHelper(root, key, value);
        if (isRed(root)) root.isRed = false;
    }

    // Helper method for 'add'.
    Node addHelper(Node node, Key key, Value value) {
        if (node == null)
            return new Node(true, key, value, null, null);

        else if (key.compareTo(node.key) < 0)
            node.left = addHelper(node.left, key, value);

        else if (key.compareTo(node.key) > 0)
            node.right = addHelper(node.right, key, value);

        else
            node.value = value;

        return rebalance(node);
    }

    // Look up a key.
    public Value get(Key key) {
        return getHelper(root, key);
    }

    // Helper method for 'get'.
    Value getHelper(Node node, Key key) {
        if (node == null)
            return null;

        else if (key.compareTo(node.key) < 0)
            return getHelper(node.left, key);

        else if (key.compareTo(node.key) > 0)
            return getHelper(node.right, key);

        else
            return node.value;
    }

    // Repair the red-black invariant by rebalancing the node.
    Node rebalance(Node node) {
        if (node == null) return node;

        // Skew
        if (isRed(node.right))
            node = rotateLeft(node);

        // Split part 1
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);

        // Split part 2
        if (isRed(node.left) && isRed(node.right)) {
            node.left.isRed = false;
            node.right.isRed = false;
            node.isRed = true;
        }

        return node;
    }

    Node rotateLeft(Node node) {
        // Left rotation.

        //    x                y
        //   / \              / \
        //  A  y      ===>   x  C
        //    / \           / \
        //   B  C          A  B
        // """

        // Variables are named according to the picture above.
        Node x = node;
        Node A = x.left;
        Node y = x.right;
        Node B = y.left;
        Node C = y.right;

        // We also swap x's and y's colours
        // (e.g. if x was black before, then y will be black afterwards).
        return new Node(x.isRed, y.key, y.value, new Node(y.isRed, x.key, x.value, A, B), C);
    }

    Node rotateRight(Node node) {
        // Right rotation.
        // 
        //      x             y
        //     / \           / \
        //    y  C    ===>  A  x
        //   / \              / \
        //  A  B             B  C

        // Variables are named according to the picture above.
        Node x = node;
        Node y = x.left;
        Node A = y.left;
        Node B = y.right;
        Node C = x.right;

        // We also swap x's and y's colours
        // (e.g. if x was black before, then y will be black afterwards).
        return new Node(x.isRed, y.key, y.value, A, new Node(y.isRed, x.key, x.value, B, C));
    }

    // Iterate through all keys.
    // This is called when the user writes 'for (Key key: bst) { ... }.'
    public Iterator<Key> iterator() {
        // The easiest way to solve this is to add all keys to an
        // ArrayList, then iterate through that.
        ArrayList<Key> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        return keys.iterator();
    }

    // Helper method for 'iterator'
    void iteratorHelper(Node node, ArrayList<Key> keys) {
        if (node == null) return;
        iteratorHelper(node.left, keys);
        keys.add(node.key);
        iteratorHelper(node.right, keys);
    }

    // Override 'toString' to print the contents of the red-black tree.
    public String toString() {
        StringBuilder str = new StringBuilder();
        boolean firstKey = true;

        for (Key key: this) {
            Value value = this.get(key);

            if (!firstKey) str.append(", ");
            str.append(key.toString() + "->" + value.toString());
            firstKey = false;
        }

        return "{" + str + "}";
    }

    // Some test code to check that the red-black tree is working
    public static void main(String[] args) {
        RedBlackTree<Integer, Integer> bst = new RedBlackTree<>();
        int[] keys = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6};
        int[] values = new int[keys.length];
        for (int i = 0; i < values.length; i++) values[i] = i;

        for (int i = 0; i < keys.length; i++) {
            bst.add(keys[i], values[i]);
            System.out.println(bst);
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            System.out.println(bst.get(keys[i]));
            bst.checkInvariant();
        }
    }
}
/* *** ODSAendTag: RedBlackTree *** */
