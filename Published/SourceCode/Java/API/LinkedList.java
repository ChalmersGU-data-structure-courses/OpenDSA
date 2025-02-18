
import java.util.Iterator;

class LinkedList<E> implements List<E> {
    private Node head;      // Pointer to list header
    private int listSize;   // Size of list

    LinkedList() {
        head = null;
        listSize = 0;
    }

    private class Node {
        E elem;       // Value for this node
        Node next;    // Pointer to next node in list

        Node(E elem, Node next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public E get(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        Node current = head;
        for (int k = 0; k < i; k++)
            current = current.next;
        return current.elem;
    }

    public E set(int i, E x) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        Node current = head;
        for (int k = 0; k < i; k++)
            current = current.next;
        E old = current.elem;
        current.elem = x;
        return old;
    }

    public void add(int i, E x) {
        if (!(0 <= i && i <= listSize)) throw new IndexOutOfBoundsException("list index out of range");
        if (i == 0) {
            head = new Node(x, head);
        } else {
            Node prev = head;
            for (int k = 0; k < i-1; k++)
                prev = prev.next;
            prev.next = new Node(x, prev.next);
        }
        listSize++;
    }

    public E remove(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        Node removed;
        if (i == 0) {
            removed = head;
            head = removed.next;
        } else {
            Node prev = head;
            for (int k = 0; k < i-1; k++)
                prev = prev.next;
            removed = prev.next;
            prev.next = removed.next;
        }
        removed.next = null;   // For garbage collection
        listSize--;
        return removed.elem;
    }

    public boolean isEmpty() {
        return listSize == 0;
    }

    public int size() {
        return listSize;
    }


    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node current;
        LinkedListIterator() {
            current = head;
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


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printList() {
        System.out.print("[ ");
        for (E e : this) System.out.print(e + " ");
        System.out.println("] " + size());
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
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
