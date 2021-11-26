
import java.util.Arrays;

class Util {

    public static <E> void swap(E[] A, int i, int j) {
        E temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static <E extends Comparable<E>> boolean checkOrder(E[] A) {
        boolean status = true;
        for (int i=1; i<A.length; i++)
            if (A[i].compareTo(A[i-1]) < 0) {
                System.err.println("Error! Value " + A[i] + " at position " + i +
                                   " was less than " + A[i-1] + " at position " + (i-1));
                status = false;
            }
        return status;
    }

    static public void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java SortUtil <sorting-algorithm> {list of strings to sort}");
            return;
        }
        String algorithm = args[0].toLowerCase();
        String[] array = Arrays.copyOfRange(args, 1, args.length);
        if (array.length == 0) {
            array = new String[] {"Peter", "Lisa", "Erik", "Linda", "Petra", "Alban"};
        }
        System.out.println("Before sorting: " + Arrays.toString(array));
        switch (algorithm) {
        case "insertionsort": InsertionSort.insertionSort(array); break;
        case "selectionsort": SelectionSort.selectionSort(array); break;
        case "bubblesort": BubbleSort.bubbleSort(array); break;
        case "mergesort": MergeSort.mergeSort(array); break;
        case "quicksort": QuickSort.quickSort(array); break;
        case "pqsort": PQSort.pqSort(array); break;
        case "heapsort": HeapSort.heapSort(array); break;
        }
        System.out.println("After sorting:  " + Arrays.toString(array));
        Util.checkOrder(array);
    }
}

