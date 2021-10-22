
from API import List, Iterator

class LinkedList(List):
    def __init__(self):
        self._head = None    # Pointer to list header
        self._listSize = 0   # Size of list

    def get(self, i):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        current = self._head
        for _ in range(i):
            current = current.next
        return current.elem

    def set(self, i, x):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        current = self._head
        for _ in range(i):
            current = current.next
        old = current.elem
        current.elem = x
        return old

    def add(self, i, x):
        if not (0 <= i <= self._listSize): raise IndexError("array index out of range")
        if i == 0:
            self._head = LinkedListNode(x, self._head)
        else:
            prev = self._head
            for _ in range(i-1):
                prev = prev.next
            prev.next = LinkedListNode(x, prev.next)
        self._listSize += 1

    def remove(self, i):
        if not (self._listSize > 0):      raise IndexError("remove from empty array")
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        if i == 0:
            removed = self._head
            self._head = removed.next
        else:
            prev = self._head
            for _ in range(i-1):
                prev = prev.next
            removed = prev.next
            prev.next = removed.next
        removed.next = None   # For garbage collection
        self._listSize -= 1
        return removed.elem

    def isEmpty(self):
        return self._listSize == 0

    def size(self):
        return self._listSize

    def __iter__(self):
        return LinkedListIterator(self._head)

# Python does not have internal classes, so we have to make the iterator standalone.
class LinkedListIterator(Iterator):
    def __init__(self, head):
        self._current = head

    def __iter__(self):
        return self

    def __next__(self):
        if self._current is None:
            raise StopIteration
        x = self._current.elem
        self._current = self._current.next
        return x


# Python does not have internal classes, so we have to make the list node standalone.
class LinkedListNode:
    def __init__(self, elem, next):
        self.elem = elem   # Value for this node
        self.next = next   # Pointer to next node in list


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print("[", " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = LinkedList()
    for i in range(20):
        a.add(a.size(), chr(i+65))
        if i % 5 == 0:
            _printList(a)
    _printList(a)
    for i in range(0, a.size(), 2): a.set(i, a.get(i).lower())
    _printList(a)
    for _ in range(4):
        for i in range(a.size()-1, -1, -3): a.remove(i)
        _printList(a)
