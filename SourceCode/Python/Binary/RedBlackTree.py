class Node:
    """A node in a red-black tree."""

    def __init__(self, is_red, key, value, left = None, right = None):
        self._is_red = is_red
        self.key = key
        self.value = value
        self.left = left
        self.right = right

    def is_red(self):
        if self is None:
            return False
        else:
            return self._is_red

    def is_black(self):
        return not Node.is_red(self)

    def __str__(self):
        return "Node(%s, %s, %s, %s, %s)" % \
            (self._is_red, self.key, self.value, self.left, self.right)

class RedBlackTree:
    """A dictionary implemented using a binary search tree."""

    def __init__(self):
        self.root = None

    def check_invariant(self):
        """Check that the invariant holds."""

        if Node.is_red(self.root):
            raise AssertionError("red root")

        self.check_invariant_recursive(self.root, None, None)

    @staticmethod
    def check_invariant_recursive(node, lo, hi):
        """Helper method for 'check_invariant'.

        Checks that the node is the root of a valid red-black tree, and that
        all keys k satisfy lo < k < hi. The test lo < k is skipped
        if lo is None, and k < hi is skipped if hi is None.

        Returns the "black height" of the tree."""

        if node is None: return 0

        if Node.is_red(node.right):
            raise AssertionError("red right child")

        if Node.is_red(node) and Node.is_red(node.left):
            raise AssertionError("red node with red left child")

        if lo is not None and node.key <= lo:
            raise AssertionError("key too small", node.key, lo, hi)

        if hi is not None and node.key >= hi:
            raise AssertionError("key too big", node.key, lo, hi)

        # Keys in the left subtree should be < node.key
        h1 = RedBlackTree.check_invariant_recursive(node.left, lo, node.key)
        # Keys in the right subtree should be > node.key
        h2 = RedBlackTree.check_invariant_recursive(node.right, node.key, hi)

        if h1 != h2:
            raise AssertionError("unbalanced tree")

        return h1 + (1 if Node.is_black(node) else 0)

    def add(self, key, value):
        """Add a key-value pair, or update the value associated with
        an existing key."""

        self.root = self.add_recursive(self.root, key, value)
        if Node.is_red(self.root):
            self.root._is_red = False

    @staticmethod
    def add_recursive(node, key, value):
        """Helper method for 'add'."""

        if node is None:
            return Node(True, key, value, None, None)
        elif key < node.key:
            node.left = RedBlackTree.add_recursive(node.left, key, value)
        elif key > node.key:
            node.right = RedBlackTree.add_recursive(node.right, key, value)
        else:
            node.value = value
        return RedBlackTree.rebalance(node)

    def get(self, key):
        """Look up a key."""

        return self.get_recursive(self.root, key)

    @staticmethod
    def get_recursive(node, key):
        """Helper method for 'get'."""

        if node is None:
            return None
        elif key < node.key:
            return RedBlackTree.get_recursive(node.left, key)
        elif key > node.key:
            return RedBlackTree.get_recursive(node.right, key)
        else:
            return node.value

    @staticmethod
    def rebalance(node):
        if node is None: return None
        
        # Skew
        if Node.is_red(node.right):
            node = RedBlackTree.rotate_left(node)

        # Split part 1
        if Node.is_red(node.left) and Node.is_red(node.left.left):
            node = RedBlackTree.rotate_right(node)

        # Split part 2
        if Node.is_red(node.left) and Node.is_red(node.right):
            node.left._is_red = False
            node.right._is_red = False
            node._is_red = True

        return node

    @staticmethod
    def rotate_left(node):
        node1 = node.left
        node2 = node.right.left
        node3 = node.right.right
        key1 = node.key
        value1 = node.value
        red1 = node._is_red
        key2 = node.right.key
        value2 = node.right.value
        red2 = node.right._is_red

        return Node(red1, key2, value2, Node(red2, key1, value1, node1, node2), node3)

    @staticmethod
    def rotate_right(node):
        node1 = node.left.left
        node2 = node.left.right
        node3 = node.right
        key1 = node.left.key
        value1 = node.left.value
        red1 = node.left._is_red
        key2 = node.key
        value2 = node.value
        red2 = node._is_red

        return Node(red2, key1, value1, node1, Node(red1, key2, value2, node2, node3))

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
            for key in RedBlackTree.iter_recursive(node.left):
                yield key
            yield node.key
            for key in RedBlackTree.iter_recursive(node.right):
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
        """This is called to show the red-black tree as a string."""

        # Use a dict comprehension to convert the red-black tree into a dict:
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
        """This is called to show the red-black tree as a string."""

        return repr({key: self[key] for key in self})

if __name__ == '__main__':
    bst = RedBlackTree()
    keys = [3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6]
    values = list(range(len(keys)))

    for i in range(len(keys)):
        bst[keys[i]] = values[i]
        print(bst.root)
        bst.check_invariant()
    for i in range(len(keys)):
        print(bst[keys[i]])
        bst.check_invariant()
