
// To be able to use Java's syntactic sugar for for-loops,
// we have to use the "real" interfaces Iterator and Iterable,
// and not the ones in BaseAPI.java
import java.util.Iterator;
import java.lang.Iterable;

/* *** ODSATag: CollectionADT *** */
// Note: This is a subset of java.util.Collection
interface Collection<E> extends Iterable<E> {
    boolean isEmpty();  // Returns true if the collection is empty.
    int size();         // Returns the number of elements in this collection.
}
/* *** ODSAendTag: CollectionADT *** */

/* *** ODSATag: ListADT *** */
// Note: This is a subset of java.util.List
interface List<E> extends Collection<E> {
    void add(int i, E x);  // Adds x at position i; where 0 <= i <= size.
    E get(int i);          // Returns the element at position i; where 0 <= i < size.
    E set(int i, E x);     // Replaces the value at position i with x; where 0 <= i < size..
    E remove(int i);       // Removes the element at position i; where 0 <= i < size..
    // Note: iterator() should yield the elements starting from position 0.
}
/* *** ODSAendTag: ListADT *** */


/* *** ODSATag: StackADT *** */
// Note: This is an interface, while java.util.Stack is a class!
interface Stack<E> extends Collection<E> {
    void push(E x);  // Pushes x on top of the stack.
    E pop();         // Pops the top of the stack and returns it. Raises an exception if the stack is empty.
    E peek();        // Returns the top element, without removing it. Raises an exception if the stack is empty.
    // Note: iterator() should yield the elements starting from the top of the stack.
}
/* *** ODSAendTag: StackADT *** */


/* *** ODSATag: QueueADT *** */
// Note: This is a subset of java.util.Queue; and it uses different method names.
interface Queue<E> extends Collection<E> {
    void enqueue(E x);  // Enqueues x at the end of the queue.
    E dequeue();        // Dequeues the frontmost element. Raises an exception if the queue is empty.
    E peek();           // Returns the frontmost element, without removing it. Raises an exception if the queue is empty.
    // Note: iterator() should yield the elements starting from the frontmost element.
}
/* *** ODSAendTag: QueueADT *** */


/* *** ODSATag: PriorityQueueADT *** */
// Note: This is an interface, while java.util.PriorityQueue is a class
// implementing the Queue interface (so different method names)!
interface PriorityQueue<E> extends Collection<E> {
    void add(E x);  // Adds x to the priority queue.
    E removeMin();  // Removes and returns the minimum element. Raises an exception if the priority queue is empty.
    E getMin();     // Returns the minimum element, without removing it. Raises an exception if the priority queue is empty.
    // Note: iterator() can yield the elements in any order, but the minimum element should come first.
}
/* *** ODSAendTag: PriorityQueueADT *** */


/* *** ODSATag: SetADT *** */
// Note: This is a subset of java.util.Set
interface Set<E> extends Collection<E> {
    boolean add(E x);       // Adds x to the set. Returns true if the element wasn't already in the set.
    boolean remove(E x);    // Removes x from the set. Returns true if the element was in the set.
    boolean contains(E x);  // Returns true if x is in the set.
    // Note: iterator() can yield the elements in any order.
}
/* *** ODSAendTag: SetADT *** */


/* *** ODSATag: SortedSetADT *** */
// Note: This is a subset of java.util.SortedSet, where
// `floor` and `ceiling` are borrowed from java.util.NavigableSet.
interface SortedSet<E> extends Set<E> {
    E first();          // Returns the first (smallest) element. Raises an exception if the set is empty.
    E last();           // Returns the last (largest) element. Raises an exception if the set is empty.
    E floor(E x);       // Returns the closest element <= x, or null if there is no such element.
    E ceiling(E x);     // Returns the closest element >= x, or null if there is no such element.
    E successor(E x);   // Returns the closest element > x, or null if there is no such element.
    E predecessor(E x); // Returns the closest element < x, or null if there is no such element.
    Iterator<E> between(E x, E y); // Returns all elements between x and y.
    // Note: iterator() should yield the elements in order.
}
/* *** ODSAendTag: SortedSetADT *** */


/* *** ODSATag: MapADT *** */
// Note: This is a subset of java.util.Map, where
// `iterator` iterates over the keys, and replaces the more complicated `keySet`.
interface Map<K, V> extends Iterable<K> {
    V put(K key, V value);       // Sets the value of the given key. Returns the previous value, or null.
    V get(K key);                // Returns the value associated with the given key, or null if the key is not there.
    V remove(K key);             // Removes and returns the value associated with the given key, or null if there is no key.
    boolean containsKey(K key);  // Returns true if the key has an associated value.
    boolean isEmpty();           // Returns true if there are no keys.
    int size();                  // Returns the number of keys (i.e., the number of key/value pairs).
    // Note: iterator() can yield the keys in any order.
}
/* *** ODSAendTag: MapADT *** */


/* *** ODSATag: SortedMapADT *** */
// Note: This is a subset of java.util.SortedMap, where
// `floorKey` and `ceilingKey` are borrowed from java.util.NavigableMap.
interface SortedMap<K, V> extends Map<K, V> {
    K firstKey();          // Returns the first (smallest) key. Raises an exception if the map is empty.
    K lastKey();           // Returns the last (largest) key. Raises an exception if the map is empty.
    K floorKey(K key);     // Returns the closest key <= k, or null if there is no key.
    K ceilingKey(K key);   // Returns the closest key >= k, or null if there is no key.
    K successorKey(K k);   // Returns the closest key > k, or null if there is no such element.
    K predecessorKey(K k); // Returns the closest key < k, or null if there is no such element.
    Iterator<K> keysBetween(K k1, K k2); // Returns all keys between k1 and k2.
    // Note: iterator() should yield the keys in order.
}
/* *** ODSAendTag: SortedMapADT *** */


/* *** ODSATag: GraphADT *** */
// Note: This interface does not exist in the standard Java API.
interface Graph<V> {
    boolean addVertex(V v);                      // Adds the vertex v to the graph. Returns true if it wasn't already in the graph.
    boolean addEdge(Edge<V> e);                  // Adds the edge e to the graph. Returns true if it wasn't already in the graph.
    Collection<V> vertices();                    // Returns a Collection of all vertices in the graph.
    Collection<Edge<V>> outgoingEdges(V from);   // Returns a Collection of the edges that originates in vertex v.
    int vertexCount();                           // Returns the number of vertices in the graph.
    int edgeCount();                             // Returns the number of edges in the graph.
}

class Edge<V> {
    public final V start;        // The start vertex.
    public final V end;          // The end vertex.
    public final double weight;  // The weight.

    Edge(V start, V end) {
        this(start, end, 1.0);
    }
    Edge(V start, V end, double weight) {
        this.start = start; this.end = end; this.weight = weight;
    }
}
/* *** ODSAendTag: GraphADT *** */

