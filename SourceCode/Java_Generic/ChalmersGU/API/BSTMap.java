import java.util.ArrayList;
import java.util.Iterator;

/* *** ODSATag: BST *** */
// A dictionary implemented using a binary search tree.
class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    BSTNode root = null;  // The root of the binary search tree.
    V previousValue;

/* *** ODSATag: Node *** */
    // A node in a binary search tree.
    class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value, BSTNode left, BSTNode right) {
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
    //
    // Checks that the node is the root of a valid BST, and that
    // all keys k satisfy lo < k < hi. The test lo < k is skipped
    // if lo is None, and k < hi is skipped if hi is None.
    void checkInvariantHelper(BSTNode node, K lo, K hi) {
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
    int sizeHelper(BSTNode node) {
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
    V getHelper(BSTNode node, K key) {
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
        previousValue = null;
        root = putHelper(root, key, value);
        return previousValue;
    }

    // Helper method for 'put'.
    BSTNode putHelper(BSTNode node, K key, V value) {
        if (node == null)
            return new BSTNode(key, value, null, null);

        else if (key.compareTo(node.key) < 0)
            node.left = putHelper(node.left, key, value);

        else if (key.compareTo(node.key) > 0)
            node.right = putHelper(node.right, key, value);

        else {
            previousValue = node.value;
            node.value = value;
        }

        return node;
    }
/* *** ODSAendTag: put *** */

/* *** ODSATag: remove *** */
    // Delete a key.
    public V remove(K key) {
        previousValue = null;
        root = removeHelper(root, key);
        return previousValue;
    }

    // Helper method for 'remove'.
    BSTNode removeHelper(BSTNode node, K key) {
        if (node == null)
            return null;
        else if (key.compareTo(node.key) < 0) {
            node.left = removeHelper(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = removeHelper(node.right, key);
            return node;
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
                return node;
            }
        }
    }
/* *** ODSAendTag: remove *** */

    // Find the largest key.
    public K lastKey() {
        if (root == null)
            return null;
        else
            return lastNodeHelper(root).key;
    }

/* *** ODSATag: lastNodeHelper *** */
    // Helper method for 'lastKey'.
    // Returns the node instead, as that's useful in 'removeHelper'.
    BSTNode lastNodeHelper(BSTNode node) {
        // This one is maybe easier to implement non-recursively :)
        while (node.right != null)
            node = node.right;

        return node;
    }
/* *** ODSAendTag: lastNodeHelper *** */

    // Iterate through all keys.
    // This is called when the user writes 'for (Key key: bst) { ... }.'
    public Iterator<K> iterator() {
        // The easiest way to solve this is to add all keys to an
        // ArrayList, then iterate through that.
        ArrayList<K> keys = new ArrayList<>();
        iteratorHelper(root, keys);
        return keys.iterator();
    }

/* *** ODSATag: iteratorHelper *** */
    // Helper method for 'iterator'
    void iteratorHelper(BSTNode node, ArrayList<K> keys) {
        if (node == null) return;
        iteratorHelper(node.left, keys);
        keys.add(node.key);
        iteratorHelper(node.right, keys);
    }
/* *** ODSATag: iteratorHelper *** */

/* *** ODSATag: printHelper *** */
    // An example inorder traversal.
    // Prints all node keys, in sorted order.
    void printHelper(BSTNode node) {
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

        for (K key: this) {
            V value = this.get(key);

            if (!firstKey) str.append(", ");
            str.append(key.toString() + "->" + value.toString());
            firstKey = false;
        }

        return "{" + str + "}";
    }

    // Some test code to check that the BST is working
    public static void main(String[] args) {
        BSTMap<Integer, Integer> bst = new BSTMap<>();
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
