#/* *** ODSATag: BST *** */
class Node:
    """A node in a binary search tree."""

    def __init__(self, key, value, left, right):
        self.key = key
        self.value = value
        self.left = left
        self.right = right

class BST:
    """A dictionary implemented using a binary search tree."""

    def __init__(self):
        self.root = None

    def check_invariant(self):
        """Check that the invariant holds."""

        self.check_invariant_helper(self.root, None, None)

    @staticmethod
    def check_invariant_helper(node, lo, hi):
        """Helper method for 'check_invariant'.

        Checks that the node is the root of a valid BST, and that
        all keys k satisfy lo < k < hi. The test lo < k is skipped
        if lo is None, and k < hi is skipped if hi is None."""

        if node is None: return

        if lo is not None and node.key <= lo:
            raise AssertionError("key too small", node.key, lo, hi)

        if hi is not None and node.key >= hi:
            raise AssertionError("key too big", node.key, lo, hi)

        # Keys in the left subtree should be < node.key
        BST.check_invariant_helper(node.left, lo, node.key)
        # Keys in the right subtree should be > node.key
        BST.check_invariant_helper(node.right, node.key, hi)

    def isEmpty(self):
        """Return true if there are no keys."""

        return self.root is None
    
    def size(self):
        """Return the number of keys."""

        return self.size_helper(self.root)

    @staticmethod
    def size_helper(node):
        """Helper method for 'size'."""

        if node is None: return 0
        else: return 1 + size_helper(node.left) + size_helper(node.right)

    def containsKey(self, key):
        """Return true if the key has an associated value."""

        return self.get(key) is not None

# /* *** ODSATag: get *** */
    def get(self, key):
        """Look up a key."""

        return self.get_helper(self.root, key)

    @staticmethod
    def get_helper(node, key):
        """Helper method for 'get'."""

        if node is None:
            return None
        elif key < node.key:
            return BST.get_helper(node.left, key)
        elif key > node.key:
            return BST.get_helper(node.right, key)
        else:
            return node.value
# /* *** ODSAendTag: get *** */

# /* *** ODSATag: put *** */
    def put(self, key, value):
        """Add a key-value pair, or update the value associated with
        an existing key.

        Returns the value previously associated with the key, or None
        if the key was not present."""

        self.root, old_value = self.put_helper(self.root, key, value)
        return old_value

    @staticmethod
    def put_helper(node, key, value):
        """Helper method for 'put'.

        Returns the updated node, and the value previously associated
        with the key."""

        if node is None:
            return Node(key, value, None, None), None
        elif key < node.key:
            node.left, old_value = BST.put_helper(node.left, key, value)
        elif key > node.key:
            node.right, old_value = BST.put_helper(node.right, key, value)
        else:
            old_value = node.value
            node.value = value
        return node, old_value
# /* *** ODSAendTag: put *** */

    def remove(self, key):
        """Delete a key.

        Returns the value previously associated with the key, or None
        if the key was not present."""

        self.root, old_value = self.remove_helper(self.root, key)
        return old_value

    @staticmethod
    def remove_helper(node, key):
        """Helper method for 'remove'.

        Returns the updated node, and the value previously associated
        with the key."""

        if node is None:
            return None, None
        elif key < node.key:
            node.left, old_value = BST.remove_helper(node.left, key)
            return node, old_value
        elif key > node.key:
            node.right, old_value = BST.remove_helper(node.right, key)
            return node, old_value
        else: # key == node.key
            if node.left is None:
                return node.right, node.value
            elif node.right is None:
                return node.left, node.value
            else:
                last_node = BST.largestNode(node.left)
                last_key = last_node.key
                last_val = last_node.value
                # We can either use 'deletemax' (as in the next)
                # or just recursively call removeHelper here
                node.left, _ = BST.remove_helper(node.left, last_key)
                node.key = last_key
                old_value = node.value
                node.value = last_val
                return node, old_value

    def lastKey(self):
        """Find the largest key."""

        if self.root is None:
            return None
        else:
            last_node = self.largestNode(self.root)
            return last_node.key

#/* *** ODSATag: largestNode *** */
    @staticmethod
    def largestNode(node):
        """Find the node having the largest key."""

        while node.right is not None:
            node = node.right

        return node
#/* *** ODSATag: largestNode *** */

    def __iter__(self):
        """Iterate through all keys.

        This is called when the user writes 'for key in bst: ...'."""

        return self.iter_helper(self.root)

    @staticmethod
    def iter_helper(node):
        """Helper method for '__iter__'."""

        # This method is a generator:
        # https://docs.python.org/3/howto/functional.html#generators
        # Generators are an easy way to make iterators

        if node is None:
            return
        else:
            for key in BST.iter_helper(node.left):
                yield key
            yield node.key
            for key in BST.iter_helper(node.right):
                yield key

# /* *** ODSATag: printHelper *** */
    @staticmethod
    def print_helper(node):
        """An example inorder traversal.
        Prints all node keys, in sorted order."""

        if node is None: return
        BST.print_helper(node.left)
        print(node.key)
        BST.print_helper(node.right)
# /* *** ODSAendTag: printHelper *** */

    def __getitem__(self, key):
        """This is called when the user writes 'x = bst[key]'."""

        return self.get(key)
    
    def __setitem__(self, key, value):
        """This is called when the user writes 'bst[key] = value'."""

        self.put(key, value)

    def __contains__(self, key):
        """This is called when the user writes 'key in bst'."""

        return self.containsKey(key)

    def __delitem__(self, key):
        """This is called when the user writes 'del bst[key]'."""

        self.remove(key)

    def __str__(self):
        """This is called to show the BST as a string."""

        # Use a dict comprehension to convert the BST into a dict:
        # https://docs.python.org/3/tutorial/datastructures.html#dictionaries
        # Then show the dict as a string.

        return str({key: self[key] for key in self})
        # This code is the same as:
        # d = {}
        # for key in self:
        #   d[key] = self[key]
        # return str(d)
        # Note that 'for key in self' calls self.__iter__ to produce
        # the keys, and 'self[key]' calls self.__getitem__.

    def __repr__(self):
        """This is called to show the BST as a string."""

        return repr({key: self[key] for key in self})

# Some code to check that the BST is working
if __name__ == '__main__':
    bst = BST()
    keys = [3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6]
    values = list(range(len(keys)))

    for i in range(len(keys)):
        bst[keys[i]] = values[i]
        print(bst)
        bst.check_invariant()
    for i in range(len(keys)):
        print(bst[keys[i]])
        bst.check_invariant()
    for i in range(len(keys)):
        del bst[keys[i]]
        print(bst)
        bst.check_invariant()
#/* *** ODSAendTag: BST *** */
