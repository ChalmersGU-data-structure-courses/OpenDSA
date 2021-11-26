public class HeapsortInplace {
/* *** ODSATag: Heapsort *** */
static void heapsort(int[] a) {
    // Convert the array to a heap
    buildHeap(a);

    // Repeatedly find and remove the minimum element
    int heapsize = a.length;
    while (heapsize > 0) {
        swap(a, 0, heapsize - 1);
        heapsize--;
        siftDown(a, heapsize, 0);
    }
}
    
static void buildHeap(int[] a) {
    // Go backwards from the first non-leaf, sifting down each one
    for (int i = a.length/2; i >= 0; i--)
        siftDown(a, a.length, i);
}

// Standard sift-down method for max heaps
static void siftDown(int[] a, int heapsize, int i) {
    while (leftChildIndex(i) < heapsize) {
        int l = leftChildIndex(i);
        int r = rightChildIndex(i);
        int largest;

        if (a[l] > a[i])
            largest = l;
        else
            largest = i;

        if (r < heapsize && a[r] > a[largest])
            largest = r;

        if (largest == i)
            return;
        else {
            swap(a, i, largest);
            i = largest;
        }
    }
}

static int leftChildIndex(int i) {
    return 2*i+1;
}

static int rightChildIndex(int i) {
    return 2*i+2;
}

// Swap index i and j
static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}
/* *** ODSAendTag: Heapsort *** */

    public static void main(String[] args) {
        int[] x = {3,1,4,1,5,9,2,6,5};
        heapsort(x);
        for (int elem: x) System.out.println(elem);
    }
}
