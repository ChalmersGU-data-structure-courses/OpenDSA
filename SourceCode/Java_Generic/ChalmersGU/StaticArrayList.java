
import java.util.Iterator;

/* *** ODSATag: StaticArrayList *** */
/* *** ODSATag: StaticArrayListInit *** */
class StaticArrayList<E> implements List<E> {
    private E[] internalArray;
    private int listSize;

    @SuppressWarnings("unchecked")
    public StaticArrayList(int capacity) {
        internalArray = (E[]) new Object[capacity];
        listSize = 0;
    }
/* *** ODSAendTag: StaticArrayListInit *** */

/* *** ODSATag: StaticArrayListGetSet *** */
    public E get(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("array index out of range");
        return internalArray[i];
    }

    public E set(int i, E x) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("array index out of range");
        E old = internalArray[i];
        internalArray[i] = x;
        return old;
    }
/* *** ODSAendTag: StaticArrayListGetSet *** */

/* *** ODSATag: StaticArrayListAdd *** */
    public void add(int i, E x) {
        if (!(listSize < internalArray.length)) throw new IndexOutOfBoundsException("array capacity exceeded");
        if (!(0 <= i && i <= listSize))         throw new IndexOutOfBoundsException("array index out of range");
        listSize++;
        for (int k = listSize-1; k > i; k--) {
            internalArray[k] = internalArray[k-1];
        }
        internalArray[i] = x;
    }
/* *** ODSAendTag: StaticArrayListAdd *** */

/* *** ODSATag: StaticArrayListRemove *** */
    public E remove(int i) {
        if (!(listSize > 0))           throw new IndexOutOfBoundsException("remove from empty array");
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("array index out of range");
        E x = internalArray[i];
        for (int k = i+1; k < listSize; k++) {
            internalArray[k-1] = internalArray[k];
        }
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
        throw new java.lang.UnsupportedOperationException("Left as an exercise.");
    }
/* *** ODSAendTag: StaticArrayList *** */

    public static void main(String[] args) {
        StaticArrayList<String> list = new StaticArrayList<>(25);
        for (int i=0; i<25; i++) list.add(list.size(), String.valueOf((char)(i+65)));
        for (int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=2) list.set(i, list.get(i) + list.get(i));
        for (int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=3) list.remove(i);
        for (int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
    }
/* *** ODSATag: StaticArrayList *** */
}
/* *** ODSAendTag: StaticArrayList *** */
