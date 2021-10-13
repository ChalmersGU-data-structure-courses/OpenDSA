
from API import List

#/* *** ODSATag: StaticArrayList *** */
#/* *** ODSATag: StaticArrayListInit *** */
class StaticArrayList(List):
    def __init__(self, capacity):
        self._internalArray = [None] * capacity   # Internal array containing the list elements
        self._listSize = 0                        # Size of list
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


def _pprint(l):
    print(l.size(), "[", " ".join(l.get(i) for i in range(l.size())), "|",
              "- " * (len(l._internalArray) - l.size()) + "]", len(l._internalArray))

if __name__ == '__main__':
    a = StaticArrayList(25)
    for i in range(20):
        a.add(a.size(), chr(i+65))
        if i % 5 == 0:
            _pprint(a)
    _pprint(a)
    for i in range(0, a.size(), 2): a.set(i, a.get(i).lower())
    _pprint(a)
    for _ in range(4):
        for i in range(a.size()-1, -1, -3): a.remove(i)
        _pprint(a)
