
from API import Stack, Iterator

#/* *** ODSATag: DynamicArrayStack *** */
#/* *** ODSATag: DynamicArrayStackInit *** */
class DynamicArrayStack(Stack):
    def __init__(self):
        self._internalArray = [None]
        self._top = 0
#/* *** ODSAendTag: DynamicArrayStackInit *** */

#/* *** ODSATag: DynamicArrayStackPush *** */
    def push(self, x):
        if self._top >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
        self._internalArray[self._top] = x
        self._top += 1
#/* *** ODSAendTag: DynamicArrayStackPush *** */

#/* *** ODSATag: DynamicArrayStackPeek *** */
    def peek(self):
        if not (self._top > 0): raise IndexError("peek from empty stack")
        return self._internalArray[self._top-1]
#/* *** ODSAendTag: DynamicArrayStackPeek *** */

#/* *** ODSATag: DynamicArrayStackPop *** */
    def pop(self):
        if not (self._top > 0): raise IndexError("pop from empty stack")
        self._top -= 1
        x = self._internalArray[self._top]
        self._internalArray[self._top] = None   # For garbage collection
        if self._top <= len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return x
#/* *** ODSAendTag: DynamicArrayStackPop *** */

#/* *** ODSATag: DynamicArrayStackResize *** */
    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
        for i in range(self._top):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray
#/* *** ODSAendTag: DynamicArrayStackResize *** */

    def isEmpty(self):
        return self._top == 0

    def size(self):
        return self._top

#/* *** ODSATag: DynamicArrayStackIterator *** */
    def __iter__(self):
        return DynamicArrayStackIterator(self._internalArray, self._top)

# Python does not have internal classes, so we have to make the iterator standalone.
class DynamicArrayStackIterator(Iterator):
    def __init__(self, array, size):
        self._array = array
        self._size = size
        self._index = size

    def __iter__(self):
        return self

    def __next__(self):
        self._index -= 1
        if self._index < 0:
            raise StopIteration
        return self._array[self._index]
#/* *** ODSAendTag: DynamicArrayStackIterator *** */
#/* *** ODSAendTag: DynamicArrayStack *** */


def _pprint(l):
    print(len(l._internalArray), "[", "- " * (len(l._internalArray) - l.size()) + "|",
               " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = DynamicArrayStack()
    for i in range(23):
        a.push(chr(i+65))
        if a.size() % 5 == 0:
            _pprint(a)
    _pprint(a)
    while not a.isEmpty():
        assert a.peek() == a.pop(), (a,)
        if a.size() % 3 == 2:
            _pprint(a)
    _pprint(a)
