import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: AVLTree *** */
// A dictionary implemented using an AVL tree.
public class AVL<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    // A node in an AVL tree.
    class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        private int height;

        Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            updateHeight();
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
    }

    // The root of the binary search tree.
    Node root = null;

    // Check that the invariant holds.
    void checkInvariant() {
        checkInvariantHelper(root, null, null);
    }

    // Helper method for 'check_invariant'.
    //
    // Checks that the node is the root of a valid AVL tree, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(Node node, Key lo, Key hi) {
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

    // Return true if there are no keys.
    public boolean isEmpty() {
        return root == null;
    }

    // Return the number of keys.
    public int size() {
        return sizeHelper(root);
    }

    // Helper method for 'size'.
    int sizeHelper(Node node) {
        if (node == null) return 0;
        else return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    // Return true if the key has an associated value.
    public boolean containsKey(Key key) {
        return get(key) != null;
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

    // Add a key-value pair, or update the value associated with an existing key.
    // Returns the previous value associated with the key, or null if
    // the key wasn't previously present.
    public Value put(Key key, Value value) {
        root = putHelper(root, key, value);
        return oldValue;
    }

    // Helper method for 'put'.
    // Stores the previous value in oldValue;
    Node putHelper(Node node, Key key, Value value) {
        if (node == null) {
            oldValue = null;
            return new Node(key, value, null, null);
        } 

        else if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
            node.updateHeight();
        }

        else if (key.compareTo(node.key) > 0) {
            node.right = putHelper(node.right, key, value);
            node.updateHeight();
        }

        else {
            oldValue = node.value;
            node.value = value;
        }

        return rebalance(node);
    }

    // Used by putHelper and removeHelper, in order to return the
    // value previously stored in the node.
    private Value oldValue;

    // Delete a key.
    public Value remove(Key key) {
        root = removeHelper(root, key);
        return oldValue;
    }

    // Helper method for 'remove'.
    Node removeHelper(Node node, Key key) {
        if (node == null) {
            oldValue = null;
            return null;
        } else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
            node.updateHeight();
            return rebalance(node);
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
            node.updateHeight();
            return rebalance(node);
        } else { // key == node.key
            if (node.left == null) {
                oldValue = node.value;
                return node.right;
            } else if (node.right == null) {
                oldValue = node.value;
                return node.left;
            } else {
                Node lastNode = largestNode(node.left);
                Key lastKey = lastNode.key;
                Value lastValue = lastNode.value;
                node.left = removeHelper(node.left, lastKey);
                oldValue = node.value;
                node.key = lastKey;
                node.value = lastValue;
                node.updateHeight();
                return rebalance(node);
            }
        }
    }

    // Find the largest key.
    public Key lastKey() {
        if (root == null)
            return null;
        else
            return largestNode(root).key;
    }

    // Helper method for 'lastKey'.
    // Returns the node instead, as that's useful in 'removeHelper'.
    Node largestNode(Node node) {
        // This one is maybe easier to implement non-recursively :)
        while (node.right != null)
            node = node.right;

        return node;
    }

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

        return new Node(y.key, y.value, new Node(x.key, x.value, A, B), C);
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

        return new Node(y.key, y.value, A, new Node(x.key, x.value, B, C));
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

    // Override 'toString' to print the contents of the AVL tree.
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

    // Some test code to check that the AVL tree is working
    public static void main(String[] args) {
        AVL<Integer, Integer> bst = new AVL<>();
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
