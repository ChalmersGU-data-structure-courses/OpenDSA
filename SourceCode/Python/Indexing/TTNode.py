# 2-3 tree node implementation
class TTNode:
    def __init__(self, lkey=None, lval=None, rkey=None, rval=None, left=None, center=None, right=None):
        self.lkey = lkey     # The node's left key
        self.rkey = rkey     # The node's right key
        self.lval = lval     # The left record
        self.rval = rval     # The right record
        self.left = left     # Pointer to left child
        self.center = center # Pointer to middle child
        self.right = right   # Pointer to right child

    def isLeaf(self): return self.left is None
    def lchild(self): return self.left
    def rchild(self): return self.right
    def cchild(self): return self.center
    def lkey(self):   return self.lkey  # Left key
    def lval(self):   return self.lval  # Left value
    def rkey(self):   return self.rkey  # Right key
    def rval(self):   return self.rval  # Right value

    def setLeft(self, k, e):      self.lkey, self.lval = k, e
    def setRight(self, k, e):     self.rkey, self.rval = k, e
    def setLeftChild(self, it):   self.left = it
    def setCenterChild(self, it): self.center = it
    def setRightChild(self, it):  self.right = it
