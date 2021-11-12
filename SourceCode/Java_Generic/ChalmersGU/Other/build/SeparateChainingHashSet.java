
import java.util.Iterator;

/* *** ODSATag: SeparateChainingHashSet *** */
/* *** ODSATag: Header *** */
abstract class SeparateChainingHashSet<E> implements Set<E> {
    SeparateChainingHashMap<E, E>[] internalMap;

//     SeparateChainingHashSet() {
//         internalMap = new SeparateChainingHashMap<>();
//     }
// /* *** ODSAendTag: Header *** */

// /* *** ODSATag: Add *** */
//     public boolean add(E x) {
//         E prev = internalMap.put(x, x);
//         return prev == null;
//     }
// /* *** ODSAendTag: Add *** */

// /* *** ODSATag: Remove *** */
//     public boolean remove(E x) {
//         E prev = internalMap.remove(x);
//         return prev != null;
//     }
// /* *** ODSAendTag: Remove *** */

// /* *** ODSATag: Contains *** */
//     public boolean contains(E x) {
//         return internalMap.containsKey(x);
//     }
// /* *** ODSAendTag: Contains *** */

//     public boolean isEmpty() {
//         return internalMap.isEmpty();
//     }

//     public int size() {
//         return internalMap.size();
//     }

//     public Iterator<E> iterator() {
//         return internalMap.iterator();
//     }
// /* *** ODSAendTag: SeparateChainingHashMap *** */


// /***************************************************************************************/
// /** What comes below is purely for debugging and testing purposes - it can be removed **/

//     public void _printMap() {
//         System.out.print(internalTable.length + " [ ");
//         for (int i=0; i<internalTable.length; i++) {
//             if (i > 0) System.out.print("| ");
//             Map<K, V> bin = internalTable[i];
//             if (bin == null) System.out.print("? ");
//             else if (bin.isEmpty()) System.out.print("- ");
//             else for (K k : bin) System.out.print(k + ":" + get(k) + " ");
//         }
//         System.out.println("] " + size() + " {" + loadFactor() + "}");
//     }

//     public static void main(String[] args) {
//         SeparateChainingHashMap<String, Integer> map = new SeparateChainingHashMap<>();
//         map._printMap();
//         System.out.println("Putting values");
//         for (int i=0; i<40; i++) {
//             map.put(String.valueOf((char)((i%25)+65)), i);
//             if (i % 5 == 0) map._printMap();
//         }
//         map._printMap();
//         System.out.println("Removing values");
//         int i = 7;
//         while (!map.isEmpty()) {
//             map.remove(String.valueOf((char)((i%25)+65)));
//             i += 11;
//             if (map.size() % 5 == 0) map._printMap();
//         }
//     }
/* *** ODSATag: SeparateChainingHashMap *** */
}
/* *** ODSAendTag: SeparateChainingHashMap *** */

