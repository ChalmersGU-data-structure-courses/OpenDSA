
import java.util.Iterator;

/* *** ODSATag: DynamicArrayList *** */
/* *** ODSATag: DynamicArrayListInit *** */
class DynamicArrayList<E> implements List<E> {
    private E[] internalArray;
    private int arraySize;

    @SuppressWarnings("unchecked")
    public DynamicArrayList() {
        internalArray = (E[]) new Object[1];
        arraySize = 0;
    }
/* *** ODSAendTag: DynamicArrayListInit *** */

    public E get(int i) {
        if (i < 0 || i >= arraySize) {
            throw new IndexOutOfBoundsException("array index out of range");
        }
        return internalArray[i];
    }

    public E set(int i, E x) {
        if (i < 0 || i >= arraySize) {
            throw new IndexOutOfBoundsException("array index out of range");
        }
        E old = internalArray[i];
        internalArray[i] = x;
        return old;
    }

/* *** ODSATag: DynamicArrayListAdd *** */
    public void add(int i, E x) {
        if (i < 0 || i > arraySize) {
            throw new IndexOutOfBoundsException("array index out of range");
        }
        if (arraySize >= internalArray.length) {
            resizeArray(2 * internalArray.length);
        }
        for (int k = arraySize; k > i; k--) {
            internalArray[k] = internalArray[k-1];
        }
        internalArray[i] = x;
        arraySize++;
    }
/* *** ODSAendTag: DynamicArrayListAdd *** */

/* *** ODSATag: DynamicArrayListRemove *** */
    public E remove(int i) {
        if (arraySize == 0) {
            throw new IndexOutOfBoundsException("remove from empty array");
        }
        if (i < 0 || i >= arraySize) {
            throw new IndexOutOfBoundsException("array index out of range");
        }
        E removed = internalArray[i];
        arraySize--;
        for (int k = i; k < arraySize; k++) {
            internalArray[k] = internalArray[k+1];
        }
        // This is to be able to garbage collect the last element:
        internalArray[arraySize] = null;
        if (arraySize < internalArray.length / 3) {
            resizeArray(internalArray.length / 2);
        }
        return removed;
    }
/* *** ODSAendTag: DynamicArrayListRemove *** */

/* *** ODSATag: DynamicArrayListResize *** */
    private void resizeArray(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = internalArray[i];
        }
        internalArray = newArray;
    }
/* *** ODSAendTag: DynamicArrayListResize *** */

    public boolean isEmpty() {
        return arraySize == 0;
    }

    public int size() {
        return arraySize;
    }

/* *** ODSATag: DynamicArrayListIterator *** */
    public Iterator<E> iterator() {
        return new DynamicArrayListIterator();
    }

    private class DynamicArrayListIterator implements Iterator<E> {
        private int index;
        DynamicArrayListIterator() {
            index = -1;
        }
        public boolean hasNext() {
            return index + 1 < arraySize;
        }
        public E next() {
            index++;
            return internalArray[index];
        }
    }
/* *** ODSAendTag: DynamicArrayListIterator *** */
/* *** ODSAendTag: DynamicArrayList *** */

    public static void main(String[] args) {
        DynamicArrayList<String> list = new DynamicArrayList<>();
        for (int i=0; i<25; i++) list.add(list.size(), String.valueOf((char)(i+65)));
        for (String e : list) System.out.print(e + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=2) list.set(i, list.get(i) + list.get(i));
        for (String e : list) System.out.print(e + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=3) list.remove(i);
        for (int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
    }
/* *** ODSATag: DynamicArrayList *** */
}
/* *** ODSAendTag: DynamicArrayList *** */
