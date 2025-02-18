import java.util.Arrays;
class Quicksort {
    public static void main(String[] args) {
        boolean res = sorttest(new int[] {3, 6, 9, 2, 8, 4, 5, 1, 7});
        System.out.println(res);
    }

static boolean sorttest(int[] B) {
    int i;
    Integer[] A = new Integer[B.length];
    for (i=0; i<B.length; i++)
        A[i] = Integer.valueOf(B[i]);
    quicksort(A, 0, A.length-1);
    if (!checkorder(A)) return false;
    return true;
}

// Swap for int arrays
static void swap(int[] A, int i, int j) {
  int temp = A[i];
  A[i] = A[j];
  A[j] = temp;
}

static <T> void swap(T[] A, int i, int j) {
    T temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}

static Boolean checkorder(int[] A) {
  Boolean status = true;
  for (int i=1; i<A.length; i++)
    if (A[i] < A[i-1]) {
      System.out.println("Error! Value " + A[i] + " at position " + i +
              " was less than " + A[i-1] + " at position " + (i-1));
      status = false;
    }
  return status;
}

static <T extends Comparable<T>> Boolean checkorder(T[] A) {
  Boolean status = true;
  for (int i=1; i<A.length; i++)
    if (A[i].compareTo(A[i-1]) < 0) {
      System.out.println("Error! Value " + A[i] + " at position " + i +
              " was less than " + A[i-1] + " at position " + (i-1));
      status = false;
    }
  return status;
}

/* Warning: Partition is sensitive. If we don't make the right
   position actually cross the left, then it seems hard to get things
   to work right when there is only one element in the partition
   (i.e., a list of 2 elements). */
/* *** ODSATag: partition *** */
static <T extends Comparable<T>> int partition(T[] A, int left, int right, int pivot) {
    swap(A, left, pivot);   // Put pivot at the leftmost index
    pivot = left;
    left++;                 // Start partitioning from the element after the pivot

    T pivotValue = A[pivot];
    while (true) {
        // Move `left` right as far as possible. Stop if equal to pivot!
        // Also stop if `left` moves past `right` – this is important,
        // so that `left` stops if it moves past the end of the array.
        while (left <= right && A[left].compareTo(pivotValue) < 0) 
            left++;

        // Move `right` left as far as possible. Stop if equal to pivot!
        // Also stop if `right` moves all the way left to `left`,
        // see above for why.
        while (left <= right && A[right].compareTo(pivotValue) > 0) 
            right--;

        // Stop here if `left` and `right` passed each other.
        if (left > right)
            break;

        // Otherwise, swap the elements and move `left` and `right` on by 1.
        swap(A, left, right);
        left++; right--;
    }

    swap(A, pivot, right);   // Finally, move the pivot into place
    return right;            // Return the position of the pivot
}
/* *** ODSAendTag: partition *** */

/* *** ODSATag: findpivot *** */
static <T extends Comparable<T>> int findpivot(T[] A, int i, int j) {
    // Not-so-good pivot selection: always choose the middle element.
    return (i + j) / 2;
}
/* *** ODSAendTag: findpivot *** */

/* *** ODSATag: Quicksort *** */
static <T extends Comparable<T>> void quicksort(T[] A, int left, int right) {
    if (left >= right)                          // Base case: Subarray length is <= 1
        return;
    int pivot = findpivot(A, left, right);      // Pick a pivot index
    pivot = partition(A, left, right, pivot);   // Partition the subarray; update pivot with its new position
    quicksort(A, left, pivot-1);                // Sort left partition
    quicksort(A, pivot+1, right);               // Sort right partition
}
/* *** ODSAendTag: Quicksort *** */

// ---------------------------------------------------------------

// Set up and implementations for doing timing runs on certain variations

// static void sorttime(Comparable[] B) {
//     int i;
//     Comparable[] A = new Comparable[B.length];
//     int totaltime, runs;
//     int numruns = 20;

//     // Timing test for standard implementation
//     totaltime = 0;
//     for (runs=0; runs<numruns; runs++) {
//         for(i=0; i<B.length; i++) A[i] = B[i];
//         time1 = millis();
//         quicksort(A, 0, A.length-1);
//         time2 = millis();
//         checkorder(A);
//         totaltime += (time2-time1);
//     }
//     println("Standard Quicksort for " + numruns + " runs: Size " +
//             testsize + ", Time: " + totaltime);

//     // Timing test for optimized version
//     totaltime = 0;
//     for (runs=0; runs<numruns; runs++) {
//         for(i=0; i<B.length; i++) A[i] = B[i];
//         time1 = millis();
//         quicksortOpt(A, 0, A.length-1);
//         time2 = millis();
//         checkorder(A);
//         totaltime += (time2-time1);
//     }
//     println("Optimized Quicksort for " + numruns + " runs: Size " +
//             testsize + ", Time: " + totaltime);

//     // Timing test for Integer type, optimized version
//     totaltime = 0;
//     Integer[] AInteger = new Integer[B.length];
//     Integer[] BInteger = new Integer[B.length];
//     for (i=0; i<B.length; i++)
//         BInteger[i] = (Integer)B[i];
//     for (runs=0; runs<numruns; runs++) {
//         for(i=0; i<B.length; i++) AInteger[i] = BInteger[i];
//         time1 = millis();
//         quicksortOptInt(AInteger, 0, AInteger.length-1);
//         time2 = millis();
//         checkorder(AInteger);
//         totaltime += (time2-time1);
//     }
//     println("Optimized Integer record Quicksort for " + numruns + " runs: Size " +
//             testsize + ", Time: " + totaltime);

//     // Timing test for integer-only optimized version
//     totaltime = 0;
//     int[] Aint = new int[B.length];
//     int[] Bint = new int[B.length];
//     for (i=0; i<B.length; i++)
//         Bint[i] = (Integer)B[i];
//     for (runs=0; runs<numruns; runs++) {
//         for(i=0; i<B.length; i++) Aint[i] = Bint[i];
//         time1 = millis();
//         quicksortOptint(Aint, 0, Aint.length-1);
//         time2 = millis();
//         checkorder(Aint);
//         totaltime += (time2-time1);
//     }
//     println("Optimized integer-only Quicksort for " + numruns + " runs: Size " +
//             testsize + ", Time: " + totaltime);
// }

// Insertion sort used by optimized quicksort
// Instead of swapping, "shift" the values down the array
static void insertionsortshift(Comparable[] A) {
    for (int i=1; i<A.length; i++) { // Insert i'th record
        int j;
        Comparable temp = A[i];
        for (j=i; (j>0) && (temp.compareTo(A[j-1]) < 0); j--)
            A[j] = A[j-1];
        A[j] = temp;
    }
}

// Insertion sort used by optimized quicksort
// Records are of type Integer
// Instead of swapping, "shift" the values down the array
static void insertionsortshiftInt(Integer[] A) {
    for (int i=1; i<A.length; i++) { // Insert i'th record
        int j;
        Integer temp = A[i];
        for (j=i; (j>0) && (temp < A[j-1]); j--)
            A[j] = A[j-1];
        A[j] = temp;
    }
}

// Insertion sort used by optimized quicksort
// Integer-only version
// Instead of swapping, "shift" the values down the array
static void insertionsortshiftint(int[] A) {
    for (int i=1; i<A.length; i++) { // Insert i'th record
        int j;
        int temp = A[i];
        for (j=i; (j>0) && (temp < A[j-1]); j--)
            A[j] = A[j-1];
        A[j] = temp;
    }
}

static int MAXSTACKSIZE = 100;
static int THRESHOLD = 10;

// Optimized Quicksort: Not recursive, and uses Inssort for small lists
static void quicksortOpt(Comparable[] A, int oi, int oj) { // Quicksort
    int[] Stack = new int[MAXSTACKSIZE]; // Stack for array bounds
    int listsize = oj-oi+1;
    int top = -1;
    Comparable pivot;
    int pivotindex, l, r;

    Stack[++top] = oi;  // Initialize stack
    Stack[++top] = oj;

    while (top > 0) {   // While there are unprocessed subarrays
        // Pop Stack
        int j = Stack[top--];
        int i = Stack[top--];

        // Findpivot
        pivotindex = (i+j)/2;
        pivot = A[pivotindex];
        swap(A, pivotindex, j); // Stick pivot at end

        // Partition
        l = i-1;
        r = j;
        do {
            while (A[++l].compareTo(pivot)<0);
            while ((r!=0) && (A[--r].compareTo(pivot)>0));
            swap(A, l, r);
        } while (l < r);
        swap(A, l, r);  // Undo final swap
        swap(A, l, j);  // Put pivot value in place

        // Put new subarrays onto Stack if they are small
        if ((l-i) > THRESHOLD) {   // Left partition
            Stack[++top] = i;
            Stack[++top] = l-1;
        }
        if ((j-l) > THRESHOLD) {   // Right partition
            Stack[++top] = l+1;
            Stack[++top] = j;
        }
    }
    insertionsortshift(A);             // Final Insertion Sort
}

// Optimized Quicksort: Not recursive, and uses Inssort for small lists
// Assumes that the record is an Integer
static void quicksortOptInt(Integer[] A, int oi, int oj) { // Quicksort
    int[] Stack = new int[MAXSTACKSIZE]; // Stack for array bounds
    int listsize = oj-oi+1;
    int top = -1;
    Integer pivot;
    int pivotindex, l, r;

    Stack[++top] = oi;  // Initialize stack
    Stack[++top] = oj;

    while (top > 0) {   // While there are unprocessed subarrays
        // Pop Stack
        int j = Stack[top--];
        int i = Stack[top--];

        // Findpivot
        pivotindex = (i+j)/2;
        pivot = A[pivotindex];
        swap(A, pivotindex, j); // Stick pivot at end

        // Partition
        l = i-1;
        r = j;
        do {
            while (A[++l] < pivot);
            while ((r!=0) && (A[--r] > pivot));
            swap(A, l, r);
        } while (l < r);
        swap(A, l, r);  // Undo final swap
        swap(A, l, j);  // Put pivot value in place

        // Put new subarrays onto Stack if they are small
        if ((l-i) > THRESHOLD) {   // Left partition
            Stack[++top] = i;
            Stack[++top] = l-1;
        }
        if ((j-l) > THRESHOLD) {   // Right partition
            Stack[++top] = l+1;
            Stack[++top] = j;
        }
    }
    insertionsortshiftInt(A);             // Final Insertion Sort
}



// Optimized Quicksort: Not recursive, and uses Inssort for small lists
// This version uses primitive integer values for the records
static void quicksortOptint(int[] A, int oi, int oj) { // Quicksort
    int[] Stack = new int[MAXSTACKSIZE]; // Stack for array bounds
    int listsize = oj-oi+1;
    int top = -1;
    int pivot;
    int pivotindex, l, r;

    Stack[++top] = oi;  // Initialize stack
    Stack[++top] = oj;

    while (top > 0) {   // While there are unprocessed subarrays
        // Pop Stack
        int j = Stack[top--];
        int i = Stack[top--];

        // Findpivot
        pivotindex = (i+j)/2;
        pivot = A[pivotindex];
        swap(A, pivotindex, j); // Stick pivot at end

        // Partition
        l = i-1;
        r = j;
        do {
            while (A[++l] < pivot);
            while ((r!=0) && (A[--r] > pivot));
            swap(A, l, r);
        } while (l < r);
        swap(A, l, r);  // Undo final swap
        swap(A, l, j);  // Put pivot value in place

        // Put new subarrays onto Stack if they are small
        if ((l-i) > THRESHOLD) {   // Left partition
            Stack[++top] = i;
            Stack[++top] = l-1;
        }
        if ((j-l) > THRESHOLD) {   // Right partition
            Stack[++top] = l+1;
            Stack[++top] = j;
        }
    }
    insertionsortshiftint(A);             // Final Insertion Sort
}
}
