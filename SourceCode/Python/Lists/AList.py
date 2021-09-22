import java.util.NoSuchElementException;

#/* *** ODSATag: AList *** */
# Array-based list implementation
#
# Note: Python arrays actually have dynamic length,
# but here we're pretending that their length is fixed on creation.
class AList(List):
  DEFAULT_SIZE = 10                 # Default size

  # Create a new list object with maximum size "size"
  def __init__(self, size=DEFAULT_SIZE):
    self.maxSize = size             # Maximum size of list
    self.listSize = 0               # Current # of list items
    self.curr = 0                   # Position of current element
    self.listArray = [None] * size  # Array holding list elements

  def clear(self):                  # Reinitialize the list
    self.listSize = self.curr = 0   # Simply reinitialize values

#/* *** ODSATag: AListInsert *** */
  # Insert "it" at current position
  def insert(self, it):
    if self.listSize >= self.maxSize:
      return false
    for i in range(self.listSize, self.curr, -1)  # Shift elements up
      self.listArray[i] = self.listArray[i-1]     # to make room
    self.listArray[self.curr] = it
    self.listSize += 1                            # Increment list size
    return true
#/* *** ODSAendTag: AListInsert *** */

#/* *** ODSATag: AListAppend *** */
  # Append "it" to list
  def append(self, it):
    if self.listSize >= self.maxSize:
      return false
    self.listArray[self.listSize] = it
    self.listSize += 1
    return true
#/* *** ODSAendTag: AListAppend *** */

#/* *** ODSATag: AListRemove *** */
  # Remove and return the current element
  def remove(self):
    if curr < 0 or curr >= self.listSize:        # No current element
      raise IndexError(f"remove() in AList has current of {self.curr}"
         f" and size of {self.listSize} that is not a a valid element")
    it = self.listArray[self.curr];              # Copy the element
    for i in range(self.curr, self.listSize-1):  # Shift them down
      self.listArray[i] = self.listArray[i+1]
    self.listSize -= 1;                          # Decrement size
    return it
#/* *** ODSAendTag: AListRemove *** */

  def moveToStart(self):  # Set to front
    self.curr = 0
  def moveToEnd(self):    # Set at end
    self.curr = self.listSize
  def prev(self):         # Move left
    if self.curr != 0:
      self.curr -= 1
  def next(self)          # Move right
    if self.curr < self.listSize:
      self.curr += 1
  def length(self):       # Return list size
    return self.listSize
  def currPos(self):      # Return current position
    return self.curr

  # Set current list position to "pos"
  def moveToPos(self, pos):
    if pos < 0 or pos > self.listSize:
      return false
    self.curr = pos
    return true

  # Return true if current position is at end of the list
  def isAtEnd(self):
    return self.curr == self.listSize

  # Return the current element
  def getValue(self):
    if self.curr < 0 or self.curr >= self.listSize:  # No current element
      raise IndexError(f"getValue() in AList has current of {self.curr}"
         f" and size of {self.listSize} that is not a a valid element")
    return self.listArray[self.curr]
 
  # Tell if the list is empty or not
  def isEmpty(self):
    return self.listSize == 0
#/* *** ODSAendTag: AList *** */
