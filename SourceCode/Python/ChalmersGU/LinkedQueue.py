
from API import Queue, Iterator

#/* *** ODSATag: LinkedQueue *** */
#/* *** ODSATag: LinkedQueueInit *** */
class LinkedQueue(Queue):
    def __init__(self):
        self._front = None    # Pointer to front queue node
        self._rear = None     # Pointer to rear queue node
        self._queueSize = 0   # Size of queue
#/* *** ODSAendTag: LinkedQueueInit *** */

#/* *** ODSATag: LinkedQueueEnqueue *** */
    def enqueue(self, x):
        newRear = LinkedQueueNode(x, None)
        if self._queueSize == 0:
            self._front = newRear
        else:
            self._rear.next = newRear
        self._rear = newRear
        self._queueSize += 1
#/* *** ODSAendTag: LinkedQueueEnqueue *** */

#/* *** ODSATag: LinkedQueuePeek *** */
    def peek(self):
        if not (self._queueSize > 0): raise IndexError("peek from empty queue")
        return self._front.elem
#/* *** ODSAendTag: LinkedQueuePeek *** */

#/* *** ODSATag: LinkedQueueDequeue *** */
    def dequeue(self):
        if not (self._queueSize > 0): raise IndexError("dequeue from empty queue")
        removed = self._front
        self._front = removed.next
        removed.next = None   # For garbage collection
        self._queueSize -= 1
        if self._queueSize == 0:
            self._rear = None
        return removed.elem
#/* *** ODSAendTag: LinkedQueueDequeue *** */

    def isEmpty(self):
        return self._queueSize == 0

    def size(self):
        return self._queueSize

#/* *** ODSATag: LinkedQueueIterator *** */
    def __iter__(self):
        return LinkedQueueIterator(self._front)

# Python does not have internal classes, so we have to make the iterator standalone.
class LinkedQueueIterator(Iterator):
    def __init__(self, front):
        self._current = front

    def __iter__(self):
        return self

    def __next__(self):
        if self._current is None:
            raise StopIteration
        x = self._current.elem
        self._current = self._current.next
        return x
#/* *** ODSAendTag: LinkedQueueIterator *** */


#/* *** ODSATag: LinkedQueueNode *** */
# Python does not have internal classes, so we have to make the queue node standalone.
class LinkedQueueNode:
    def __init__(self, elem, next):
        self.elem = elem   # Value for this node
        self.next = next   # Pointer to next node in queue
#/* *** ODSAendTag: LinkedQueueNode *** */
#/* *** ODSAendTag: LinkedQueue *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print("[", " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = LinkedQueue()
    for i in range(23):
        a.enqueue(chr(i+65))
        a.enqueue(chr(i+97))
        a.dequeue()
        if a.size() % 5 == 0: _printList(a)
    _printList(a)
    while not a.isEmpty():
        assert a.peek() == a.dequeue(), (a,)
        if a.size() % 3 == 2: _printList(a)
    _printList(a)
