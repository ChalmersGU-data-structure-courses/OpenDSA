
import java.util.Iterator;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.NoSuchElementException;

class DynamicArrayQueue<E> implements Queue<E> {
    private E[] internalArray;   // Internal array containing the queue elements
    private int queueSize;       // Size of queue, and index of the next free slot
    private int front;           // Index of front element
    private int rear;            // Index of rear element

    static int MinCapacity = 8;               // Minimum capacity of internalArray
    static double MinLoadFactor = 0.5;        // Must be smaller than 1/CapacityMultiplier
    static double CapacityMultiplier = 1.5;   // Factor to grow/shrink the capacity

    @SuppressWarnings("unchecked")
    public DynamicArrayQueue() {
        internalArray = (E[]) new Object[MinCapacity];
        queueSize = 0;
        front = 0;
        rear = -1;
    }

    public void enqueue(E x) {
        if (queueSize >= internalArray.length)
            resizeArray((int) (internalArray.length * CapacityMultiplier));
        rear = (rear + 1) % internalArray.length;   // Circular increment
        internalArray[rear] = x;
        queueSize++;
    }

    public E peek() {
        if (!(queueSize > 0)) throw new NoSuchElementException("peek from empty queue");
        return internalArray[front];
    }

    public E dequeue() {
        if (!(queueSize > 0)) throw new NoSuchElementException("dequeue from empty queue");
        queueSize--;
        E x = internalArray[front];
        internalArray[front] = null;   // For garbage collection
        front = (front + 1) % internalArray.length;   // Circular increment
        if (queueSize <= internalArray.length * MinLoadFactor)
            resizeArray((int) (internalArray.length / CapacityMultiplier));
        return x;
    }

    private void resizeArray(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < queueSize; i++)
            newArray[i] = internalArray[(i + front) % internalArray.length];
        internalArray = newArray;
        front = 0;
        rear = queueSize-1;
    }

    public boolean isEmpty() {
        return queueSize == 0;
    }

    public int size() {
        return queueSize;
    }

    public Iterator<E> iterator() {
        if (front + queueSize <= internalArray.length)
            return Arrays.stream(internalArray, front, front + queueSize).iterator();
        else
            return Stream.concat(Arrays.stream(internalArray, front, internalArray.length),
                                 Arrays.stream(internalArray, 0, rear+1)).iterator();
    }


/***************************************************************************************/
/** What comes below is purely for debugging and testing purposes - it can be removed **/

    public void _printList() {
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
