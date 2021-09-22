
// The following interfaces are not used in the implementations.
// Instead we use the corresponding standard Java API interfaces.

/* *** ODSATag: ComparatorADT *** */
// Note: This is a subset of the java.util.Comparator interface
interface Comparator<E> {
    int compare(E one, E other);  // Returns <0 if one<other, 0 if one==other, >0 if one>other
}

// Note: This is the same as the java.lang.Comparable interface
interface Comparable<E> {
    int compareTo(E other);  // Returns <0 if this<other, 0 if this==other, >0 if this>other
}
/* *** ODSAendTag: ComparatorADT *** */


/* *** ODSATag: IteratorADT *** */
// Note: This is a subset of the java.util.Iterator interface
interface Iterator<E> {
    boolean hasNext();  // Returns true if the iterator is not empty.
    E next();           // Yields the next element in the iterator.
}

// Note: This is a subset of the java.lang.Iterable interface
interface Iterable<E> {
    Iterator<E> iterator();  // Returns a new iterator.
}
/* *** ODSAendTag: IteratorADT *** */

