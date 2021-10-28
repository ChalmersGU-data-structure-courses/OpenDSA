
import java.util.Iterator;

/* *** ODSATag: ListMap *** */
/* *** ODSATag: Header *** */
class ListMap<K, V> implements Map<K, V> {
    private List<KVPair<K,V>> internalList;

    ListMap() {
        // This could also be a DynamicArrayList.
        internalList = new LinkedList<>();
    }
/* *** ODSAendTag: Header *** */

/* *** ODSATag: Put *** */
    public V put(K key, V value) {
        for (KVPair<K,V> entry : internalList) {
            if (key.equals(entry.key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        // If we're using a DynamicArrayList we should add at the end of the list instead.
        internalList.add(0, new KVPair<>(key, value));
        return null;
    }
/* *** ODSAendTag: Put *** */

/* *** ODSATag: Get *** */
    public V get(K key) {
        for (KVPair<K,V> entry : internalList)
            if (key.equals(entry.key))
                return entry.value;
        return null;
    }
/* *** ODSAendTag: Get *** */

/* *** ODSATag: Remove *** */
    // To be able to remove entries efficiently,
    // the underlying list iterator has to implement the .remove() method.
    public V remove(K key) {
        Iterator<KVPair<K,V>> iter = internalList.iterator();
        while (iter.hasNext()) {
            KVPair<K,V> entry = iter.next();
            if (key.equals(entry.key)) {
                V removed = entry.value;
                iter.remove();   // Remove the last entry returned by the iterator.
                return removed;
            }
        }
        return null;
    }
/* *** ODSAendTag: Remove *** */

/* *** ODSATag: ContainsKey *** */
    public boolean containsKey(K key) {
        return get(key) != null;
    }
/* *** ODSAendTag: ContainsKey *** */

/* *** ODSATag: EmptySize *** */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    public int size() {
        return internalList.size();
    }
/* *** ODSAendTag: EmptySize *** */

/* *** ODSATag: Iterator *** */
    public Iterator<K> iterator() {
        return new LinkedMapIterator();
    }

    private class LinkedMapIterator implements Iterator<K> {
        private Iterator<KVPair<K,V>> iter;
        LinkedMapIterator() {
            iter = internalList.iterator();
        }
        public boolean hasNext() {
            return iter.hasNext();
        }
        public K next() {
            return iter.next().key;
        }
    }
/* *** ODSAendTag: Iterator *** */
/* *** ODSAendTag: ListMap *** */


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printMap() {
        System.out.print("[ ");
        for (K k : this) System.out.print(k + ":" + get(k) + " ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        ListMap<String, Integer> map = new ListMap<>();
        map._printMap();
        System.out.println("Putting values");
        for (int i=0; i<40; i++) {
            map.put(String.valueOf((char)((i%25)+65)), i);
            if (i % 5 == 0) map._printMap();
        }
        map._printMap();
        System.out.println("Removing values");
        int i = 7;
        while (!map.isEmpty()) {
            map.remove(String.valueOf((char)((i%25)+65)));
            i += 11;
            if (map.size() % 5 == 0) map._printMap();
        }
    }
/* *** ODSATag: ListMap *** */
}
/* *** ODSAendTag: ListMap *** */
