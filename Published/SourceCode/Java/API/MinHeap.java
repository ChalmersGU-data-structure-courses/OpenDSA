
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

// Min-heap implementation
class MinHeap<E extends Comparable<E>> implements PriorityQueue<E> {
    private E[] heap;       // The heap array
    private int heapSize;   // Size of heap, and index of the next free slot

    static int MinCapacity = 8;               // Minimum capacity of heap
    static double MinLoadFactor = 0.5;        // Must be smaller than 1/CapacityMultiplier
    static double CapacityMultiplier = 1.5;   // Factor to grow/shrink the capacity

    // Constructor supporting preloading of heap contents
    @SuppressWarnings("unchecked")
    MinHeap() {
        heap = (E[]) new Comparable[MinCapacity];
        heapSize = 0;
    }

    MinHeap(E[] h) {
        heap = Arrays.copyOf(h, h.length);
        heapSize = heap.length;
        buildHeap();
        if (heapSize < MinCapacity)
            resizeHeap(MinCapacity);
    }

    // Return true if there are no elements.
    public boolean isEmpty() {
        return heapSize == 0;
    }

    // Return current size of the heap
    public int size() {
        return heapSize;
    }

    public E getMin() {
        if (!(heapSize > 0)) throw new NoSuchElementException("getMin from empty heap");
        return heap[0];
    }

    // Insert val into heap
    public void add(E elem) {
        if (heapSize >= heap.length)
            resizeHeap((int) (heap.length * CapacityMultiplier));
        heap[heapSize] = elem;  // Start at end of heap.
        siftUp(heapSize);       // Put the new value in its correct place.
        heapSize++;
    }

    // Remove and return minimum value
    public E removeMin() {
        if (!(heapSize > 0)) throw new NoSuchElementException("removeMin from empty heap");
        E removed = heap[0];
        heapSize--;
        swap(0, heapSize);  // Swap the root with the current last value.
        if (heapSize > 0) 
            siftDown(0);    // Put the new heap root val in its correct place.
        if (heapSize <= heap.length * MinLoadFactor)
            resizeHeap((int) (heap.length / CapacityMultiplier));
        heap[heapSize] = null;   // Remove the old root value, for garbage collection.
        return removed;
    }

    public String toString() {
        return Arrays.toString(heap).replace("null", "-") + heapSize;
    }

    public Iterator<E> iterator() {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Private helper methods

    // Check that the invariant holds.
    void checkInvariant() {
        for (int i = 0; i < heapSize; i++) {
            int left = getLeftChild(i);
            int right = getRightChild(i);
            if (left < heapSize && lessThan(left, i))
                throw new AssertionError("Parent (" + i + ") is smaller than its left child: " + heap[i] + " < " + heap[left]);
            if (right < heapSize && lessThan(right, i))
                throw new AssertionError("Parent (" + i + ") is smaller than its right child: " + heap[i] + " < " + heap[right]);
        }
    }

    private void resizeHeap(int newCapacity) {
        if (newCapacity < MinCapacity) return;
        @SuppressWarnings("unchecked")
        E[] newHeap = (E[]) new Comparable[newCapacity];
        for (int i = 0; i < heapSize; i++)
            newHeap[i] = heap[i];
        heap = newHeap;
    }

    // Return true if pos is a leaf position, false otherwise.
    private boolean isLeaf(int pos) {
        return pos >= heapSize / 2;
    }

    // Return the position for the left child of the given node
    private int getLeftChild(int pos) {
        return 2 * pos + 1;
    }

    // Return the position for the right child of the given node
    private int getRightChild(int pos) {
        return 2 * pos + 2;
    }

    // Return the position for the parent. Returns 0 if we're already at the root.
    private int getParent(int pos) {
        return (pos - 1) / 2;
    }

    // Swap the values in two positions
    private void swap(int pos1, int pos2) {
        E tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    // Heapify the contents of an array
    private void buildHeap() {
        for (int pos = heapSize/2-1; pos >= 0; pos--)
            siftDown(pos);
    }

    // Sift a value down the tree, return its new position.
    private int siftDown(int pos) {
        while (!isLeaf(pos)) {
            int child = getLeftChild(pos);
            int right = child + 1;   // or: getRightChild(pos)
            if (right < heapSize && lessThan(right, child))
                child = right;   // 'child' is now the index of the child with smaller value
            if (!lessThan(child, pos))
                return pos;
            swap(pos, child);
            pos = child;   // Move down one level in the tree.
        }
        return pos;
    }

    // Sift a value up the tree, return its new position.
    private int siftUp(int pos) {
        while (pos > 0) {
            int parent = getParent(pos);
            if (!lessThan(pos, parent))
                return pos;
            swap(pos, parent);
            pos = parent;   // Move up one level in the tree.
        }
        return pos;
    }

    // Compare the values in the given positions.
    private boolean lessThan(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    ////////////////////////////////////////////////////////////////////////////////
    // A very simple testing method

    public static void main(String[] args) {
        MinHeap<String> h;
        if (args.length > 0) {
            h = new MinHeap<>(args);
            System.out.println(h);
            h.checkInvariant();
        } else {
            Random rnd = new Random();
            h = new MinHeap<>();
            for (int i=0; i<20; i++) {
                String r = "" + (10 + rnd.nextInt(42));
                h.add(r);
                System.out.println("Adding " + r + "  -->  " + h);
                h.checkInvariant();
            }
        }
        System.out.println();
        while (!h.isEmpty()) {
            String x = h.removeMin();
            System.out.println("Remove " + x + "  -->  " + h);
            h.checkInvariant();
        }
    }
}
