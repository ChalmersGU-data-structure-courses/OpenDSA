
from API import Stack

#/* *** ODSATag: LinkedStack *** */
#/* *** ODSATag: LinkedStackInit *** */
class LinkedStack(Stack):
    def __init__(self):
        self._top = None      # Pointer to top of stack
        self._stackSize = 0   # Size of stack
#/* *** ODSAendTag: LinkedStackInit *** */

#/* *** ODSATag: LinkedStackPush *** */
    def push(self, x):
        self._top = LinkedStackNode(x, self._top)
        self._stackSize += 1
#/* *** ODSAendTag: LinkedStackPush *** */

#/* *** ODSATag: LinkedStackPeek *** */
    def peek(self):
        if not (self._stackSize > 0): raise IndexError("peek from empty stack")
        return self._top.elem
#/* *** ODSAendTag: LinkedStackPeek *** */

#/* *** ODSATag: LinkedStackPop *** */
    def pop(self):
        if not (self._stackSize > 0): raise IndexError("pop from empty stack")
        removed = self._top
        self._top = removed.next
        removed.next = None   # For garbage collection
        self._stackSize -= 1
        return removed.elem
#/* *** ODSAendTag: LinkedStackPop *** */

    def isEmpty(self):
        return self._stackSize == 0

    def size(self):
        return self._stackSize

#/* *** ODSATag: LinkedStackIterator *** */
    def __iter__(self):
        current = self._top
        while current is not None:
            yield current.elem
            current = current.next
#/* *** ODSAendTag: LinkedStackIterator *** */


#/* *** ODSATag: LinkedStackNode *** */
# Python does not have internal classes, so we have to make the stack node standalone.
class LinkedStackNode:
    def __init__(self, elem, next):
        self.elem = elem   # Value for this node
        self.next = next   # Pointer to next node in stack
#/* *** ODSAendTag: LinkedStackNode *** */
#/* *** ODSAendTag: LinkedStack *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print("[", " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = LinkedStack()
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
