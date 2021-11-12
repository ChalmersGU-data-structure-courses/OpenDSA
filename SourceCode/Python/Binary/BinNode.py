# /* *** ODSATag: BinNode *** */
class BinNode: # Binary tree node ADT
  # Get and set the element value
  def value(self): ...
  def setValue(self, value): ...

  # Return the children
  def left(self): ... # returns a BinNode
  def right(self): ... # returns a BinNode

  # return True if a leaf node, False otherwise
  def isLeaf(self): ...
# /* *** ODSAendTag: BinNode *** */
