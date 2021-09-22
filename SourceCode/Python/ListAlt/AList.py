#/* *** ODSATag: AList *** */
class AListIndex(ListIndex):
    def __init__(self, posit, listSize):
        self.pos = posit
        self.listSize = listSize
    def prev(self):
        if self.pos > 0: self.pos -= 1
    def next(self):
        if self.pos < self.listSize: self.pos += 1

# Array-based list implementation
class AList(List):
    defaultSize = 10  # Default size
    def __init__(self, size=defaultSize):
        self.maxSize = size             # Maximum size of list
        self.listSize = 0               # Current # of list items
        self.listArray = [None] * size  # Array holding list elements

    def insert(self, it, where):
        if self.listSize >= self.maxSize:
            raise IndexError("List capacity exceeded, nothing inserted")
        pos = where.pos
        for i in range(self.listSize, pos, -1):      # Shift elements up
            self.listArray[i] = self.listArray[i-1]  # to make room
        self.listArray[pos] = it
        self.listSize += 1       # Increment list size

    def append(self, it):
        if self.listSize >= self.maxSize:
            raise IndexError("List capacity exceeded, nothing inserted")
        self.listArray[self.listSize] = it
        self.listSize += 1

    def remove(self, where):
        int pos = where.pos
        if where.pos < 0 or where.pos >= self.listSize  # No current element
            return None
        it = self.listArray[pos]               # Copy the element
        for i in range(pos, self.listSize-1):  # Shift them down
            self.listArray[i] = self.listArray[i+1]
        self.listSize -= 1  # Decrement size
        return it

    def length(self):
        return self.listSize

    def getStart(self):
        return AListIndex(0, self.listSize)
    
    def getEnd(self):
        return AListIndex(self.listSize, self.listSize)
    
    def pointToPos(self, pos):
        return AListIndex(pos, self.listSize)

    def getValue(self, where):
        if where.pos < 0 or where.pos >= self.listSize  # No current element
            return None
        return self.listArray[where.pos]
#/* *** ODSAendTag: AList *** */

