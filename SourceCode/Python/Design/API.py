
# All ADTs used in the ChalmersGU course book

#/* *** ODSATag: Emptyline *** */

#/* *** ODSAendTag: Emptyline *** */


#/* *** ODSATag: ComparatorADT *** */
# Note: this is just an informal protocol,
# there is no Python abstract class Comparable.
class Comparable:
    def __eq__(self, other):
        """Test if self == other."""
    def __ne__(self, other):
        """Test if self != other."""
    def __lt__(self, other):
        """Test if self < other."""
    def __le__(self, other):
        """Test if self <= other."""
    def __gt__(self, other):
        """Test if self > other."""
    def __ge__(self, other):
        """Test if self >= other."""
#/* *** ODSAendTag: ComparatorADT *** */


#/* *** ODSATag: IteratorADT *** */
# Note: this is just an informal protocol,
# there is no Python abstract class Iterator.
class Iterator:
    def __iter__(self):
        """Returns the iterator itself."""
    def __next__(self):
        """Returns the next item.
        If there are no further items, raise the StopIteration exception"""
#/* *** ODSAendTag: IteratorADT *** */


#/* *** ODSATag: CollectionADT *** */
class Collection:
    def __iter__(self):
        """Returns an iterator."""
    def __len__(self):
        """Returns the number of elements in this collection."""
#/* *** ODSAendTag: CollectionADT *** */


#/* *** ODSATag: ListADT *** */
class List(Collection):
    def add(self, i, x):
        """Adds x at position i."""
    def get(self, i):
        """Returns the element at position i."""
    def set(self, i, x):
        """Replaces the value at position i with x."""
    def remove(self, i):
        """Removes the element at position i."""
#/* *** ODSAendTag: ListADT *** */


#/* *** ODSATag: StackADT *** */
class Stack(Collection):
    def push(self, x):
        """Pushes x on top of the stack."""
    def pop(self):
        """Pops the top of the stack and returns it."""
    def peek(self):
        """Returns the top element of the stack, without removing it."""
#/* *** ODSAendTag: StackADT *** */


#/* *** ODSATag: QueueADT *** */
class Queue(Collection):
    def enqueue(self, x):
        """Enqueues x at the end of the queue."""
    def dequeue(self):
        """Dequeues the frontmost element."""
    def peek(self):
        """Returns the frontmost element, without removing it."""
#/* *** ODSAendTag: QueueADT *** */


#/* *** ODSATag: PriorityQueueADT *** */
class PriorityQueue(Collection):
    def add(self, x):
        """Adds x to the priority queue."""
    def removeMin(self):
        """Removes the minimum element, and returns it."""
    def getMin(self):
        """Returns the minimum element, without removing it."""
#/* *** ODSAendTag: PriorityQueueADT *** */


#/* *** ODSATag: SetADT *** */
class Set(Collection):
    def add(self, x):
        """Adds x to the set."""
    def remove(self, x):
        """Removes x from the set, and returns it."""
    def contains(self, x):
        """Checks if x is in the set."""
#/* *** ODSAendTag: SetADT *** */


#/* *** ODSATag: OrderedSetADT *** */
class OrderedSet(Set):
    def min(self):
        """Returns the minimum element."""
    def max(self):
        """Returns the maximum element."""
    def floor(self, x):
        """Returns the closest element <= x."""
    def ceiling(self, x):
        """Returns the closest element >= x."""
    # Note: __iter__() should return the elements in order
#/* *** ODSAendTag: OrderedSetADT *** */


#/* *** ODSATag: MapADT *** */
class Map:
    def put(self, key, value):
        """Sets the value of the given key."""
    def get(self, key):
        """Returns the value associated with the given key."""
    def remove(self, key):
        """Removes the value associated with the given key."""
    def contains(self, key):
        """Checks if the key has an associated value."""
    def __iter__(self):
        """Returns an iterator over the keys."""
    def __len__(self):
        """Returns the number of keys."""
}
#/* *** ODSAendTag: MapADT *** */


#/* *** ODSATag: OrderedMapADT *** */
interface OrderedMap(Map):
    def minKey(self):
        """Returns the minimum key."""
    def maxKey(self):
        """Returns the maximum key."""
    def floorKey(self, key):
        """Returns the closest key <= k."""
    def ceilingKey(self, key):
        """Returns the closest key >= k."""
    # Note: __iter__() should return the keys in order
}
#/* *** ODSAendTag: OrderedMapADT *** */


#/* *** ODSATag: GraphADT *** */
class Graph:
    def add(self, e):
        """Adds the edge e to the graph."""
    def remove(self, e):
        """Removes the edge e from the graph."""
    def contains(self, e):
        """Checks if e is an edge in the graph."""
    def outgoingEdges(self, v):
        """Returns a Collection of the edges that originates in vertex v."""
    def vertices(self):
        """Returns a Collection of all vertices in the graph."""
    def edges(self):
        """Returns a Collection of all edges in the graph."""

Edge = namedtuple('Edge', ['start', 'end', 'weight'], defaults=[1.0])
#/* *** ODSAendTag: GraphADT *** */



