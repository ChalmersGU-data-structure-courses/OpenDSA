
import java.util.Iterator;

class SeparateChainingHashMap<K, V> implements Map<K, V> {
    Map<K, V>[] internalTable;
    int mapSize;

    SeparateChainingHashMap() {
        initialiseTable(MinCapacity);
    }

    @SuppressWarnings("unchecked")
    private void initialiseTable(int capacity) {
        internalTable = (Map<K, V>[]) new LinkedListMap[capacity];
        mapSize = 0;
    }

    static int MinCapacity = 8;
    static double MinLoadFactor = 0.5;
    static double MaxLoadFactor = 2.0;
    static double CapacityMultiplier = 1.5;

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % internalTable.length;
    }

    public V put(K key, V value) {
        int i = hash(key);
        Map<K, V> bin = internalTable[i];
        if (bin == null)
            bin = internalTable[i] = new LinkedListMap<>();
        V old = bin.put(key, value);
        if (old == null) {
            mapSize++;
            if (loadFactor() > MaxLoadFactor)
                resizeTable((int) (internalTable.length * CapacityMultiplier));
        }
        return old;
    }

    public V get(K key) {
        int i = hash(key);
        Map<K, V> bin = internalTable[i];
        return bin == null ? null : bin.get(key);
    }        

    public V remove(K key) {
        int i = hash(key);
        Map<K, V> bin = internalTable[i];
        if (bin == null)
            return null;
        V removed = bin.remove(key);
        if (removed != null) {
            mapSize--;
            if (loadFactor() < MinLoadFactor) 
                resizeTable((int) (internalTable.length / CapacityMultiplier));
        }
        return removed;
    }            

    public boolean containsKey(K key) {
        int i = hash(key);
        Map<K, V> bin = internalTable[i];
        return bin != null && bin.containsKey(key);
    }

    private void resizeTable(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        Map<K, V>[] oldTable = internalTable;
        initialiseTable(newCapacity);
        for (Map<K, V> bin : oldTable)
            if (bin != null)
                for (K k : bin)
                    put(k, bin.get(k));
    }

    public boolean isEmpty() {
        return mapSize == 0;
    }

    public int size() {
        return mapSize;
    }

    public double loadFactor() {
        return (double) mapSize / internalTable.length;
    }

    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int bucketIndex;
        private Iterator<K> bucketIterator;
        HashMapIterator() {
            bucketIndex = 0;
            bucketIterator = internalTable[bucketIndex].iterator();
        }
        public boolean hasNext() {
            while (!bucketIterator.hasNext()) {
                bucketIndex++;
                if (bucketIndex >= internalTable.length) return false;
                bucketIterator = internalTable[bucketIndex].iterator();
            }
            return true;
        }
        public K next() {
            hasNext();
            return bucketIterator.next();
        }
    }


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printMap() {
        System.out.print(internalTable.length + " [ ");
        for (int i=0; i<internalTable.length; i++) {
            if (i > 0) System.out.print("| ");
            Map<K, V> bin = internalTable[i];
            if (bin == null) System.out.print("? ");
            else if (bin.isEmpty()) System.out.print("- ");
            else for (K k : bin) System.out.print(k + ":" + get(k) + " ");
        }
        System.out.println("] " + size() + " {" + loadFactor() + "}");
    }

    public static void main(String[] args) {
        SeparateChainingHashMap<String, Integer> map = new SeparateChainingHashMap<>();
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
}

