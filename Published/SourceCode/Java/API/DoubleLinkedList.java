
import java.util.Iterator;

class DoubleLinkedList<E> implements List<E> {
    private DNode head;     // Pointer to list header
    private DNode tail;     // Pointer to list tail
    private int listSize;   // Size of list

    DoubleLinkedList() {
        head = null;
        tail = null;
        listSize = 0;
    }

    private class DNode {
        E elem;       // Value for this node
        DNode prev;   // Pointer to previous node in list
        DNode next;   // Pointer to next node in list

        DNode(E elem, DNode prev, DNode next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    public E get(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        DNode current = head;
        for (int k = 0; k < i; k++)
            current = current.next;
        return current.elem;
    }

    public E set(int i, E x) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        DNode current = head;
        for (int k = 0; k < i; k++)
            current = current.next;
        E old = current.elem;
        current.elem = x;
        return old;
    }

    public void add(int i, E x) {
        if (!(0 <= i && i <= listSize)) throw new IndexOutOfBoundsException("list index out of range");
        if (listSize == 0) {
            head = tail = new DNode(x, null, null);
        } else if (i == 0) {
            DNode newhead = new DNode(x, null, head);
            head.prev = newhead;
            head = newhead;
        } else if (i == listSize) {
            DNode newtail = new DNode(x, tail, null);
            tail.next = newtail;
            tail = newtail;
        } else {
            DNode prev = head;
            for (int k = 0; k < i-1; k++)
                prev = prev.next;
            DNode next = prev.next;
            DNode newnode = new DNode(x, prev, next);
            prev.next = newnode;
            next.prev = newnode;
        }
        listSize++;
    }

    public E remove(int i) {
        if (!(0 <= i && i < listSize)) throw new IndexOutOfBoundsException("list index out of range");
        DNode removed;
        if (i == 0) {
            removed = head;
            head = removed.next;
            head.prev = null;
        } else if (i == listSize-1) {
            removed = tail;
            tail = removed.prev;
            tail.next = null;
        } else {
            DNode prev = head;
            for (int k = 0; k < i-1; k++)
                prev = prev.next;
            removed = prev.next;
            prev.next = removed.next;
            prev.next.prev = prev;
        }
        removed.prev = removed.next = null;   // For garbage collection
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
        return new DoubleLinkedListIterator();
    }

    private class DoubleLinkedListIterator implements Iterator<E> {
        private DNode current;
        DoubleLinkedListIterator() {
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

    public void _testList() {
        if (size() == 0) {
            if (head != null) System.err.println("head is not null (size==0)");
            if (tail != null) System.err.println("tail is not null (size==0)");
            return;
        }
        if (size() == 1) {
            if (head != tail) System.err.println("head != tail (size==1)");
        }
        if (head == null) System.err.println("head is null");
        if (tail == null) System.err.println("tail is null");
        if (head.prev != null) System.err.println("head.prev is not null");
        if (tail.next != null) System.err.println("tail.next is not null");
        DNode n = head;
        while (n != null) {
            if (n.prev != null) {
                if (n.prev.next != n) System.err.println("n.prev.next != n");
            }
            if (n.next != null) {
                if (n.next.prev != n) System.err.println("n.next.prev != n");
            }
            n = n.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list._testList();
        System.out.println("Adding");
        for (int i=0; i<20; i++) {
            list.add(list.size(), String.valueOf((char)(i+65)));
            list._testList();
            if (i % 5 == 0) list._printList();
        }
        list._printList();
        System.out.println("Setting");
        for (int i=0; i<list.size(); i+=2) list.set(i, list.get(i).toLowerCase());
        list._printList();
        System.out.println("Removing");
        for (int k=0; k<4; k++) {
            for (int i=list.size()-1; i>=0; i-=3) {
                list.remove(i);
                list._testList();
            }
            list._printList();
        }
    }
}
