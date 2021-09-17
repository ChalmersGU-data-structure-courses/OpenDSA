#/* *** ODSATag: HuffmanNode *** */
# Huffman tree node implementation: Base class
class HuffBaseNode:
    def isLeaf(self): raise NotImplementedError
    def weight(self): raise NotImplementedError


# Huffman tree node: Leaf class 
class HuffLeafNode(HuffBaseNode):
    def __init__(self, el, wt):
        self.element = el
        self.weight = wt
    def value(self):
        return self.element
    def weight(self):
        return self.weight
    def isLeaf(self):
        return True


# Huffman tree node: Internal class
class HuffInternalNode(HuffBaseNode):
    def __init__(self, l, r, wt):
        self.left = l
        self.right = r
        self.weight = wt
    def left(self):
        return self.left
    def right(self):
        return self.right
    def weight(self):
        return self.weight
    def isLeaf(self):
        return False
#/* *** ODSAendTag: HuffmanNode *** */

#/* *** ODSATag: HuffmanTree *** */
# A Huffman coding tree
class HuffTree(Comparable):
    def __init__(self, el_or_pair, wt):
        if isinstance(el_or_pair, str):
            self.root = HuffLeafNode(el_or_pair, wt)
        else:
            l, r = el_or_pair
            self.root = HuffInternalNode(l, r, wt)
    def root(self):
        return self.root
    def weight(self):
        return root.weight()

    def __eq__(self, other): return self.weight() == other.weight()
    def __ne__(self, other): return self.weight() != other.weight()
    def __lt__(self, other): return self.weight() <  other.weight()
    def __le__(self, other): return self.weight() <= other.weight()
    def __gt__(self, other): return self.weight() >  other.weight()
    def __ge__(self, other): return self.weight() >= other.weight()
#/* *** ODSAendTag: HuffmanTree *** */

#/* *** ODSATag: HuffmanTreeBuild *** */
def buildTree(huffHeap) {
  while (huffHeap.heapsize() > 1) { # While two items left
    tmp1 = huffHeap.removemin()
    tmp2 = huffHeap.removemin()
    tmp3 = new HuffTree(tmp1.root(), tmp2.root(),
                            tmp1.weight() + tmp2.weight())
    huffHeap.insert(tmp3)   # Return new tree to heap
  }
  return huffHeap.getmin()  # Return the tree
}
#/* *** ODSAendTag: HuffmanTreeBuild *** */
