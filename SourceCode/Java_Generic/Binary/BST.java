import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: BST *** */
// A dictionary implemented using a binary search tree.
public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    // A node in a binary search tree.
    class Node {
        Key key;
        Value value;
        Node left;
        Node right;

        Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
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
    // Checks that the node is the root of a valid BST, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(Node node, Key lo, Key hi) {
        if (node == null) return;

        if (lo != null && node.key.compareTo(lo) <= 0)
            throw new AssertionError("key too small");

        if (hi != null && node.key.compareTo(hi) >= 0)
            throw new AssertionError("key too big");

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

/* *** ODSATag: get *** */
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
/* *** ODSAendTag: get *** */

/* *** ODSATag: put *** */
    // Add a key-value pair, or update the value associated with an existing key.
    public void put(Key key, Value value) {
        root = putHelper(root, key, value);
    }

    // Helper method for 'put'.
    Node putHelper(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, null, null);

        else if (key.compareTo(node.key) < 0)
            node.left = putHelper(node.left, key, value);

        else if (key.compareTo(node.key) > 0)
            node.right = putHelper(node.right, key, value);

        else
            node.value = value;

        return node;
    }
/* *** ODSAendTag: put *** */

    // Delete a key.
    public void remove(Key key) {
        root = removeHelper(root, key);
    }

    // Helper method for 'remove'.
    Node removeHelper(Node node, Key key) {
        if (node == null)
            return null;
        else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
            return node;
        } else { // key == node.key
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Node lastNode = lastNodeHelper(node.left);
                Key lastKey = lastNode.key;
                Value lastValue = lastNode.value;
                node.left = removeHelper(node.left, lastKey);
                node.key = lastKey;
                node.value = lastValue;
                return node;
            }
        }
    }

    // Find the largest key.
    public Key lastKey() {
        if (root == null)
            return null;
        else
            return lastNodeHelper(root).key;
    }

/* *** ODSATag: lastNodeHelper *** */
    // Helper method for 'lastKey'.
    // Returns the node instead, as that's useful in 'removeHelper'.
    Node lastNodeHelper(Node node) {
        // This one is maybe easier to implement non-recursively :)
        while (node.right != null)
            node = node.right;

        return node;
    }
/* *** ODSAendTag: lastNodeHelper *** */

    // Iterate through all keys.
    // This is called when the user writes 'for (Key key: bst) { ... }.'
    public Iterator<Key> iterator() {
        // The easiest way to solve this is to add all keys to an
        // ArrayList, then iterate through that.
        ArrayList<Key> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        return keys.iterator();
    }

/* *** ODSATag: iteratorHelper *** */
    // Helper method for 'iterator'
    void iteratorHelper(Node node, ArrayList<Key> keys) {
        if (node == null) return;
        iteratorHelper(node.left, keys);
        keys.add(node.key);
        iteratorHelper(node.right, keys);
    }
/* *** ODSATag: iteratorHelper *** */

/* *** ODSATag: printHelper *** */
    // An example inorder traversal.
    // Prints all node keys, in sorted order.
    void printHelper(Node node) {
        if (node == null) return;
        printHelper(node.left);
        System.out.println(node.key);
        printHelper(node.right);
    }
/* *** ODSATag: printHelper *** */

    // Override 'toString' to print the contents of the BST.
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

    // Some test code to check that the BST is working
    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();
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
/* *** ODSAendTag: BST *** */
