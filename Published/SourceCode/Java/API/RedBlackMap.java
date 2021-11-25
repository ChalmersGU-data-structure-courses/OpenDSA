
import java.util.ArrayList;
import java.util.Iterator;

// A dictionary implemented using an red-black tree.
public class RedBlackMap<K extends Comparable<K>, V> implements Map<K, V> {
    Node root = null;   // The root of the red black tree.
    int treeSize = 0;   // The size of the tree.

    // A node in an red-black tree.
    class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean isRed;

        Node(boolean isRed, K key, V value, Node left, Node right) {
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

    // Check that the invariant holds.
    void checkInvariant() {
        if (isRed(root))
            throw new AssertionError("red root");
        ArrayList<K> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        if (keys.size() != treeSize)
            throw new AssertionError("wrong tree size");
        checkInvariantHelper(root, null, null);
    }

    // Recursive helper method for 'check_invariant'.
    // Checks that the node is the root of a valid red-black tree, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    // Returns the "black height" of the tree.
    int checkInvariantHelper(Node node, K lo, K hi) {
        if (node == null) return 0;

        if (lo != null && node.key.compareTo(lo) <= 0)
            throw new AssertionError("key too small");
        if (hi != null && node.key.compareTo(hi) >= 0)
            throw new AssertionError("key too big");

        if (isRed(node.right))
            throw new AssertionError("red right child");
        if (isRed(node) && isRed(node.left))
            throw new AssertionError("red node with red left child");

        // Keys in the left subtree should be < node.key
        // Keys in the right subtree should be > node.key
        int h1 = checkInvariantHelper(node.left, lo, node.key);
        int h2 = checkInvariantHelper(node.right, node.key, hi);
        if (h1 != h2)
            throw new AssertionError("unbalanced tree");

        return h1 + (isRed(node) ? 0 : 1);
    }

    // Return true if there are no keys.
    public boolean isEmpty() {
        return root == null;
    }

    // Return the number of keys.
    public int size() {
        return treeSize;
    }

    // Return true if the key has an associated value.
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    // Look up a key.
    public V get(K key) {
        return getHelper(root, key);
    }

    // Recursive helper method for 'get'.
    V getHelper(Node node, K key) {
        if (node == null)
            return null;
        if (node.key.compareTo(key) > 0)
            return getHelper(node.left, key);
        else if (node.key.compareTo(key) < 0)
            return getHelper(node.right, key);
        else // node.key == key
            return node.value;
    }

    // Add a key-value pair, or update the value associated with an existing key.
    // Returns the previous value associated with the key,
    // or null if the key wasn't previously present.
    public V put(K key, V value) {
        root = putHelper(root, key, value);
        if (isRed(root))
            root.isRed = false;
        if (oldValue == null)
            treeSize++;
        return oldValue;
    }

    // Recursive helper method for 'put'.
    Node putHelper(Node node, K key, V value) {
        if (node == null) {
            oldValue = null;
            return new Node(true, key, value, null, null);
        } else if (node.key.compareTo(key) > 0) {
            node.left = putHelper(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = putHelper(node.right, key, value);
        } else { // node.key == key
            oldValue = node.value;
            node.value = value;
        }
        return rebalance(node);
    }

    // Used by put, remove, putHelper and removeHelper,
    // in order to return the value previously stored in the node.
    private V oldValue;

    // Delete a key.
    public V remove(K key) {
        throw new UnsupportedOperationException("remove is not implemented yet");
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
        // 
        //    x                 y
        //   / \               / \
        //  A   y     ===>    x   C
        //     / \           / \
        //    B   C         A   B
        // 
        // Variables are named according to the picture above.
        Node x = node;
        Node A = x.left;
        Node y = x.right;
        Node B = y.left;
        Node C = y.right;
        // We also swap x's and y's colours (e.g. if x was black before, then y will be black afterwards).
        return new Node(x.isRed, y.key, y.value, new Node(y.isRed, x.key, x.value, A, B), C);
    }

    Node rotateRight(Node node) {
        // Right rotation.
        // 
        //      x              y
        //     / \            / \
        //    y   C   ===>   A   x
        //   / \                / \
        //  A   B              B   C
        // 
        // Variables are named according to the picture above.
        Node x = node;
        Node y = x.left;
        Node A = y.left;
        Node B = y.right;
        Node C = x.right;
        // We also swap x's and y's colours (e.g. if x was black before, then y will be black afterwards).
        return new Node(x.isRed, y.key, y.value, A, new Node(y.isRed, x.key, x.value, B, C));
    }

    // Iterate through all keys.
    // This is called when the user writes 'for (K key: bst) { ... }.'
    public Iterator<K> iterator() {
        // The easiest way to solve this is to add all keys to an
        // ArrayList, then iterate through that.
        ArrayList<K> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        return keys.iterator();
    }

    // Recursive helper method for 'iterator'
    void iteratorHelper(Node node, ArrayList<K> keys) {
        if (node == null) return;
        iteratorHelper(node.left, keys);
        keys.add(node.key);
        iteratorHelper(node.right, keys);
    }

    // Override 'toString' to print the contents of the red-black tree.
    public String toString() {
        StringBuilder str = new StringBuilder();
        boolean firstKey = true;
        for (K key: this) {
            V value = this.get(key);
            if (!firstKey) str.append(", ");
            str.append(key.toString() + "->" + value.toString());
            firstKey = false;
        }
        return "{" + str + "}";
    }

    // Some test code to check that the red-black tree is working
    public static void main(String[] args) {
        RedBlackMap<Integer, Integer> bst = new RedBlackMap<>();
        int[] keys = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6};
        int[] values = new int[keys.length];
        for (int i = 0; i < values.length; i++) values[i] = i;

        for (int i = 0; i < keys.length; i++) {
            bst.put(keys[i], values[i]);
            System.out.println(bst.size() + ": " + bst);
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            System.out.println(keys[i] + " -> " + bst.get(keys[i]));
            bst.checkInvariant();
        }
    }
}
