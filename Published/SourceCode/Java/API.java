
// To be able to use Java's syntactic sugar for for-loops,
// we have to use the "real" interfaces Iterator and Iterable,
// and not the ones in BaseAPI.java
import java.util.Iterator;
import java.lang.Iterable;

// Note: This is a subset of java.util.Collection
interface Collection<E> extends Iterable<E> {
    boolean isEmpty();  // Returns true if the collection is empty.
    int size();         // Returns the number of elements in this collection.
}

// Note: This is a subset of java.util.List
interface List<E> extends Collection<E> {
    void add(int i, E x);  // Adds x at position i.
    E get(int i);          // Returns the element at position i.
    E set(int i, E x);     // Replaces the value at position i with x.
    E remove(int i);       // Removes the element at position i.
    // Note: iterator() should yield the elements starting from position 0.
}


// Note: This is an interface, while java.util.Stack is a class!
interface Stack<E> extends Collection<E> {
    void push(E x);  // Pushes x on top of the stack.
    E pop();         // Pops the top of the stack and returns it.
    E peek();        // Returns the top element of the stack, without removing it.
    // Note: iterator() should yield the elements starting from the top of the stack.
}


// Note: This is a subset of java.util.Queue; and it uses different method names.
interface Queue<E> extends Collection<E> {
    void enqueue(E x);  // Enqueues x at the end of the queue.
    E dequeue();        // Dequeues the frontmost element.
    E peek();           // Returns the frontmost element, without removing it.
    // Note: iterator() should yield the elements starting from the frontmost element.
}


// Note: This is an interface, while java.util.PriorityQueue is a class
// implementing the Queue interface (so different method names)!
interface PriorityQueue<E> extends Collection<E> {
    void add(E x);  // Adds x to the priority queue.
    E removeMin();  // Removes the minimum element, and returns it.
    E getMin();     // Returns the minimum element, without removing it.
    // Note: iterator() can yield the elements in any order, but the minimum element should come first.
}


// Note: This is a subset of java.util.Set
interface Set<E> extends Collection<E> {
    void add(E x);          // Adds x to the set.
    void remove(E x);       // Removes x from the set, and returns it.
    boolean contains(E x);  // Checks if x is in the set.
    // Note: iterator() can yield the elements in any order.
}


// Note: This is a subset of java.util.SortedSet, where
// `floor` and `ceiling` are borrowed from java.util.NavigableSet.
interface SortedSet<E> extends Set<E> {
    E first();       // Returns the first (smallest) element.
    E last();        // Returns the last (largest) element.
    E floor(E x);    // Returns the closest element <= x.
    E ceiling(E x);  // Returns the closest element >= x.
    // Note: iterator() should yield the elements in order.
}


// Note: This is a subset of java.util.Map, where
// `keyIterator` replaces the more complicated `keySet`.
interface Map<K, V> {
    void put(K key, V value);    // Sets the value of the given key.
    V get(K key);                // Returns the value associated with the given key.
    V remove(K key);             // Removes and returns the value associated with the given key.
    boolean containsKey(K key);  // Checks if the key has an associated value.
    Iterator<K> keyIterator();   // Returns an iterator over the keys.
    boolean isEmpty();           // Returns true if there are no keys.
    int size();                  // Returns the number of keys (i.e., the number of key/value pairs).
    // Note: keyIterator() can yield the keys in any order.
}


// Note: This is a subset of java.util.SortedMap, where
// `floorKey` and `ceilingKey` are borrowed from java.util.NavigableMap.
interface SortedMap<K, V> extends Map<K, V> {
    K firstKey();          // Returns the first (smallest) key.
    K lastKey();           // Returns the last (largest) key.
    K floorKey(K key);     // Returns the closest key <= k.
    K ceilingKey(K key);   // Returns the closest key >= k.
    // Note: keyIterator() should yield the keys in order.
}


// Note: This interface does not exist in the standard Java API.
interface Graph<V> {
    void addVertex(V v);                         // Adds the vertex v to the graph.
    void addEdge(Edge<V> e);                     // Adds the edge e to the graph.
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

