
import java.util.Iterator;

class DynamicArrayStack<E> implements Stack<E> {
    private E[] internalArray;   // Internal array containing the stack elements
    private int stackSize;       // Size of stack, and index of the next free slot

    @SuppressWarnings("unchecked")
    public DynamicArrayStack() {
        internalArray = (E[]) new Object[1];
        stackSize = 0;
    }

    public void push(E x) {
        if (stackSize >= internalArray.length) {
            resizeArray(2 * internalArray.length);
        }
        internalArray[stackSize] = x;
        stackSize++;
    }

    public E peek() {
        if (!(stackSize > 0)) throw new IndexOutOfBoundsException("peek from empty stack");
        return internalArray[stackSize-1];
    }

    public E pop() {
        if (!(stackSize > 0)) throw new IndexOutOfBoundsException("pop from empty stack");
        stackSize--;
        E x = internalArray[stackSize];
        internalArray[stackSize] = null;   // For garbage collection
        if (stackSize <= internalArray.length / 3) {
            resizeArray(internalArray.length / 2);
        }
        return x;
    }

    private void resizeArray(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < stackSize; i++) {
            newArray[i] = internalArray[i];
        }
        internalArray = newArray;
    }

    public boolean isEmpty() {
        return stackSize == 0;
    }

    public int size() {
        return stackSize;
    }

    public Iterator<E> iterator() {
        return new DynamicArrayStackIterator();
    }

    private class DynamicArrayStackIterator implements Iterator<E> {
        private int index;
        DynamicArrayStackIterator() {
            index = stackSize;
        }
        public boolean hasNext() {
            return index > 0;
        }
        public E next() {
            index--;
            return internalArray[index];
        }
    }


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printList() {
        System.out.print(internalArray.length + " [ ");
        for (int i=size(); i<internalArray.length; i++) System.out.print("- ");
        System.out.print("| ");
        for (E e : this) System.out.print(e + " ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        DynamicArrayStack<String> list = new DynamicArrayStack<>();
        for (int i=0; i<23; i++) {
            list.push(String.valueOf((char)(i+65)));
            if (list.size() % 5 == 0) list._printList();
        }
        list._printList();
        while (!list.isEmpty()) {
            if (list.peek() != list.pop()) throw new IndexOutOfBoundsException("ERROR!!!");
            if (list.size() % 3 == 2) list._printList();
        }
        list._printList();
    }
}
