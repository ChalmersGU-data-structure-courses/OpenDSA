
import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: AVLTree *** */
/* *** ODSATag: header *** */
// A dictionary implemented using an AVL tree.
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    Node root = null;   // The root of the AVL tree.
    int treeSize;       // The size of the tree.
    V oldValue;         // Internal temporary variable for storing the old value of a key.
/* *** ODSAendTag: header *** */

/* *** ODSATag: Node *** */
    // A node in an AVL tree.
    class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            // super(key, value, left, right);
            updateHeight();
            System.err.println("--> " + this);
        }

        int getHeight(Node node) {
            if (node == null) return 0;
            else return node.height;
        }

        void updateHeight() {
            height = 1 + Math.max(getHeight(left), getHeight(right));
        }

        int heightDiff() {
            return getHeight(left) - getHeight(right);
        }

        public String toString() {
            return "(" + left + " {" + key + ":" + value + "} " + right + ") ";
        }
    }
/* *** ODSAendTag: header *** */

/* *** ODSATag: invariant *** */
    // Check that the invariant holds.
    void checkInvariant() {
        checkInvariantHelper(root, null, null);
    }

    // Recursive helper method for 'check_invariant'.
    // Checks that the node is the root of a valid AVL tree, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(Node node, K lo, K hi) {
        if (node == null)
            return;
        if (lo != null && node.key.compareTo(lo) <= 0)
            throw new AssertionError("key too small");
        if (hi != null && node.key.compareTo(hi) >= 0)
            throw new AssertionError("key too big");
        if (node.heightDiff() > 1)
            throw new AssertionError("too left-leaning");
        if (node.heightDiff() < -1)
            throw new AssertionError("too right-leaning");
        // Keys in the left subtree should be < node.key
        checkInvariantHelper(node.left, lo, node.key);
        // Keys in the right subtree should be > node.key
        checkInvariantHelper(node.right, node.key, hi);
    }
/* *** ODSAendTag: invariant *** */

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

/* *** ODSATag: get *** */
    // Look up a key.
    public V get(K key) {
        return getHelper(root, key);
    }

    // Recursive helper method for 'get'.
    V getHelper(Node node, K key) {
        if (node == null)
            return null;
        else if (key.compareTo(node.key) < 0)
            return getHelper(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getHelper(node.right, key);
        else
            return node.value;
    }
/* *** ODSAendTag: get *** */

/* *** ODSATag: put *** */
    // Add a key-value pair, or update the value associated with an existing key.
    public V put(K key, V value) {
        oldValue = null;
        root = putHelper(root, key, value);
        if (oldValue == null)
            treeSize++;
        return oldValue;
    }

    // Recursive helper method for 'put'.
    Node putHelper(Node node, K key, V value) {
        if (node == null)
            return new Node(key, value, null, null);
        if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
            node.updateHeight();
        } else if (key.compareTo(node.key) > 0) {
            node.right = putHelper(node.right, key, value);
            node.updateHeight();
        } else {
            oldValue = node.value;
            node.value = value;
        }
        return rebalance(node);
    }
/* *** ODSAendTag: put *** */
    
/* *** ODSATag: remove *** */
    // Delete a key.
    public V remove(K key) {
        oldValue = null;
        root = removeHelper(root, key);
        if (oldValue != null)
            treeSize--;
        return oldValue;
    }

    // Recursive helper method for 'remove'.
    Node removeHelper(Node node, K key) {
        if (node == null)
            return null;
        else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
            node.updateHeight();
            return rebalance(node);
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
            node.updateHeight();
            return rebalance(node);
        } else { // key == node.key
            if (oldValue == null)
                oldValue = node.value;
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Node lastNode = lastNodeHelper(node.left);
                K lastKey = lastNode.key;
                V lastValue = lastNode.value;
                node.left = removeHelper(node.left, lastKey);
                node.key = lastKey;
                node.value = lastValue;
                node.updateHeight();
                return rebalance(node);
            }
        }
    }
/* *** ODSAendTag: remove *** */

/* *** ODSATag: rebalance *** */
    // Repair the AVL invariant by rebalancing the node.
    Node rebalance(Node node) {
        if (node == null) return node;
        int diff = node.heightDiff();

        if (diff == 2) { // Left-leaning
            int leftDiff = node.left.heightDiff();
            if (leftDiff == -1) { // Left-right - convert to left-left
                node.left = rotateLeft(node.left);
                node.updateHeight();
            }
            return rotateRight(node);
        } else if (diff == -2) { // Right-leaning
            int rightDiff = node.right.heightDiff();
            if (rightDiff == 1) { // Right-left - convert to right-right
                node.right = rotateRight(node.right);
                node.updateHeight();
            }
            return rotateLeft(node);
        } else
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
        return new Node(y.key, y.value, new Node(x.key, x.value, A, B), C);
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
        return new Node(y.key, y.value, A, new Node(x.key, x.value, B, C));
    }
/* *** ODSAendTag: remove *** */

    // Find the largest key.
    public K lastKey() {
        if (root == null)
            return null;
        else
            return lastNodeHelper(root).key;
    }

    // Recursive helper method for 'lastKey'.
    // Returns the node instead, as that's useful in 'removeHelper'.
    Node lastNodeHelper(Node node) {
        // This one is maybe easier to implement non-recursively :)
        while (node.right != null)
            node = node.right;
        return node;
    }

/* *** ODSATag: iterator *** */
    // Iterate through all keys.
    // This is called when the user writes 'for (Key key: bst) { ... }.'
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
/* *** ODSAendTag: iterator *** */
/* *** ODSAendTag: AVLTree *** */

    // Override 'toString' to print the contents of the BST.
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

    // Some test code to check that the AVL tree is working
    public static void main(String[] args) {
        AVLMap<Integer, Integer> bst = new AVLMap<>();
        int[] keys = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6};
        int[] values = new int[keys.length];
        for (int i = 0; i < values.length; i++) values[i] = i;

        for (int i = 0; i < keys.length; i++) {
            bst.put(keys[i], values[i]);
            System.out.println(bst.size() + ": " + bst);
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            System.out.println(bst.get(keys[i]));
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            bst.remove(keys[i]);
            System.out.println(bst.size() + ": " + bst);
            bst.checkInvariant();
        }
    }
/* *** ODSATag: AVLTree *** */
}
/* *** ODSAendTag: AVLTree *** */
