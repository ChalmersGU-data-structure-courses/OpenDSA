
from API import Stack

#/* *** ODSATag: DynamicArrayStack *** */
#/* *** ODSATag: DynamicArrayStackInit *** */
class DynamicArrayStack(Stack):
    _minCapacity = 8
    _minLoadFactor = 0.5
    _capacityMultiplier = 1.5

    def __init__(self):
        self._internalArray = [None] * self._minCapacity   # Internal array containing the stack elements
        self._stackSize = 0                                # Size of stack, and index of the next free slot
#/* *** ODSAendTag: DynamicArrayStackInit *** */

#/* *** ODSATag: DynamicArrayStackPush *** */
    def push(self, x):
        if self._stackSize >= len(self._internalArray):
            self._resizeArray(len(self._internalArray) * self._capacityMultiplier)
        self._internalArray[self._stackSize] = x
        self._stackSize += 1
#/* *** ODSAendTag: DynamicArrayStackPush *** */

#/* *** ODSATag: DynamicArrayStackPeek *** */
    def peek(self):
        if not (self._stackSize > 0): raise IndexError("peek from empty stack")
        return self._internalArray[self._stackSize-1]
#/* *** ODSAendTag: DynamicArrayStackPeek *** */

#/* *** ODSATag: DynamicArrayStackPop *** */
    def pop(self):
        if not (self._stackSize > 0): raise IndexError("pop from empty stack")
        self._stackSize -= 1
        x = self._internalArray[self._stackSize]
        self._internalArray[self._stackSize] = None   # For garbage collection
        if self._stackSize <= len(self._internalArray) * self._minLoadFactor:
            self._resizeArray(len(self._internalArray) / self._capacityMultiplier)
        return x
#/* *** ODSAendTag: DynamicArrayStackPop *** */

#/* *** ODSATag: DynamicArrayStackResize *** */
    def _resizeArray(self, newCapacity):
        if newCapacity < self._minCapacity: return
        newArray = [None] * int(newCapacity)
        for i in range(self._stackSize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray
#/* *** ODSAendTag: DynamicArrayStackResize *** */

    def isEmpty(self):
        return self._stackSize == 0

    def size(self):
        return self._stackSize

#/* *** ODSATag: DynamicArrayStackIterator *** */
    def __iter__(self):
        for i in reversed(range(self._stackSize)):
            yield self._internalArray[i]
#/* *** ODSAendTag: DynamicArrayStackIterator *** */
#/* *** ODSAendTag: DynamicArrayStack *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print(len(l._internalArray), "[", "- " * (len(l._internalArray) - l.size()) + "|",
               " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = DynamicArrayStack()
    for i in range(23):
        a.push(chr(i+65))
        if a.size() % 5 == 0:
            _printList(a)
    _printList(a)
    while not a.isEmpty():
        assert a.peek() == a.pop(), (a,)
        if a.size() % 3 == 2:
            _printList(a)
    _printList(a)
