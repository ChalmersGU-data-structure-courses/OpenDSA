
from BaseAPI import Comparable, Iterable, Iterator

#/* *** ODSATag: CollectionADT *** */
class Collection(Iterable):
    def isEmpty(self):  """Returns true if the collection is empty."""
    def size(self):     """Returns the number of elements in this collection."""
#/* *** ODSAendTag: CollectionADT *** */


#/* *** ODSATag: ListADT *** */
class List(Collection):
    def add(self, i, x): """Adds x at position i; where 0 <= i <= size."""
    def get(self, i):    """Returns the element at position i; where 0 <= i < size."""
    def set(self, i, x): """Replaces the value at position i with x; where 0 <= i < size."""
    def remove(self, i): """Removes the element at position i; where 0 <= i < size."""
    # Note: __iter__() should yield the elements starting from position 0.
#/* *** ODSAendTag: ListADT *** */


#/* *** ODSATag: StackADT *** */
class Stack(Collection):
    def push(self, x): """Pushes x on top of the stack."""
    def pop(self):     """Pops the top of the stack and returns it. Raises an exception if the stack is empty."""
    def peek(self):    """Returns the top element, without removing it. Raises an exception if the stack is empty."""
    # Note: __iter__() should yield the elements starting from the top of the stack.
#/* *** ODSAendTag: StackADT *** */


#/* *** ODSATag: QueueADT *** */
class Queue(Collection):
    def enqueue(self, x): """Enqueues x at the end of the queue."""
    def dequeue(self):    """Dequeues the frontmost element. Raises an exception if the queue is empty."""
    def peek(self):       """Returns the frontmost element, without removing it. Raises an exception if the queue is empty."""
    # Note: __iter__() should yield the elements starting from the frontmost element.
#/* *** ODSAendTag: QueueADT *** */


#/* *** ODSATag: PriorityQueueADT *** */
class PriorityQueue(Collection):
    def add(self, x):    """Adds x to the priority queue."""
    def removeMin(self): """Removes and returns the minimum element. Raises an exception if the priority queue is empty."""
    def getMin(self):    """Returns the minimum element, without removing it. Raises an exception if the priority queue is empty."""
    # Note: __iter__() can yield the elements in any order, but the minimum element should come first.
#/* *** ODSAendTag: PriorityQueueADT *** */


#/* *** ODSATag: SetADT *** */
class Set(Collection):
    def add(self, x):      """Adds x to the set. Returns true if the element wasn't already in the set."""
    def remove(self, x):   """Removes x from the set. Returns true if the element was in the set."""
    def contains(self, x): """Returns true if x is in the set."""
    # Note: __iter__() can yield the elements in any order.
#/* *** ODSAendTag: SetADT *** */


#/* *** ODSATag: SortedSetADT *** */
class SortedSet(Set):
    def first(self):            """Returns the first (smallest) element. Raises an exception if the set is empty."""
    def last(self):             """Returns the last (largest) element. Raises an exception if the set is empty."""
    def floor(self, x):         """Returns the closest element <= x, or None if there is no such element."""
    def ceiling(self, x):       """Returns the closest element >= x, or None if there is no such element."""
    def lower(self, x):         """Returns the closest element < x, or None if there is no such element."""
    def higher(self, x):        """Returns the closest element > x, or None if there is no such element."""
    def between(self, x1, x2):  """Returns all elements x such that x1 <= x <= x2."""
    # Note: __iter__() should yield the elements in order.
#/* *** ODSAendTag: SortedSetADT *** */


#/* *** ODSATag: MapADT *** */
class Map(Iterable):
    def put(self, key, value):  """Sets the value of the given key. Returns the previous value, or None."""
    def get(self, key):         """Returns the value associated with the given key, or None if the key is not there."""
    def remove(self, key):      """Removes and returns the value associated with the given key, or None if there is no key."""
    def containsKey(self, key): """Returns true if the key has an associated value."""
    def isEmpty(self):          """Returns true if there are no keys."""
    def size(self):             """Returns the number of keys (i.e., the number of key/value pairs)."""
    # Note: __iter__() can yield the keys in any order.
#/* *** ODSAendTag: MapADT *** */


#/* *** ODSATag: SortedMapADT *** */
class SortedMap(Map):
    def firstKey(self):                """Returns the first (smallest) key. Raises an exception if the map is empty."""
    def lastKey(self):                 """Returns the last (largest) key. Raises an exception if the map is empty."""
    def floorKey(self, key):           """Returns the closest key <= k, or None if there is no key."""
    def ceilingKey(self, key):         """Returns the closest key >= k, or None if there is no key."""
    def lowerKey(self, key):           """Returns the closest key < k, or None if there is no such element."""
    def higherKey(self, key):          """Returns the closest key > k, or None if there is no such element."""
    def keysBetween(self, key1, key2): """Returns all keys k such that k1 <= k <= k2."""
    # Note: __iter__() should yield the keys in order.
#/* *** ODSAendTag: SortedMapADT *** */


#/* *** ODSATag: GraphADT *** */
class Graph:
    def addVertex(self, v):     """Adds the vertex v to the graph. Returns true if it wasn't already in the graph."""
    def addEdge(self, e):       """Adds the edge e to the graph. Returns true if it wasn't already in the graph."""
    def vertices(self):         """Returns a Collection of all vertices in the graph."""
    def outgoingEdges(self, v): """Returns a Collection of the edges that originates in vertex v. Returns None if there are no edges."""
    def vertexCount(self):      """Returns the number of vertices in the graph."""
    def edgeCount(self):        """Returns the number of edges in the graph."""

from collections import namedtuple
Edge = namedtuple('Edge', ['start', 'end', 'weight'], defaults=[1.0])
#/* *** ODSAendTag: GraphADT *** */

