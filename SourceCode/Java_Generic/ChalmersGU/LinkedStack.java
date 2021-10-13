
import java.util.Iterator;

/* *** ODSATag: LinkedStack *** */
/* *** ODSATag: LinkedStackInit *** */
class LinkedStack<E> implements Stack<E> {
    private Node top;        // Pointer to top of stack
    private int stackSize;   // Size of stack

    LinkedStack() {
        top = null;
        stackSize = 0;
    }
/* *** ODSAendTag: LinkedStackInit *** */

/* *** ODSATag: LinkedStackNode *** */
    private class Node {
        E elem;       // Value for this node
        Node next;    // Pointer to next node in stack

        Node(E elem, Node next) {
            this.elem = elem;
            this.next = next;
        }
    }
/* *** ODSAendTag: LinkedStackNode *** */

/* *** ODSATag: LinkedStackPush *** */
    public void push(E x) {
        top = new Node(x, top);
        stackSize++;
    }
/* *** ODSAendTag: LinkedStackPush *** */

/* *** ODSATag: LinkedStackPeek *** */
    public E peek() {
        if (!(stackSize > 0)) throw new IndexOutOfBoundsException("peek from empty stack");
        return top.elem;
    }
/* *** ODSAendTag: LinkedStackPeek *** */

/* *** ODSATag: LinkedStackPop *** */
    public E pop() {
        if (!(stackSize > 0)) throw new IndexOutOfBoundsException("pop from empty stack");
        Node removed = top;
        top = removed.next;
        removed.next = null;   // For garbage collection
        stackSize--;
        return removed.elem;
    }
/* *** ODSAendTag: LinkedStackPop *** */

    public boolean isEmpty() {
        return stackSize == 0;
    }

    public int size() {
        return stackSize;
    }

/* *** ODSATag: LinkedStackIterator *** */
    public Iterator<E> iterator() {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<E> {
        private Node current;
        LinkedStackIterator() {
            current = top;
        }
        public boolean hasNext() {
            return current != null;
        }
        public E next() {
            E x = current.elem;
            current = current.next;
            return x;
        }
    }
/* *** ODSAendTag: LinkedStackIterator *** */
/* *** ODSAendTag: LinkedStack *** */

    public void pPrint() {
        System.out.print("[ ");
        for (E e : this) System.out.print(e + " ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        LinkedStack<String> list = new LinkedStack<>();
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
/* *** ODSATag: LinkedStack *** */
}
/* *** ODSAendTag: LinkedStack *** */
