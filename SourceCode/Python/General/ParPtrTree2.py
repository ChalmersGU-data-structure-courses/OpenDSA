# General Tree class implementation for UNION/FIND
class ParPtrTree:
    def __init__(self, size):
        self.array = [-1] * size
        self.weights = [1] * size

    # Determine if nodes are in different trees
    def differ(self, a, b):
        root1 = self.FIND(a)   # Find root of node a
        root2 = self.FIND(b)   # Find root of node b
        return root1 != root2  # Compare roots

    # Merge two subtrees with weighted union
#/* *** ODSATag: UnionFind *** */
    def UNION(self, a, b):
        root1 = self.FIND(a)   # Find root of node a
        root2 = self.FIND(b)   # Find root of node b
        if root1 != root2:     # Merge with weighted union
            if self.weights[root2] > self.weights[root1]:
                self.array[root1] = root2
                self.weights[root2] += self.weights[root1]
            else:
                self.array[root2] = root1;
                self.weights[root1] += self.weights[root2];
#/* *** ODSAendTag: UnionFind *** */

#/* *** ODSATag: PathCompress *** */
    # Return the root of curr's tree with path compression
    def FIND(self, curr):
        if self.array[curr] == -1:
            return curr  # At root
        self.array[curr] = self.FIND(self.array[curr])
        return self.array[curr];
#/* *** ODSAendTag: PathCompress *** */
