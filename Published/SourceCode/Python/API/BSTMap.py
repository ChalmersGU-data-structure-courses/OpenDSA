
from API import Map

# Python does not have internal classes, so we have to make the tree node class standalone.
class Node:
    """A node in a binary search tree."""
    def __init__(self, key, value, left, right):
        self.key = key
        self.value = value
        self.left = left
        self.right = right


class BSTMap(Map):
    """A dictionary implemented using a binary search tree."""

    def __init__(self):
        self.root = None
        self.treeSize = 0

    def check_invariant(self):
        """Check that the invariant holds."""
        size = self.check_invariant_helper(self.root, None, None)
        assert size == self.treeSize, "wrong tree size"

    @staticmethod
    def check_invariant_helper(node, lo, hi):
        """Recursive helper method for 'check_invariant'.
        Checks that the node is the root of a valid BST, and that
        all keys k satisfy lo < k < hi. The test lo < k is skipped
        if lo is None, and k < hi is skipped if hi is None."""

        if node is None: return 0

        assert lo is None or node.key > lo, "key too small"
        assert hi is None or node.key < hi, "key too big"

        # Keys in the left subtree should be < node.key
        # Keys in the right subtree should be > node.key
        return (1 + 
                BSTMap.check_invariant_helper(node.left, lo, node.key) +
                BSTMap.check_invariant_helper(node.right, node.key, hi))

    def isEmpty(self):
        """Return true if there are no keys."""
        return self.root is None
    
    def size(self):
        """Return the number of keys."""
        return self.treeSize

    def containsKey(self, key):
        """Return true if the key has an associated value."""
        return self.get(key) is not None

    def get(self, key):
        """Look up a key."""
        return self.get_helper(self.root, key)

    @staticmethod
    def get_helper(node, key):
        """Helper method for 'get'."""
        if node is None:
            return None
        elif node.key > key:
            return BSTMap.get_helper(node.left, key)
        elif node.key < key:
            return BSTMap.get_helper(node.right, key)
        else:
            return node.value

    def put(self, key, value):
        """Add a key-value pair, or update the value associated with an existing key. 
        Returns the value previously associated with the key, 
        or None if the key was not present."""
        self.root, old_value = self.put_helper(self.root, key, value)
        if old_value is None:
            self.treeSize += 1
        return old_value

    @staticmethod
    def put_helper(node, key, value):
        """Recursive helper method for 'put'.
        Returns the updated node, and the value previously associated with the key."""
        if node is None:
            return Node(key, value, None, None), None
        elif node.key > key:
            node.left, old_value = BSTMap.put_helper(node.left, key, value)
        elif node.key < key:
            node.right, old_value = BSTMap.put_helper(node.right, key, value)
        else: # node.key == key
            old_value = node.value
            node.value = value
        return node, old_value

    def remove(self, key):
        """Delete a key.
        Returns the value previously associated with the key, 
        or None if the key was not present."""
        self.root, old_value = self.remove_helper(self.root, key)
        if old_value is not None:
            self.treeSize -= 1
        return old_value

    @staticmethod
    def remove_helper(node, key):
        """Helper method for 'remove'.
        Returns the updated node, and the value previously associated with the key."""
        if node is None:
            return None, None
        elif node.key > key:
            node.left, old_value = BSTMap.remove_helper(node.left, key)
            return node, old_value
        elif node.key < key:
            node.right, old_value = BSTMap.remove_helper(node.right, key)
            return node, old_value
        else: # node.key == key
            if node.left is None:
                return node.right, node.value
            elif node.right is None:
                return node.left, node.value
            else:
                predecessor = BSTMap.largestNode(node.left)
                old_value = node.value
                node.key = predecessor.key
                node.value = predecessor.value
                node.left, _ = BSTMap.remove_helper(node.left, predecessor.key)
                return node, old_value

    def lastKey(self):
        """Find the largest key."""
        if self.root is None:
            return None
        else:
            return self.largestNode(self.root).key

    @staticmethod
    def largestNode(node):
        """Find the node having the largest key."""
        while node.right is not None:
            node = node.right
        return node

    def __iter__(self):
        """Iterate through all keys.
        This is called when the user writes 'for key in bst: ...'."""
        return self.iter_helper(self.root)

    @staticmethod
    def iter_helper(node):
        """Helper method for '__iter__'."""

        # This method is a generator:
        # https://docs.python.org/3/howto/functional.html#generators
        # Generators are an easy way to make iterators.
        if node is None:
            return
        else:
            for key in BSTMap.iter_helper(node.left):
                yield key
            yield node.key
            for key in BSTMap.iter_helper(node.right):
                yield key

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

    @staticmethod
    def print_helper(node):
        """An example inorder traversal.
        Prints all node keys, in sorted order."""
        if node is None: return
        BSTMap.print_helper(node.left)
        print(node.key)
        BSTMap.print_helper(node.right)

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
    bst = BSTMap()
    keys = [3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6]
    values = list(range(len(keys)))

    for i in range(len(keys)):
        bst[keys[i]] = values[i]
        print(bst)
        bst.check_invariant()
    for i in range(len(keys)):
        print(keys[i], "->", bst[keys[i]])
        bst.check_invariant()
    for i in range(len(keys)):
        del bst[keys[i]]
        print(bst)
        bst.check_invariant()
