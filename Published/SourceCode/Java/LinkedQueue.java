
import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedQueue<E> implements Queue<E> {
    private Node front;      // Pointer to front queue node
    private Node rear;       // Pointer to rear queue node
    private int queueSize;   // Size of queue

    LinkedQueue() {
        front = null;
        rear = null;
        queueSize = 0;
    }

    private class Node {
        E elem;       // Value for this node
        Node next;    // Pointer to next node in queue

        Node(E elem, Node next) {
            this.elem = elem;
            this.next = next;
        }
    }

    public void enqueue(E x) {
        Node newRear = new Node(x, null);
        if (queueSize == 0)
            front = newRear;
        else
            rear.next = newRear;
        rear = newRear;
        queueSize++;
    }

    public E peek() {
        if (!(queueSize > 0)) throw new NoSuchElementException("peek from empty queue");
        return front.elem;
    }

    public E dequeue() {
        if (!(queueSize > 0)) throw new NoSuchElementException("dequeue from empty queue");
        Node removed = front;
        front = removed.next;
        removed.next = null;   // For garbage collection
        queueSize--;
        if (queueSize == 0)
            rear = null;
        return removed.elem;
    }

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public int size() {
        return queueSize;
    }

    public Iterator<E> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<E> {
        private Node current;
        LinkedQueueIterator() {
            current = front;
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
        LinkedQueue<String> list = new LinkedQueue<>();
        for (int i=0; i<23; i++) {
            list.enqueue(String.valueOf((char)(i+65)));
            list.enqueue(String.valueOf((char)(i+97)));
            list.dequeue();
            if (list.size() % 5 == 0) list._printList();
        }
        list._printList();
        while (!list.isEmpty()) {
            if (list.peek() != list.dequeue()) throw new IndexOutOfBoundsException("ERROR!!!");
            if (list.size() % 3 == 2) list._printList();
        }
        list._printList();
    }
}
