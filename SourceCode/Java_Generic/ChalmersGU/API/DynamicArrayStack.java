
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

/* *** ODSATag: DynamicArrayStack *** */
/* *** ODSATag: DynamicArrayStackInit *** */
class DynamicArrayStack<E> implements Stack<E> {
    private E[] internalArray;   // Internal array containing the stack elements
    private int stackSize;       // Size of stack, and index of the next free slot

    static int MinCapacity = 8;               // Minimum capacity of internalArray
    static double MinLoadFactor = 0.5;        // Must be smaller than 1/CapacityMultiplier
    static double CapacityMultiplier = 1.5;   // Factor to grow/shrink the capacity

    @SuppressWarnings("unchecked")
    public DynamicArrayStack() {
        internalArray = (E[]) new Object[MinCapacity];
        stackSize = 0;
    }
/* *** ODSAendTag: DynamicArrayStackInit *** */

/* *** ODSATag: DynamicArrayStackPush *** */
    public void push(E x) {
        if (stackSize >= internalArray.length)
            resizeArray((int) (internalArray.length * CapacityMultiplier));
        internalArray[stackSize] = x;
        stackSize++;
    }
/* *** ODSAendTag: DynamicArrayStackPush *** */

/* *** ODSATag: DynamicArrayStackPeek *** */
    public E peek() {
        if (!(stackSize > 0)) throw new NoSuchElementException("peek from empty stack");
        return internalArray[stackSize-1];
    }
/* *** ODSAendTag: DynamicArrayStackPeek *** */

/* *** ODSATag: DynamicArrayStackPop *** */
    public E pop() {
        if (!(stackSize > 0)) throw new NoSuchElementException("pop from empty stack");
        stackSize--;
        E x = internalArray[stackSize];
        internalArray[stackSize] = null;   // For garbage collection
        if (stackSize <= internalArray.length * MinLoadFactor)
            resizeArray((int) (internalArray.length / CapacityMultiplier));
        return x;
    }
/* *** ODSAendTag: DynamicArrayStackPop *** */

/* *** ODSATag: DynamicArrayStackResize *** */
    private void resizeArray(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < stackSize; i++)
            newArray[i] = internalArray[i];
        internalArray = newArray;
    }
/* *** ODSAendTag: DynamicArrayStackResize *** */

    public boolean isEmpty() {
        return stackSize == 0;
    }

    public int size() {
        return stackSize;
    }

/* *** ODSATag: DynamicArrayStackIterator *** */
    public Iterator<E> iterator() {
        return Arrays.stream(internalArray, 0, stackSize).iterator();
    }
/* *** ODSAendTag: DynamicArrayStackIterator *** */
/* *** ODSAendTag: DynamicArrayStack *** */


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
/* *** ODSATag: DynamicArrayStack *** */
}
/* *** ODSAendTag: DynamicArrayStack *** */
