
// All ADTs used in the ChalmersGU course book

/* *** ODSATag: Emptyline *** */

/* *** ODSAendTag: Emptyline *** */



/* *** ODSATag: ComparatorADT *** */
interface Comparator<E> {
    int compare(E one, E other);
}

interface Comparable<E> {
    int compareTo(E other);
}
/* *** ODSAendTag: ComparatorADT *** */


/* *** ODSATag: IteratorADT *** */
interface Iterator<E> {
    boolean hasNext();
    E next();
}
/* *** ODSAendTag: IteratorADT *** */


/* *** ODSATag: CollectionADT *** */
interface Iterable<E> {
    Iterator<E> iterator();
}

interface Collection<E> extends Iterable<E> {
    boolean isEmpty();
    int size();
}
/* *** ODSAendTag: CollectionADT *** */


/* *** ODSATag: ListADT *** */
interface List<E> extends Collection<E> {
    void add(int i, E x);
    E get(int i);
    E set(int i, E x);
    E remove(int i);
}
/* *** ODSAendTag: ListADT *** */


/* *** ODSATag: StackADT *** */
interface Stack<E> extends Collection<E> {
    void push(E x);
    E pop();
    E peek();
}
/* *** ODSAendTag: StackADT *** */


/* *** ODSATag: QueueADT *** */
interface Queue<E> extends Collection<E> {
    void enqueue(E x);
    E dequeue();
    E peek();
}
/* *** ODSAendTag: QueueADT *** */


/* *** ODSATag: PriorityQueueADT *** */
interface PriorityQueue<E> extends Collection<E> {
    void add(E x);
    E removeMin();
    E getMin();
}
/* *** ODSAendTag: PriorityQueueADT *** */


/* *** ODSATag: SetADT *** */
interface Set<E> extends Collection<E> {
    void add(E x);
    void remove(E x);
    boolean contains(E x);
}
/* *** ODSAendTag: SetADT *** */


/* *** ODSATag: OrderedSetADT *** */
interface OrderedSet<E> extends Set<E> {
    E min();
    E max();
    E floor(E x);
    E ceiling(E x);
    // Note: iterator() should return the elements in order
}
/* *** ODSAendTag: OrderedSetADT *** */


/* *** ODSATag: MapADT *** */
interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean contains(K key);
    Iterable<K> keys();
    boolean isEmpty();
    int size();
}
/* *** ODSAendTag: MapADT *** */


/* *** ODSATag: OrderedMapADT *** */
interface OrderedMap<K, V> extends Map<K, V> {
    K minKey();
    K maxKey();
    K floorKey(K key);
    K ceilingKey(K key);
    // Note: keys() should return the keys in order
}
/* *** ODSAendTag: OrderedMapADT *** */


/* *** ODSATag: GraphADT *** */
interface Graph<V> {
    void addVertex(V v);
    void addEdge(Edge<V> e);
    Collection<V> vertices();
    Collection<Edge<V>> outgoingEdges(V from);
    int vertexCount();
    int edgeCount();
}

class Edge<V> {
    // constructor, the 3rd argument is optional:
    Edge(V from, V to, double weight=1.0);
    V from();
    V to();
    double weight();
}
/* *** ODSAendTag: GraphADT *** */



