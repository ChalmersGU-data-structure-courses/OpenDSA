
import java.util.Iterator;

class LinkedMap<K, V> implements Map<K, V> {
    private KVNode head;    // Pointer to list header
    private int listSize;   // Size of list

    LinkedMap() {
        head = null;
        listSize = 0;
    }

    private class KVNode {
        K key;
        V value;
        KVNode next;
        KVNode(K key, V value, KVNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public V put(K key, V value) {
        KVNode current = head;
        while (current != null) {
            if (key.equals(current.key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }
        head = new KVNode(key, value, head);
        listSize++;
        return null;
    }

    public V get(K key) {
        KVNode current = head;
        while (current != null) {
            if (key.equals(current.key))
                return current.value;
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        KVNode prev = null;
        KVNode removed = head;
        while (removed != null) {
            if (key.equals(removed.key)) {
                if (prev == null)
                    head = removed.next;
                else
                    prev.next = removed.next;
                removed.next = null;   // For garbage collection
                listSize--;
                return removed.value;
            }
            prev = removed;
            removed = removed.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return listSize == 0;
    }

    public int size() {
        return listSize;
    }

    public Iterator<K> iterator() {
        return new LinkedMapIterator();
    }

    private class LinkedMapIterator implements Iterator<K> {
        private KVNode current;
        LinkedMapIterator() {
            current = head;
        }
        public boolean hasNext() {
            return current != null;
        }
        public K next() {
            K k = current.key;
            current = current.next;
            return k;
        }
    }


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printMap() {
        System.out.print("[ ");
        for (K k : this) System.out.print(k + ":" + get(k) + " ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        LinkedMap<String, Integer> map = new LinkedMap<>();
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
