
import java.util.Iterator;

class DynamicArrayList<E> implements List<E> {
    private E[] internalArray;   // Internal array containing the list elements
    private int listSize;        // Size of list

    static int MinCapacity = 8;
    static double MinLoadFactor = 0.5;
    static double CapacityMultiplier = 1.5;

    @SuppressWarnings("unchecked")
    public DynamicArrayList() {
        internalArray = (E[]) new Object[MinCapacity];
        listSize = 0;
    }

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

    public void add(int i, E x) {
        if (!(0 <= i && i <= listSize)) throw new IndexOutOfBoundsException("list index out of range");
        if (listSize >= internalArray.length)
            resizeArray((int) (internalArray.length * CapacityMultiplier));
        listSize++;
        for (int k = listSize-1; k > i; k--)
            internalArray[k] = internalArray[k-1];
        internalArray[i] = x;
    }

    public E remove(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        E x = internalArray[i];
        for (int k = i+1; k < listSize; k++)
            internalArray[k-1] = internalArray[k];
        listSize--;
        internalArray[listSize] = null;   // For garbage collection
        if (listSize <= internalArray.length * MinLoadFactor)
            resizeArray((int) (internalArray.length / CapacityMultiplier));
        return x;
    }

    private void resizeArray(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < listSize; i++)
            newArray[i] = internalArray[i];
        internalArray = newArray;
    }

    public boolean isEmpty() {
        return listSize == 0;
    }

    public int size() {
        return listSize;
    }

    public Iterator<E> iterator() {
        return new DynamicArrayListIterator();
    }

    private class DynamicArrayListIterator implements Iterator<E> {
        private int index;
        DynamicArrayListIterator() {
            index = -1;
        }
        public boolean hasNext() {
            return index + 1 < listSize;
        }
        public E next() {
            index++;
            return internalArray[index];
        }
    }


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printList() {
        System.out.print(internalArray.length + " [ ");
        for (E e : this) System.out.print(e + " ");
        System.out.print("| ");
        for (int i=size(); i<internalArray.length; i++) System.out.print("- ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        DynamicArrayList<String> list = new DynamicArrayList<>();
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
}
