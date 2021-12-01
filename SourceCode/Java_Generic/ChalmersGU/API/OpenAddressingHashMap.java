
import java.util.Iterator;

/* *** ODSATag: OpenAddressingHashMap *** */
/* *** ODSATag: Header *** */
class OpenAddressingHashMap<K, V> implements Map<K, V> {
    KVPair<K,V>[] internalTable;
    int mapSize;
    int numDeleted;

/* *** ODSAendTag: Header *** */
/* *** ODSATag: Constants *** */
    static int MinCapacity = 8;
    static double MinLoadFactor = 0.3;
    static double MaxLoadFactor = 0.7;
    static double CapacityMultiplier = 1.5;
/* *** ODSAendTag: Constants *** */

/* *** ODSATag: Header *** */
    OpenAddressingHashMap() {
        initialiseTable(MinCapacity);
    }

    @SuppressWarnings("unchecked")
    private void initialiseTable(int capacity) {
        internalTable = (KVPair<K,V>[]) new KVPair[capacity];
        mapSize = 0;
        numDeleted = 0;
    }
/* *** ODSAendTag: Header *** */

/* *** ODSATag: HashIndex *** */
    private int hashAndProbe(K key) {
        int home = key.hashCode() & 0x7fffffff;   // Turn the hash code into a positive integer
        for (int i = 0; i < internalTable.length; i++) {
            int pos = (home + probe(key, i)) % internalTable.length;
            KVPair<K,V> elem = internalTable[pos];
            if (elem == null || key.equals(elem.key))
                return pos;
        }
        throw new IllegalStateException("Hash table full");
    }

    private int probe(K key, int i) {
        return i;        // Linear probing
        // return i*i;   // Quadratic probing
    }
/* *** ODSAendTag: HashIndex *** */

/* *** ODSATag: Put *** */
    public V put(K key, V value) {
        int i = hashAndProbe(key);
        KVPair<K,V> elem = internalTable[i];
        V old = null;
        if (elem == null) {
            internalTable[i] = new KVPair<>(key, value);
            mapSize++;
        } else {
            old = elem.value;
            elem.value = value;
        }
        if (loadFactor() > MaxLoadFactor)
            resizeTable((int) (internalTable.length * CapacityMultiplier));
        return old;
    }
/* *** ODSAendTag: Put *** */

/* *** ODSATag: Get *** */
    public V get(K key) {
        int i = hashAndProbe(key);
        KVPair<K,V> elem = internalTable[i];
        return elem == null ? null : elem.value;
    }
/* *** ODSAendTag: Get *** */

/* *** ODSATag: Remove *** */
    public V remove(K key) {
        int i = hashAndProbe(key);
        KVPair<K,V> elem = internalTable[i];
        if (elem == null)
            return null;
        V removed = elem.value;
        elem.key = null;
        elem.value = null;
        mapSize--;
        numDeleted++;
        if (mapSize < MinLoadFactor * internalTable.length) 
            resizeTable((int) (internalTable.length / CapacityMultiplier));
        return removed;
    }            
/* *** ODSAendTag: Remove *** */

    public boolean containsKey(K key) {
        int i = hashAndProbe(key);
        return internalTable[i] != null;
    }

/* *** ODSATag: Resize *** */
    private void resizeTable(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        KVPair<K,V>[] oldTable = internalTable;
        initialiseTable(newCapacity);
        for (int i = 0; i < oldTable.length; i++) {
            KVPair<K,V> elem = oldTable[i];
            if (elem != null && elem.key != null)
                put(elem.key, elem.value);
        }
    }
/* *** ODSAendTag: Resize *** */

    public boolean isEmpty() {
        return mapSize == 0;
    }

    public int size() {
        return mapSize;
    }

/* *** ODSATag: LoadFactor *** */
    public double loadFactor() {
        return (double) (mapSize + numDeleted) / internalTable.length;
    }
/* *** ODSAendTag: LoadFactor *** */

    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int index;
        HashMapIterator() {
            index = 0;
        }
        public boolean hasNext() {
            KVPair<K,V> elem = internalTable[index];
            while (elem == null || elem.key == null) {
                index++;
                if (index >= mapSize)
                    return false;
                elem = internalTable[index];
            }
            return true;
        }
        public K next() {
            hasNext();
            KVPair<K,V> elem = internalTable[index];
            index++;
            return elem.key;
        }
    }
/* *** ODSAendTag: OpenAddressingHashMap *** */


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printMap() {
        System.out.print(internalTable.length + " [ ");
        for (int i=0; i<internalTable.length; i++) {
            KVPair<K,V> elem = internalTable[i];
            System.out.print(elem == null ? "- " : elem.key == null ? "# " : elem.key + ":" + elem.value + " ");
        }
        System.out.println("] " + size() + " {" + loadFactor() + "}");
    }

    public static void main(String[] args) {
        OpenAddressingHashMap<String, Integer> map = new OpenAddressingHashMap<>();
        map._printMap();
        System.out.println("Putting values");
        for (int i=0; i<40; i++) {
            map.put(String.valueOf((char)((i%25)+65)), i);
            if (i % 5 == 0) map._printMap();
        }
        map._printMap();
        System.out.println("Removing values");
        for (int i = 7; !map.isEmpty(); i += 11) {
            map.remove(String.valueOf((char)((i%25)+65)));
            if (map.size() % 3 == 0) map._printMap();
        }
    }
/* *** ODSATag: OpenAddressingHashMap *** */
}
/* *** ODSAendTag: OpenAddressingHashMap *** */

