
import java.util.Iterator;

/* *** ODSATag: DynamicArray *** */
/* *** ODSATag: DynamicArrayInit *** */
class DynamicArray<E> implements Collection<E> {
    private E[] internalArray;
    private int arraySize;

    public DynamicArray() {
        this(0);
    }
    @SuppressWarnings("unchecked")
    public DynamicArray(int size) {
        internalArray = (E[]) new Object[size + 1];
        arraySize = size;
    }
/* *** ODSAendTag: DynamicArrayInit *** */

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

/* *** ODSATag: DynamicArrayAppend *** */
    public void append(E x) {
        if (arraySize >= internalArray.length) {
            resizeArray(2 * internalArray.length);
        }
        internalArray[arraySize] = x;
        arraySize++;
    }
/* *** ODSAendTag: DynamicArrayAppend *** */

/* *** ODSATag: DynamicArrayRemove *** */
    public E removeLast() {
        if (arraySize == 0) {
            throw new IndexOutOfBoundsException("remove from empty array");
        }
        arraySize--;
        E removed = internalArray[arraySize];
        internalArray[arraySize] = null;  // to be able to garbage collect the removed element
        if (arraySize < internalArray.length / 3) {
            resizeArray(internalArray.length / 2);
        }
        return removed;
    }
/* *** ODSAendTag: DynamicArrayRemove *** */

/* *** ODSATag: DynamicArrayResize *** */
    private void resizeArray(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < arraySize; i++) {
            newArray[i] = internalArray[i];
        }
        internalArray = newArray;
    }
/* *** ODSAendTag: DynamicArrayResize *** */

    public boolean isEmpty() {
        return arraySize == 0;
    }

    public int size() {
        return arraySize;
    }

/* *** ODSATag: DynamicArrayIterator *** */
    public Iterator<E> iterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements Iterator<E> {
        private int index;
        DynamicArrayIterator() {
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
/* *** ODSAendTag: DynamicArrayIterator *** */
/* *** ODSAendTag: DynamicArray *** */

    public static void main(String[] args) {
        DynamicArray<String> list = new DynamicArray<>();
        for (int i=0; i<25; i++) list.append(String.valueOf((char)(i+65)));
        for (String e : list) System.out.print(e + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=2) list.set(i, list.get(i) + list.get(i));
        for (String e : list) System.out.print(e + " ");
        System.out.println();
        for (int i=0; i<list.size(); i+=3) list.removeLast();
        for (int i=0; i<list.size(); i++) System.out.print(list.get(i) + " ");
        System.out.println();
    }
/* *** ODSATag: DynamicArray *** */
}
/* *** ODSAendTag: DynamicArray *** */
