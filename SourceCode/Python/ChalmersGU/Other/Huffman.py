
from functools import total_ordering
from MinHeap import MinHeap

#/* *** ODSATag: HuffTree *** */
@total_ordering
class HuffTree:
    def __init__(self, elem=None, left=None, right=None, weight=None):
        self.elem = elem
        self.left = left
        self.right = right
        self.weight = weight

    def is_leaf(self):
        return self.left is None or self.right is None

    def __lt__(self, other):
        return self.weight < other.weight

    def __eq__(self, other):
        return self.weight == other.weight
#/* *** ODSAendTag: HuffTree *** */

    def encoding_map(self):
        if self.is_leaf():
            return {self.elem: ""}

        result = {}
        left_encoding = self.left.encoding_map()
        for ch, bits in left_encoding.items():
            result[ch] = "0" + bits
        right_encoding = self.right.encoding_map()
        for ch, bits in right_encoding.items():
            result[ch] = "1" + bits
        return result

    def __str__(self):
        enc = self.encoding_map()
        s = "\n".join(f"  {ch}: <{bits}>" for ch, bits in enc.items())
        return f"{self.weight}\n" + s

#/* *** ODSATag: HuffTreeBuild *** */
def build_huff_tree(frequencies):
    huff_heap = MinHeap()
    # Initialise the heap with singleton Huffman trees
    for ch, freq in frequencies.items():
        huff_heap.add(HuffTree(elem=ch, weight=freq))

    # While at least two trees left on heap
    while huff_heap.size() > 1:
        t1 = huff_heap.removeMin()
        t2 = huff_heap.removeMin()
        t3 = HuffTree(left=t1, right=t2, weight=t1.weight + t2.weight)
        # Return new tree to the heap
        huff_heap.add(t3)

    # Return the final Huffman tree
    return huff_heap.removeMin() 
#/* *** ODSAendTag: HuffTreeBuild *** */

if __name__=='__main__':
    freqs = {
        'a': 5,
        'b': 2,
        'c': 1,
        'd': 1,
        'r': 2,
        '!': 1,
    }
    tree = build_huff_tree(freqs)
    print(tree)

