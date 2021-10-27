// Various Insertionsort algorithms, followed by driver function named "sorttime"

// Standard insertion sort (on int's)
static void insertionsort(int[] A, int startpos, int length) {
  int temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j] < A[j-1]); j--) {
      swap(A, j, j-1);
    }
}

// Same as standard insertion sort, except get rid of the swap
// function call
static void insertionsort2(int[] A, int startpos, int length) {
  int temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j] < A[j-1]); j--) {
      temp = A[j]; A[j] = A[j-1]; A[j-1] = temp;
    }
}

// Standard insertion sort for Integer class
static void insertionsortInteger(Integer[] A, int startpos, int length) {
  int temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j].compareTo(A[j-1]) < 0); j--) {
      swapInteger(A, j, j-1);
    }
}

// Standard insertion sort for double type array
static void insertionsortDouble(double[] A,int startpos, int length) {
  int temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j] < A[j-1]); j--) {
      swapDouble(A, j, j-1);
    }
}

// Same as standard insertion sort, except get rid of the swap
// function call
static void insertionsort2Integer(Integer[] A, int startpos, int length) {
  Integer temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j].compareTo(A[j-1]) < 0); j--) {
      temp = A[j]; A[j] = A[j-1]; A[j-1] = temp;
    }
}


// Same as standard insertion sort, except get rid of the swap
// function call
static void insertionsort2Double(double[] A, int startpos, int length) {
  double temp;
  for (int i = startpos + 1; i < startpos + length; i++) // Insert i'th record
    for (int j = i; (j>startpos) && (A[j] < A[j-1]); j--) {
      temp = A[j]; A[j] = A[j-1]; A[j-1] = temp;
    }
}


// -------------- Driver function. Invoke all of the sorts to be tested -----------------

static void sorttime(String arraySize,String dataType) {
Integer temporaryValue = Integer.parseInt(arraySize);
int testsize = temporaryValue.intValue(); // Put this here so that we can easily control the size for debugging


  System.out.println("Insertion Sort");
  System.out.println("Method Name,  Array Type, Array Size,Data Distribution,Time");

  // insertionsort
if(dataType.equals("int"))
{
  testsortallint("insertionsort", testsize);
  testsortallint("insertionsort2", testsize);
}
else if(dataType.equals("Integer"))
{
  testsortallInteger("insertionsortInteger",testsize);
  testsortallInteger("insertionsort2Integer",testsize);
}
else if(dataType.equals("double"))
{
  testsortallDouble("insertionsortDouble",testsize);
  testsortallDouble("insertionsort2Double",testsize);
}
}
