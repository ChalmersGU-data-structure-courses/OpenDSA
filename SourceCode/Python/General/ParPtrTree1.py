#/* *** ODSATag: UF1 *** */
# General Tree implementation for UNION/FIND
class ParPtrTree:
    def __init__(self, size) {
        # Each node is its own root to start
        self.array = [-1] * size

    # Merge two subtrees if they are different
    def UNION(self, a, b):
        root1 = self.FIND(a)  # Find root of node a
        root2 = self.FIND(b)  # Find root of node b
        if root1 != root2:    # Merge two trees
            self.array[root1] = root2

    # Return the root of curr's tree
    def FIND(self, curr):
        while self.array[curr] != -1:
            curr = self.array[curr]
        return curr           # Now at root
#/* *** ODSAendTag: UF1 *** */
#/* *** ODSATag: UF2 *** */
#/* *** ODSAendTag: UF2 *** */
