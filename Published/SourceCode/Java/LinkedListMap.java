
import java.util.Iterator;

class LinkedListMap<K, V> implements Map<K, V> {
    private KVNode head;    // Pointer to list header
    private int listSize;   // Size of list

    LinkedListMap() {
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
        KVNode node = head;
        while (node != null) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }
        head = new KVNode(key, value, head);
        listSize++;
        return null;
    }

    public V get(K key) {
        KVNode node = head;
        while (node != null) {
            if (node.key.equals(key))
                return node.value;
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        KVNode prev = null;
        KVNode node = head;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null)
                    head = node.next;
                else
                    prev.next = node.next;
                node.next = null;   // For garbage collection
                listSize--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        KVNode node = head;
        while (node != null) {
            if (node.key.equals(key))
                return true;
            node = node.next;
        }
        return false;
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
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
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
