
from API import List

#/* *** ODSATag: StaticArrayList *** */
#/* *** ODSATag: StaticArrayListInit *** */
class StaticArrayList(List):
    def __init__(self, capacity):
        self._internalArray = [None] * capacity
        self._listSize = 0
#/* *** ODSAendTag: StaticArrayListInit *** */

#/* *** ODSATag: StaticArrayListGetSet *** */
    def get(self, i):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        return self._internalArray[i]

    def set(self, i, x):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        old = self._internalArray[i]
        self._internalArray[i] = x
        return old
#/* *** ODSAendTag: StaticArrayListGetSet *** */

#/* *** ODSATag: StaticArrayListAdd *** */
    def add(self, i, x):
        if not (self._listSize < len(self._internalArray)): raise IndexError("array capacity exceeded")
        if not (0 <= i <= self._listSize):                  raise IndexError("array index out of range")
        self._listSize += 1
        for k in reversed(range(i+1, self._listSize)):
            self._internalArray[k] = self._internalArray[k-1]
        self._internalArray[i] = x
#/* *** ODSAendTag: StaticArrayListAdd *** */

#/* *** ODSATag: StaticArrayListRemove *** */
    def remove(self, i):
        if not (self._listSize > 0):      raise IndexError("remove from empty array")
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        x = self._internalArray[i]
        for k in range(i+1, self._listSize):
            self._internalArray[k-1] = self._internalArray[k]
        self._listSize -= 1
        self._internalArray[self._listSize] = None   # For garbage collection
        return x
#/* *** ODSAendTag: StaticArrayListRemove *** */

    def isEmpty(self):
        return self._listSize == 0

    def size(self):
        return self._listSize

    def __iter__(self):
        raise NotImplementedException("Left as an exercise.")
#/* *** ODSAendTag: StaticArrayList *** */


if __name__ == '__main__':
    a = StaticArrayList(25)
    for i in range(25): a.add(a.size(), chr(i+65))
    print(" ".join(a.get(i) for i in range(a.size())))
    for i in range(0, a.size(), 2): a.set(i, a.get(i) + a.get(i))
    print(" ".join(a.get(i) for i in range(a.size())))
    i = 0
    while i < a.size():
        a.remove(i)
        i += 3
    print(" ".join(a.get(i) for i in range(a.size())))
