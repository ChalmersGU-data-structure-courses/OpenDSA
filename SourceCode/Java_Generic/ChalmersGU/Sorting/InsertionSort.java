
import java.util.Arrays;

class InsertionSort {

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

// static void sorttime(int[] B) {
//   int i;
//   Integer[] A = new Integer[B.length];
//   int[] Aint = new int[B.length];
//   int totaltime, runs;
//   int numruns = 5;

//   println("Doing timings on the basis of " + numruns + " runs");

// totaltime = 0;
// for (runs=0; runs<numruns; runs++) {
//   for (i=0; i<B.length; i++)
//     A[i] = new Integer(B[i]);
//   time1 = millis();
//   insertionsort(A);
//   time2 = millis();
//   checkorder(A);
// totaltime += (time2-time1);
// }
//   println("Standard Insertion Sort: Size " + testsize + ", Time: " + totaltime);

// totaltime = 0;
// for (runs=0; runs<numruns; runs++) {
//   for (i=0; i<B.length; i++)
//     Aint[i] = B[i];
//   time1 = millis();
//   insertionsort2(Aint);
//   time2 = millis();
//   checkorder(Aint);
// totaltime += (time2-time1);
// }
//   println("Standard Insertion Sort/No swaps: Size " + testsize + ", Time: " + totaltime);

// totaltime = 0;
// for (runs=0; runs<numruns; runs++) {
//   for(i=0; i<B.length; i++) Aint[i] = B[i];
//   time1 = millis();
//   insertionsortshift(Aint);
//   time2 = millis();
//   checkorder(Aint);
// totaltime += (time2-time1);
// }
//   println("shuffling Insertion Sort: Size " + testsize + ", Time: " + totaltime);

// totaltime = 0;
// for (runs=0; runs<numruns; runs++) {
//   for(i=0; i<B.length; i++) Aint[i] = B[i];
//   time1 = millis();
//   insertionsortshift2(Aint);
//   time2 = millis();
//   checkorder(Aint);
// totaltime += (time2-time1);
// }
//   println("shuffling Insertion Sort 2: Size " + testsize + ", Time: " + totaltime);
// }


// Same as insertionsortsuffle, but try != instead of < for the zero test
// This will only matter to JavaScript
static void insertionsortshift2(int[] A) {
  for (int i=1; i!=A.length; i++) { // Insert i'th record
    int j;
    int temp = A[i];
    for (j=i; (j!=0) && (temp < A[j-1]); j--) {
      A[j] = A[j-1];
    }
    A[j] = temp;
  }
}

/* *** ODSATag: InsertionOpt *** */
// Instead of swapping, "shift" the values down the array
static <T extends Comparable<T>> void insertionSortShift(T[] A) {
    for (int i = 1; i < A.length; i++) { // Insert i'th record
        T temp = A[i];
        int j = i;
        while (j > 0 && temp.compareTo(A[j-1]) < 0) {
            A[j] = A[j-1];
            j--;
        }
        A[j] = temp;
    }
}
/* *** ODSAendTag: InsertionOpt *** */

// Same as standard insertion sort, except get rid of the swap
// function call
static void insertionsort2(int[] A) {
  int temp;
  for (int i=1; i<A.length; i++) { // Insert i'th record
    for (int j=i; (j>0) && (A[j] < A[j-1]); j--) {
      temp = A[j]; A[j] = A[j-1]; A[j-1] = temp;
    }
  }
}

@SuppressWarnings("unchecked") // Generic array allocation
static Boolean sorttest(int[] B) {
  int i;
  Integer[] A = new Integer[B.length];
  for (i=0; i<B.length; i++) {
      A[i] = Integer.valueOf(B[i]);
  }
  insertionSort(A);
  if (!checkorder(A)) return false;

  //  KVPair[] AKV = (KVPair[])new Object[B.length];
  //  for (i=0; i<B.length; i++)
  //    AKV[i] = new KVPair(new Integer(B[i]), new Integer(B[i]));
  //  insertionsort(A);
  //  if (!checkorder(A)) return false;
  return true;
}

/* *** ODSATag: InsertionSort *** */
static <T extends Comparable<T>> void insertionSort(T[] A) {
    for (int i = 1; i < A.length; i++) { // Insert i'th record
        int j = i;
        while (j > 0 && A[j].compareTo(A[j-1]) < 0) {
            swap(A, j, j-1);
            j--;
        }
    }
}
/* *** ODSAendTag: InsertionSort *** */

    static public void main(String[] args) {
        if (args.length == 0)
            args = new String[] {"Peter", "Lisa", "Linda", "Petra", "Alban"};
        System.out.println("Before sorting: " + Arrays.toString(args));
        insertionSort(args);
        System.out.println("After sorting:  " + Arrays.toString(args));
        if (!checkorder(args))
            System.out.println("FAIL: not sorted!");
    }
}
