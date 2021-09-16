
#/* *** ODSATag: ListADT *** */
# List class ADT.
class List:
  def clear(self):
    """Remove all contents from the list, so it is once again empty."""
    raise NotImplementedError

  def insert(self, it):
    """Insert "it" at the current location."""
    # The client must ensure that the list's capacity is not exceeded.
    raise NotImplementedError

  def append(self, it):
    """Append "it" at the end of the list."""
    # The client must ensure that the list's capacity is not exceeded.
    raise NotImplementedError

  def remove(self):
    """Remove and return the current element."""
    raise NotImplementedError

  def moveToStart(self):
    """Set the current position to the start of the list."""
    raise NotImplementedError

  def moveToEnd(self):
    """Set the current position to the end of the list."""
    raise NotImplementedError

  def prev(self):
    """Move the current position one step left, no change if already at beginning."""
    raise NotImplementedError

  def next(self):
    """Move the current position one step right, no change if already at end."""
    raise NotImplementedError

  def length(self):
    """Return the number of elements in the list."""
    raise NotImplementedError

  def currPos(self):
    """Return the position of the current element."""
    raise NotImplementedError

  def moveToPos(self, pos):
    """Set the current position to "pos"."""
    raise NotImplementedError

  def isAtEnd(self):
    """Return true if current position is at end of the list."""
    raise NotImplementedError

  def getValue(self):
    """Return the current element."""
    raise NotImplementedError
  
  def isEmpty():
    """Tell if the list is empty or not."""
    raise NotImplementedError
#/* *** ODSAendTag: ListADT *** */
