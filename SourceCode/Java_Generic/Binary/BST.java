import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: BST *** */
/* *** ODSATag: Header *** */
// A dictionary implemented using a binary search tree.
public class BST<K extends Comparable<K>, V> implements Iterable<K> {
    Node root = null;   // The root of the binary search tree.
/* *** ODSAendTag: Header *** */

/* *** ODSATag: Node *** */
    // A node in a binary search tree.
    class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
/* *** ODSAendTag: Node *** */

/* *** ODSATag: Invariant *** */
    // Check that the invariant holds.
    void checkInvariant() {
        checkInvariantHelper(root, null, null);
    }

    // Helper method for 'check_invariant'.
    // Checks that the node is the root of a valid BST, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(Node node, K lo, K hi) {
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
/* *** ODSAendTag: Invariant *** */
    
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
    public boolean containsKey(K key) {
        return get(key) != null;
    }

/* *** ODSATag: get *** */
    // Look up a key.
    public V get(K key) {
        return getHelper(root, key);
    }

    // Helper method for 'get'.
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
    // Returns the previous value associated with the key, or null if
    // the key wasn't previously present.
    public V put(K key, V value) {
        root = putHelper(root, key, value);
        return oldValue;
    }

    // Helper method for 'put'.
    // Stores the previous value in oldValue;
    Node putHelper(Node node, K key, V value) {
        if (node == null) {
            oldValue = null;
            return new Node(key, value, null, null);
        } else if (key.compareTo(node.key) < 0) {
            node.left = putHelper(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = putHelper(node.right, key, value);
        } else {
            oldValue = node.value;
            node.value = value;
        }
        return node;
    }

    // Used by putHelper and removeHelper, in order to return the
    // value previously stored in the node.
    private V oldValue;
/* *** ODSAendTag: put *** */

    // Delete a key.
    public V remove(K key) {
        root = removeHelper(root, key);
        return oldValue;
    }

    // Helper method for 'remove'.
    Node removeHelper(Node node, K key) {
        if (node == null) {
            oldValue = null;
            return null;
        } else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
            return node;
        } else { // key == node.key
            if (node.left == null) {
                oldValue = node.value;
                return node.right;
            } else if (node.right == null) {
                oldValue = node.value;
                return node.left;
            } else {
                Node lastNode = largestNode(node.left);
                K lastKey = lastNode.key;
                V lastValue = lastNode.value;
                // We can either use 'deletemax' (as in the text)
                // or just recursively call removeHelper here
                node.left = removeHelper(node.left, lastKey);
                oldValue = node.value;
                node.key = lastKey;
                node.value = lastValue;
                return node;
            }
        }
    }

    // Find the largest key.
    public K lastKey() {
        if (root == null)
            return null;
        else
            return largestNode(root).key;
    }

/* *** ODSATag: largestNode *** */
    // Find the node having the largest key.
    Node largestNode(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }
/* *** ODSAendTag: largestNode *** */

    // Iterate through all keys.
    // This is called when the user writes 'for (K key: bst) { ... }.'
    public Iterator<K> iterator() {
        // The easiest way to solve this is to add all keys to an
        // ArrayList, then iterate through that.
        ArrayList<K> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        return keys.iterator();
    }

/* *** ODSATag: iteratorHelper *** */
    // Helper method for 'iterator'
    void iteratorHelper(Node node, ArrayList<K> keys) {
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
/* *** ODSAendTag: printHelper *** */

    // Override 'toString' to print the contents of the BST.
    public String toString() {
        if (root == null) return "{}";
        return toStringHelper(root);
    }

    String toStringHelper(Node node) {
        if (node == null) return ".";
        return "{" + toStringHelper(node.left) + " " +
            node.key + "->" + node.value + " " + toStringHelper(node.right) + "}";
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
            System.out.println(keys[i] + " -> " + bst.get(keys[i]));
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
