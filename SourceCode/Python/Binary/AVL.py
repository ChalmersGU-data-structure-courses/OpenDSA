class Node:
    """A node in an AVL tree."""

    def __init__(self, key, value, left = None, right = None):
        self.key = key
        self.value = value
        self._left = left
        self._right = right
        self.update_height()

    @property
    def left(self):
        return self._left

    @property
    def right(self):
        return self._right

    @left.setter
    def left(self, value):
        self._left = value
        self.update_height()

    @right.setter
    def right(self, value):
        self._right = value
        self.update_height()

    def height(self):
        if self is None:
            return 0
        else:
            return self._height

    def height_diff(self):
        return Node.height(self.left) - Node.height(self.right)

    def update_height(self):
        self._height = max(Node.height(self.left), Node.height(self.right))+1

class AVL:
    """A dictionary implemented using a binary search tree."""

    def __init__(self):
        self.root = None

    def check_invariant(self):
        """Check that the invariant holds."""

        self.check_invariant_recursive(self.root, None, None)

    @staticmethod
    def check_invariant_recursive(node, lo, hi):
        """Helper method for 'check_invariant'.

        Checks that the node is the root of a valid AVL tree, and that
        all keys k satisfy lo < k < hi. The test lo < k is skipped
        if lo is None, and k < hi is skipped if hi is None."""

        if node is None: return

        if node.height_diff() > 1:
            raise AssertionError("too left-leaning")
        if node.height_diff() < -1:
            raise AssertionError("too right-leaning")

        if lo is not None and node.key <= lo:
            raise AssertionError("key too small", node.key, lo, hi)

        if hi is not None and node.key >= hi:
            raise AssertionError("key too big", node.key, lo, hi)

        # Keys in the left subtree should be < node.key
        AVL.check_invariant_recursive(node.left, lo, node.key)
        # Keys in the right subtree should be > node.key
        AVL.check_invariant_recursive(node.right, node.key, hi)

    def add(self, key, value):
        """Add a key-value pair, or update the value associated with
        an existing key."""

        self.root = self.add_recursive(self.root, key, value)

    @staticmethod
    def add_recursive(node, key, value):
        """Helper method for 'add'."""

        if node is None:
            return Node(key, value, None, None)
        elif key < node.key:
            node.left = AVL.add_recursive(node.left, key, value)
        elif key > node.key:
            node.right = AVL.add_recursive(node.right, key, value)
        else:
            node.value = value
        return AVL.rebalance(node)

    def get(self, key):
        """Look up a key."""

        return self.get_recursive(self.root, key)

    @staticmethod
    def get_recursive(node, key):
        """Helper method for 'get'."""

        if node is None:
            return None
        elif key < node.key:
            return AVL.get_recursive(node.left, key)
        elif key > node.key:
            return AVL.get_recursive(node.right, key)
        else:
            return node.value

    def delete(self, key):
        """Delete a key."""

        self.root = self.delete_recursive(self.root, key)

    @staticmethod
    def delete_recursive(node, key):
        """Helper method for 'delete'."""

        if node is None:
            return None
        elif key < node.key:
            node.left = AVL.delete_recursive(node.left, key)
            return AVL.rebalance(node)
        elif key > node.key:
            node.right = AVL.delete_recursive(node.right, key)
            return AVL.rebalance(node)
        else: # key == node.key
            if node.left is None:
                return node.right
            elif node.right is None:
                return node.left
            else:
                maxkey, maxval = AVL.max_recursive(node.left)
                node.left = AVL.delete_recursive(node.left, maxkey)
                node.key = maxkey
                node.value = maxval
                return AVL.rebalance(node)

    def max(self):
        """Find the largest key."""

        if self.root is None:
            return None
        else:
            return self.max_recursive(self.root)

    @staticmethod
    def max_recursive(node):
        """Helper method for 'max'."""

        # This one is maybe easier to implement non-recursively :)
        while node.right is not None:
            node = node.right

        return node.key, node.value

    @staticmethod
    def rebalance(node):
        if node is None: return None
        diff = node.height_diff()
        if diff == 2:
            left_diff = node.left.height_diff()
            if left_diff == -1:
                node.left = AVL.rotate_left(node.left)
            return AVL.rotate_right(node)
        elif diff == -2:
            right_diff = node.right.height_diff()
            if right_diff == 1:
                node.right = AVL.rotate_right(node.right)
            return AVL.rotate_left(node)
        else:
            return node

    @staticmethod
    def rotate_left(node):
        node1 = node.left
        node2 = node.right.left
        node3 = node.right.right
        key1 = node.key
        value1 = node.value
        key2 = node.right.key
        value2 = node.right.value

        return Node(key2, value2, Node(key1, value1, node1, node2), node3)

    @staticmethod
    def rotate_right(node):
        node1 = node.left.left
        node2 = node.left.right
        node3 = node.right
        key1 = node.left.key
        value1 = node.left.value
        key2 = node.key
        value2 = node.value

        return Node(key1, value1, node1, Node(key2, value2, node2, node3))

    def __iter__(self):
        """Iterate through all keys.

        This is called when the user writes 'for key in bst: ...'."""

        return self.iter_recursive(self.root)

    @staticmethod
    def iter_recursive(node):
        """Helper method for '__iter__'."""

        # This method is a generator:
        # https://docs.python.org/3/howto/functional.html#generators
        # Generators are an easy way to make iterators

        if node is None:
            return
        else:
            for key in AVL.iter_recursive(node.left):
                yield key
            yield node.key
            for key in AVL.iter_recursive(node.right):
                yield key

    def __getitem__(self, key):
        """This is called when the user writes 'x = bst[key]'."""

        return self.get(key)
    
    def __setitem__(self, key, value):
        """This is called when the user writes 'bst[key] = value'."""

        self.add(key, value)

    def __delitem__(self, key):
        """This is called when the user writes 'del bst[key]'."""

        self.delete(key)

    def __str__(self):
        """This is called to show the AVL as a string."""

        # Use a dict comprehension to convert the AVL into a dict:
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
        """This is called to show the AVL as a string."""

        return repr({key: self[key] for key in self})

if __name__ == '__main__':
    bst = AVL()
    keys = [3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6]
    values = list(range(len(keys)))

    for i in range(len(keys)):
        bst[keys[i]] = values[i]
        print(Node.height(bst.root), bst)
        bst.check_invariant()
    for i in range(len(keys)):
        print(bst[keys[i]])
        bst.check_invariant()
    for i in range(len(keys)):
        del bst[keys[i]]
        print(Node.height(bst.root), bst)
        bst.check_invariant()
