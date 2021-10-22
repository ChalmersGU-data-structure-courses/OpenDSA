
import java.util.Iterator;

class SeparateChainingHashMap<K, V> implements Map<K, V> {
    LinkedListMap<K, V>[] HT;
    int mapSize;

    static int minCapacity = 10;
    static double minLoadFactor = 1.0;
    static double maxLoadFactor = 2.0;
    static double capacityMultiplier = 1.5;

    // @SuppressWarnings("unchecked")
    SeparateChainingHashMap() {
        initialiseTable(minCapacity);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % HT.length;
    }

    public V put(K key, V value) {
        int h = hash(key);
        V old = HT[h].put(key, value);
        if (old == null) {
            mapSize++;
            if (loadFactor() > maxLoadFactor)
                resizeTable((int) (HT.length * capacityMultiplier));
        }
        return old;
    }

    public V get(K key) {
        int h = hash(key);
        return HT[h].get(key);
    }        

    public V remove(K key) {
        int h = hash(key);
        V removed = HT[h].remove(key);
        if (removed != null) {
            mapSize--;
            if (loadFactor() < minLoadFactor) 
                resizeTable((int) (HT.length / capacityMultiplier));
        }
        return removed;
    }            

    public boolean containsKey(K key) {
        int h = hash(key);
        return HT[h].containsKey(key);
    }

    @SuppressWarnings("unchecked")
    private void initialiseTable(int capacity) {
        HT = (LinkedListMap<K, V>[]) new LinkedListMap[capacity];
        for (int i = 0; i < capacity; i++)
            HT[i] = new LinkedListMap<>();
        mapSize = 0;
    }

    private void resizeTable(int newCapacity) {
        if (newCapacity < minCapacity) return;
        System.out.println("RESIZE " + HT.length + " -> " + newCapacity);
        LinkedListMap<K, V>[] oldHT = HT;
        initialiseTable(newCapacity);
        for (LinkedListMap<K, V> bucket : oldHT)
            for (K k : bucket)
                put(k, bucket.get(k));
    }

    public boolean isEmpty() {
        return mapSize == 0;
    }

    public int size() {
        return mapSize;
    }

    public double loadFactor() {
        return (double) mapSize / HT.length;
    }

    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int currentIndex;
        private Iterator<K> bucketIterator;
        HashMapIterator() {
            currentIndex = 0;
            bucketIterator = HT[currentIndex].iterator();
        }
        public boolean hasNext() {
            while (!bucketIterator.hasNext()) {
                currentIndex++;
                if (currentIndex >= HT.length) return false;
                bucketIterator = HT[currentIndex].iterator();
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
        System.out.print("[ ");
        for (int i=0; i<HT.length; i++) {
            if (i > 0) System.out.print("| ");
            for (K k : HT[i]) System.out.print(k + ":" + get(k) + " ");
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

