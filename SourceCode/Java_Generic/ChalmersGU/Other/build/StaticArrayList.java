
import java.util.Iterator;

/* *** ODSATag: StaticArrayList *** */
/* *** ODSATag: StaticArrayListInit *** */
class StaticArrayList<E> implements List<E> {
    private E[] internalArray;   // Internal array containing the list elements
    private int listSize;        // Size of list

    @SuppressWarnings("unchecked")
    public StaticArrayList(int capacity) {
        internalArray = (E[]) new Object[capacity];
        listSize = 0;
    }
/* *** ODSAendTag: StaticArrayListInit *** */

/* *** ODSATag: StaticArrayListGetSet *** */
    public E get(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        return internalArray[i];
    }

    public E set(int i, E x) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        E old = internalArray[i];
        internalArray[i] = x;
        return old;
    }
/* *** ODSAendTag: StaticArrayListGetSet *** */

/* *** ODSATag: StaticArrayListAdd *** */
    public void add(int i, E x) {
        if (!(listSize < internalArray.length)) throw new IndexOutOfBoundsException("list capacity exceeded");
        if (!(0 <= i && i <= listSize))         throw new IndexOutOfBoundsException("list index out of range");
        listSize++;
        for (int k = listSize-1; k > i; k--)
            internalArray[k] = internalArray[k-1];
        internalArray[i] = x;
    }
/* *** ODSAendTag: StaticArrayListAdd *** */

/* *** ODSATag: StaticArrayListRemove *** */
    public E remove(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        E x = internalArray[i];
        for (int k = i+1; k < listSize; k++)
            internalArray[k-1] = internalArray[k];
        listSize--;
        internalArray[listSize] = null;   // For garbage collection
        return x;
    }
/* *** ODSAendTag: StaticArrayListRemove *** */

    public boolean isEmpty() {
        return listSize == 0;
    }

    public int size() {
        return listSize;
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Left as an exercise.");
    }
/* *** ODSAendTag: StaticArrayList *** */


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printList() {
        System.out.print(size() + " [ ");
        for (int i=0; i<size(); i++) System.out.print(get(i) + " ");
        System.out.print("| ");
        for (int i=size(); i<internalArray.length; i++) System.out.print("- ");
        System.out.println("] " + internalArray.length);
    }

    public static void main(String[] args) {
        StaticArrayList<String> list = new StaticArrayList<>(25);
        for (int i=0; i<20; i++) {
            list.add(list.size(), String.valueOf((char)(i+65)));
            if (i % 5 == 0) list._printList();
        }
        list._printList();
        for (int i=0; i<list.size(); i+=2) list.set(i, list.get(i).toLowerCase());
        list._printList();
        for (int k=0; k<4; k++) {
            for (int i=list.size()-1; i>=0; i-=3) list.remove(i);
            list._printList();
        }
    }
/* *** ODSATag: StaticArrayList *** */
}
/* *** ODSAendTag: StaticArrayList *** */
