
from API import Collection, Iterator

#/* *** ODSATag: DynamicArray *** */
#/* *** ODSATag: DynamicArrayInit *** */
class DynamicArray(Collection):
    def __init__(self):
        self._internalArray = [None]
        self._arraySize = 0
#/* *** ODSAendTag: DynamicArrayInit *** */

    def get(self, i):
        if not (0 <= i < self._arraySize):
            raise IndexError("array index out of range")
        return self._internalArray[i]

    def set(self, i, x):
        if not (0 <= i < self._arraySize):
            raise IndexError("array index out of range")
        old = self._internalArray[i]
        self._internalArray[i] = x
        return old

#/* *** ODSATag: DynamicArrayAppend *** */
    def append(self, x):
        if self._arraySize >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
        self._internalArray[self._arraySize] = x
        self._arraySize += 1
#/* *** ODSAendTag: DynamicArrayAppend *** */

#/* *** ODSATag: DynamicArrayRemove *** */
    def removeLast(self):
        if self._arraySize == 0:
            raise IndexError("remove from empty array")
        self._arraySize -= 1
        removed = self._internalArray[self._arraySize]
        self._internalArray[self._arraySize] = None  # to be able to garbage collect the removed element
        if self._arraySize < len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return removed
#/* *** ODSAendTag: DynamicArrayRemove *** */

#/* *** ODSATag: DynamicArrayResize *** */
    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
        for i in range(self._arraySize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray
#/* *** ODSAendTag: DynamicArrayResize *** */

    def isEmpty(self):
        return self._arraySize == 0

    def size(self):
        return self._arraySize

#/* *** ODSATag: DynamicArrayIterator *** */
    def iterator(self):
        return DynamicArrayIterator(self._internalArray, self._arraySize)

# Python does not have internal classes, so we have to make the iterator standalone.
class DynamicArrayIterator(Iterator):
    def __init__(self, array, size):
        self._array = array
        self._size = size
        self._index = -1

    def __iter__(self):
        return self

    def __next__(self):
        self._index += 1
        if self._index >= self._size:
            raise StopIteration
        return self._array[self._index]
#/* *** ODSAendTag: DynamicArrayIterator *** */
#/* *** ODSAendTag: DynamicArray *** */


if __name__ == '__main__':
    a = DynamicArray()
    for i in range(25): a.append(chr(i+65))
    print(" ".join(a.iterator()))
    for i in range(0, a.size(), 2): a.set(i, a.get(i) + a.get(i))
    print(" ".join(a.iterator()))
    i = 0
    while i < a.size():
        a.removeLast()
        i += 3
    print(" ".join(a.get(i) for i in range(a.size())))
