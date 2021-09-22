
from BaseAPI import Comparable, Iterable, Iterator

#/* *** ODSATag: CollectionADT *** */
class Collection(Iterable):
    def isEmpty(self):  """Returns true if the collection is empty."""
    def size(self):     """Returns the number of elements in this collection."""
#/* *** ODSAendTag: CollectionADT *** */


#/* *** ODSATag: ListADT *** */
class List(Collection):
    def add(self, i, x): """Adds x at position i."""
    def get(self, i):    """Returns the element at position i."""
    def set(self, i, x): """Replaces the value at position i with x."""
    def remove(self, i): """Removes the element at position i."""
#/* *** ODSAendTag: ListADT *** */


#/* *** ODSATag: StackADT *** */
class Stack(Collection):
    def push(self, x): """Pushes x on top of the stack."""
    def pop(self):     """Pops the top of the stack and returns it."""
    def peek(self):    """Returns the top element of the stack, without removing it."""
#/* *** ODSAendTag: StackADT *** */


#/* *** ODSATag: QueueADT *** */
class Queue(Collection):
    def enqueue(self, x): """Enqueues x at the end of the queue."""
    def dequeue(self):    """Dequeues the frontmost element."""
    def peek(self):       """Returns the frontmost element, without removing it."""
#/* *** ODSAendTag: QueueADT *** */


#/* *** ODSATag: PriorityQueueADT *** */
class PriorityQueue(Collection):
    def add(self, x):    """Adds x to the priority queue."""
    def removeMin(self): """Removes the minimum element, and returns it."""
    def getMin(self):    """Returns the minimum element, without removing it."""
#/* *** ODSAendTag: PriorityQueueADT *** */


#/* *** ODSATag: SetADT *** */
class Set(Collection):
    def add(self, x):      """Adds x to the set."""
    def remove(self, x):   """Removes x from the set, and returns it."""
    def contains(self, x): """Checks if x is in the set."""
#/* *** ODSAendTag: SetADT *** */


#/* *** ODSATag: SortedSetADT *** */
class SortedSet(Set):
    def first(self):      """Returns the first (smallest) element."""
    def last(self):       """Returns the last (largest) element."""
    def floor(self, x):   """Returns the closest element <= x."""
    def ceiling(self, x): """Returns the closest element >= x."""
    # Note: iterator() should yield the elements in order
#/* *** ODSAendTag: SortedSetADT *** */


#/* *** ODSATag: MapADT *** */
class Map:
    def put(self, key, value):  """Sets the value of the given key."""
    def get(self, key):         """Returns the value associated with the given key."""
    def remove(self, key):      """Removes the value associated with the given key."""
    def containsKey(self, key): """Checks if the key has an associated value."""
    def keyIterator(self):      """Returns an iterator over the keys."""
    def isEmpty(self):          """Returns true if there are no ."""
    def size(self):             """Returns the number of keys (i.e., the number of key/value pairs)."""
#/* *** ODSAendTag: MapADT *** */


#/* *** ODSATag: SortedMapADT *** */
class SortedMap(Map):
    def firstKey(self):        """Returns the first (smallest) key."""
    def lastKey(self):         """Returns the last (largest) key."""
    def floorKey(self, key):   """Returns the closest key <= k."""
    def ceilingKey(self, key): """Returns the closest key >= k."""
    # Note: keyIterator() should yield the keys in order
#/* *** ODSAendTag: SortedMapADT *** */


#/* *** ODSATag: GraphADT *** */
class Graph:
    def addVertex(self, v):     """Adds the vertex v to the graph."""
    def addEdge(self, e):       """Adds the edge e to the graph."""
    def vertices(self):         """Returns a Collection of all vertices in the graph."""
    def outgoingEdges(self, v): """Returns a Collection of the edges that originates in vertex v."""
    def vertexCount(self):      """Returns the number of vertices in the graph."""
    def edgeCount(self):        """Returns the number of edges in the graph."""

from collections import namedtuple
Edge = namedtuple('Edge', ['start', 'end', 'weight'], defaults=[1.0])
#/* *** ODSAendTag: GraphADT *** */

