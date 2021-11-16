import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: AVLTree *** */
// A dictionary implemented using an AVL tree.
public class AVLMap<K extends Comparable<K>, V> extends BSTMap<K, V> implements Map<K, V> {
    AVLNode root = null;   // The root of the binary search tree.
    V previousValue;

    // A node in an AVL tree.
    class AVLNode extends BSTNode {
        K key;
        V value;
        AVLNode left;
        AVLNode right;
        int height;

        AVLNode(K key, V value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            // super(key, value, left, right);
            updateHeight();
            System.err.println("--> " + this);
        }

        int getHeight(AVLNode node) {
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

    // Check that the invariant holds.
    void checkInvariant() {
        checkInvariantHelper(root, null, null);
    }

    // Helper method for 'check_invariant'.
    //
    // Checks that the node is the root of a valid AVL tree, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(AVLNode node, K lo, K hi) {
        if (node == null) return;

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

    // Add a key-value pair, or update the value associated with an existing key.
    public V put(K key, V value) {
        previousValue = null;
        root = putHelper(root, key, value);
        return previousValue;
    }

    // Helper method for 'put'.
    AVLNode putHelper(AVLNode node, K key, V value) {
        if (node == null)
            return new AVLNode(key, value, null, null);

        System.err.println(node + ": " + key + " = " + value);

        if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
            node.updateHeight();
        }

        else if (key.compareTo(node.key) > 0) {
            node.right = putHelper(node.right, key, value);
            node.updateHeight();
        }

        else {
            previousValue = node.value;
            node.value = value;
        }

        return rebalance(node);
    }

    
    // Delete a key.
    public V remove(K key) {
        previousValue = null;
        root = removeHelper(root, key);
        return previousValue;
    }

    // Helper method for 'remove'.
    AVLNode removeHelper(AVLNode node, K key) {
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
            previousValue = node.value;
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                BSTNode lastNode = lastNodeHelper(node.left);
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

    // Repair the AVL invariant by rebalancing the node.
    AVLNode rebalance(AVLNode node) {
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

    AVLNode rotateLeft(AVLNode node) {
        // Left rotation.

        //    x                y
        //   / \              / \
        //  A  y      ===>   x  C
        //    / \           / \
        //   B  C          A  B
        // """

        // Variables are named according to the picture above.
        AVLNode x = node;
        AVLNode A = x.left;
        AVLNode y = x.right;
        AVLNode B = y.left;
        AVLNode C = y.right;

        return new AVLNode(y.key, y.value, new AVLNode(x.key, x.value, A, B), C);
    }

    AVLNode rotateRight(AVLNode node) {
        // Right rotation.
        // 
        //      x             y
        //     / \           / \
        //    y  C    ===>  A  x
        //   / \              / \
        //  A  B             B  C

        // Variables are named according to the picture above.
        AVLNode x = node;
        AVLNode y = x.left;
        AVLNode A = y.left;
        AVLNode B = y.right;
        AVLNode C = x.right;

        return new AVLNode(y.key, y.value, A, new AVLNode(x.key, x.value, B, C));
    }

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
            System.out.println(bst);
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            System.out.println(bst.get(keys[i]));
            bst.checkInvariant();
        }

        for (int i = 0; i < keys.length; i++) {
            bst.remove(keys[i]);
            System.out.println(bst);
            bst.checkInvariant();
        }
    }
}
/* *** ODSAendTag: AVLTree *** */
