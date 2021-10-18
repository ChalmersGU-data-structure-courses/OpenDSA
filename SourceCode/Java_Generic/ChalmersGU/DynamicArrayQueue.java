
import java.util.Iterator;

/* *** ODSATag: DynamicArrayQueue *** */
/* *** ODSATag: DynamicArrayQueueInit *** */
class DynamicArrayQueue<E> implements Queue<E> {
    private E[] internalArray;   // Internal array containing the queue elements
    private int queueSize;       // Size of queue, and index of the next free slot
    private int front;           // Index of front element
    private int rear;            // Index of rear element

    @SuppressWarnings("unchecked")
    public DynamicArrayQueue() {
        internalArray = (E[]) new Object[1];
        queueSize = 0;
        front = 0;
        rear = -1;
    }
/* *** ODSAendTag: DynamicArrayQueueInit *** */

/* *** ODSATag: DynamicArrayQueueEnqueue *** */
    public void enqueue(E x) {
        if (queueSize >= internalArray.length) {
            resizeArray(2 * internalArray.length);
        }
        rear = (rear + 1) % internalArray.length;   // Circular increment
        internalArray[rear] = x;
        queueSize++;
    }
/* *** ODSAendTag: DynamicArrayQueueEnqueue *** */

/* *** ODSATag: DynamicArrayQueuePeek *** */
    public E peek() {
        if (!(queueSize > 0)) throw new IndexOutOfBoundsException("peek from empty queue");
        return internalArray[front];
    }
/* *** ODSAendTag: DynamicArrayQueuePeek *** */

/* *** ODSATag: DynamicArrayQueueDequeue *** */
    public E dequeue() {
        if (!(queueSize > 0)) throw new IndexOutOfBoundsException("dequeue from empty queue");
        queueSize--;
        E x = internalArray[front];
        internalArray[front] = null;   // For garbage collection
        front = (front + 1) % internalArray.length;   // Circular increment
        if (queueSize <= internalArray.length / 3) {
            resizeArray(internalArray.length / 2);
        }
        return x;
    }
/* *** ODSAendTag: DynamicArrayQueueDequeue *** */

/* *** ODSATag: DynamicArrayQueueResize *** */
    private void resizeArray(int newCapacity) {
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < queueSize; i++) {
            newArray[i] = internalArray[(i + front) % internalArray.length];
        }
        internalArray = newArray;
        front = 0;
        rear = queueSize-1;
    }
/* *** ODSAendTag: DynamicArrayQueueResize *** */

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public int size() {
        return queueSize;
    }

/* *** ODSATag: DynamicArrayQueueIterator *** */
    public Iterator<E> iterator() {
        return new DynamicArrayQueueIterator();
    }

    private class DynamicArrayQueueIterator implements Iterator<E> {
        private int index;
        DynamicArrayQueueIterator() {
            index = -1;
        }
        public boolean hasNext() {
            return index + 1 < queueSize;
        }
        public E next() {
            index++;
            return internalArray[(index + front) % internalArray.length];
        }
    }
/* *** ODSAendTag: DynamicArrayQueueIterator *** */
/* *** ODSAendTag: DynamicArrayQueue *** */

    public void pPrint() {
        System.out.print(internalArray.length + " : " + front + " [ ");
        for (E e : internalArray) System.out.print(e == null ? "- " : e + " ");
        System.out.print("] " + rear + "  ...  ");
        for (E e : this) System.out.print(e + " ");
        System.out.println("| " + size());
    }

    public static void main(String[] args) {
        DynamicArrayQueue<String> list = new DynamicArrayQueue<>();
        for (int i=0; i<23; i++) {
            list.enqueue(String.valueOf((char)(i+65)));
            list.enqueue(String.valueOf((char)(i+97)));
            list.dequeue();
            //if (list.size() % 5 == 0)
                list.pPrint();
        }
        list.pPrint();
        while (!list.isEmpty()) {
            if (list.peek() != list.dequeue()) throw new IndexOutOfBoundsException("ERROR!!!");
            if (list.size() % 3 == 2) list.pPrint();
        }
        list.pPrint();
    }
/* *** ODSATag: DynamicArrayQueue *** */
}
/* *** ODSAendTag: DynamicArrayQueue *** */
