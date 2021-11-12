
import java.util.Iterator;
    
// This is how Java does it, in the method Collections.newSetFromMap:
// https://stackoverflow.com/questions/13524608/how-to-make-a-set-backed-by-a-map/13524864

/* *** ODSATag: MapSet *** */
/* *** ODSATag: Header *** */
class MapSet<E> implements Set<E> {
    Map<E, Boolean> internalMap;
    // One alternative possibility would be to use a Map<E, Void>,
    // but then Map.add() and Map.remove() would always return null
    // and it would be impossible to know if the key already existed or not.

    MapSet() {
        // Use a separate chaining hash map as the default:
        internalMap = new SeparateChainingHashMap<>();
    }

    MapSet(Map<E, Boolean> initialMap) {
        if (!initialMap.isEmpty()) throw new IllegalArgumentException("The initial map must be empty!");
        internalMap = initialMap;
    }
/* *** ODSAendTag: Header *** */

/* *** ODSATag: Add *** */
    public boolean add(E x) {
        return internalMap.put(x, true) == null;
    }
/* *** ODSAendTag: Add *** */

/* *** ODSATag: Remove *** */
    public boolean remove(E x) {
        return internalMap.remove(x) != null;
    }
/* *** ODSAendTag: Remove *** */

/* *** ODSATag: Contains *** */
    public boolean contains(E x) {
        return internalMap.containsKey(x);
    }
/* *** ODSAendTag: Contains *** */

    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    public int size() {
        return internalMap.size();
    }

    public Iterator<E> iterator() {
        return internalMap.iterator();
    }
/* *** ODSAendTag: MapSet *** */


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printSet() {
        System.out.print(internalMap.size() + " { ");
        for (E elem : internalMap) System.out.print(elem + ", ");
        System.out.println("}");
    }

    public static void main(String[] args) {
        MapSet<String> set = new MapSet<>();
        System.out.println("Adding values");
        for (int i=0; i<40; i++) {
            set.add(String.valueOf((char)((i%25)+65)));
            if (i % 5 == 0) set._printSet();
        }
        set._printSet();
        System.out.println("Removing values");
        int i = 7;
        while (!set.isEmpty()) {
            set.remove(String.valueOf((char)((i%25)+65)));
            i += 11;
            if (set.size() % 5 == 0) set._printSet();
        }
    }
/* *** ODSATag: MapSet *** */
}
/* *** ODSAendTag: MapSet *** */

