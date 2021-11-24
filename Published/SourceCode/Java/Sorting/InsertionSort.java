
import java.util.Arrays;

class InsertionSort {

static <E extends Comparable<E>> void checkorder(E[] A) {
    for (int i=1; i<A.length; i++)
        if (A[i].compareTo(A[i-1]) < 0) {
            System.err.println("Error! Value " + A[i] + " at position " + i +
                               " was less than " + A[i-1] + " at position " + (i-1));
        }
}

public static <T extends Comparable<T>> void insertionSort(T[] A) {
    for (int i = 1; i < A.length; i++) {
        // Insert i'th record.
        int j = i;
        while (j > 0 && A[j].compareTo(A[j-1]) < 0) {
            Util.swap(A, j, j-1);
            j--;
        }
    }
}

// Instead of swapping, "shift" the values down the array
public static <T extends Comparable<T>> void insertionSortShift(T[] A) {
    for (int i = 1; i < A.length; i++) {
        // Insert i'th record.
        T temp = A[i];
        int j = i;
        while (j > 0 && temp.compareTo(A[j-1]) < 0) {
            A[j] = A[j-1];
            j--;
        }
        A[j] = temp;
    }
}

public static void main(String[] args) {
    if (args.length == 0)
        args = new String[] {"Peter", "Lisa", "Linda", "Petra", "Alban"};
    System.out.println("Before sorting: " + Arrays.toString(args));
    insertionSort(args);
    System.out.println("After sorting:  " + Arrays.toString(args));
    checkorder(args);
}
}
