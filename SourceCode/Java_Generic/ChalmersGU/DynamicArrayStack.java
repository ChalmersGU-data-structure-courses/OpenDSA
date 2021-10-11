
import java.util.Iterator;

/* *** ODSATag: DynamicArrayStack *** */
/* *** ODSATag: DynamicArrayStackInit *** */
class DynamicArrayStack<E> implements Stack<E> {
    private E[] internalArray;
    private int top;

    @SuppressWarnings("unchecked")
    public DynamicArrayStack() {
        internalArray = (E[]) new Object[1];
        top = 0;
    }
/* *** ODSAendTag: DynamicArrayStackInit *** */

/* *** ODSATag: DynamicArrayStackPush *** */
    public void push(E x) {
        if (top >= internalArray.length) {
            resizeArray(2 * internalArray.length);
        }
        internalArray[top] = x;
        top++;
    }
/* *** ODSAendTag: DynamicArrayStackPush *** */

/* *** ODSATag: DynamicArrayStackPeek *** */
    public E peek() {
        if (!(top > 0)) throw new IndexOutOfBoundsException("peek from empty stack");
        return internalArray[top-1];
    }
/* *** ODSAendTag: DynamicArrayStackPeek *** */

/* *** ODSATag: DynamicArrayStackPop *** */
    public E pop() {
        if (!(top > 0)) throw new IndexOutOfBoundsException("pop from empty stack");
        top--;
        E x = internalArray[top];
        internalArray[top] = null;   // For garbage collection
        if (top <= internalArray.length / 3) {
            resizeArray(internalArray.length / 2);
        }
        return x;
    }
/* *** ODSAendTag: DynamicArrayStackPop *** */

/* *** ODSATag: DynamicArrayStackResize *** */
    private void resizeArray(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < top; i++) {
            newArray[i] = internalArray[i];
        }
        internalArray = newArray;
    }
/* *** ODSAendTag: DynamicArrayStackResize *** */

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }

/* *** ODSATag: DynamicArrayStackIterator *** */
    public Iterator<E> iterator() {
        return new DynamicArrayStackIterator();
    }

    private class DynamicArrayStackIterator implements Iterator<E> {
        private int index;
        DynamicArrayStackIterator() {
            index = top;
        }
        public boolean hasNext() {
            return index > 0;
        }
        public E next() {
            index--;
            return internalArray[index];
        }
    }
/* *** ODSAendTag: DynamicArrayStackIterator *** */
/* *** ODSAendTag: DynamicArrayStack *** */

    public void pPrint() {
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
            if (list.size() % 5 == 0) list.pPrint();
        }
        list.pPrint();
        while (!list.isEmpty()) {
            if (list.peek() != list.pop()) throw new IndexOutOfBoundsException("ERROR!!!");
            if (list.size() % 3 == 2) list.pPrint();
        }
        list.pPrint();
    }
/* *** ODSATag: DynamicArrayStack *** */
}
/* *** ODSAendTag: DynamicArrayStack *** */
