function sorttime(B) {
  var i;
  var A = [];
  A.length = B.length;
  var totaltime, runs;
  var numruns = 20;

totaltime = 0;
for (runs=0; runs<numruns; runs++) {
  for(i=0; i<B.length; i++) A[i] = B[i];
  time1 = new Date();
  insertionsort(A);
  time2 = new Date();
  checkorder(A);
totaltime += (time2-time1);
}
  console.log("Standard Insertion Sort: Size " + testsize + ", Time: " + totaltime);

totaltime = 0;
for (runs=0; runs<numruns; runs++) {
  for(i=0; i<B.length; i++) A[i] = B[i];
  time1 = new Date();
  insertionsort2(A);
  time2 = new Date();
  checkorder(A);
totaltime += (time2-time1);
}
  console.log("Standard Insertion Sort, no swap function: Size " + testsize + ", Time: " + totaltime);

totaltime = 0;
for (runs=0; runs<numruns; runs++) {
  for(i=0; i<B.length; i++) A[i] = B[i];
  time1 = new Date();
  insertionsortshift(A);
  time2 = new Date();
  checkorder(A);
totaltime += (time2-time1);
}
  console.log("Shifting Insertion Sort: Size " + testsize + ", Time: " + totaltime);

totaltime = 0;
for (runs=0; runs<numruns; runs++) {
  for(i=0; i<B.length; i++) A[i] = B[i];
  time1 = new Date();
  insertionsortshift2(A);
  time2 = new Date();
  checkorder(A);
totaltime += (time2-time1);
}
  console.log("Shifting Insertion Sort 2 (!==): Size " + testsize + ", Time: " + totaltime);
}


// Instead of swapping, "shift" the values down the array
function insertionsortshift(A) {
  for (var i = 1; i < A.length; i++) { // Insert i'th record
    var j;
    var temp = A[i];
    for (j = i; (j > 0) && (temp < A[j-1]); j--)
      A[j] = A[j-1];
    A[j] = temp;
  }
}

// Same as insertionsortshift, but try != instead of < for the zero test
// This will only matter to JavaScript
function insertionsortshift2(A) {
  for (var i = 1; i !== A.length; i++) { // Insert i'th record
    var j;
    var temp = A[i];
    for (j = i; (j !== 0) && (temp < A[j - 1]); j--)
      A[j] = A[j-1];
    A[j] = temp;
  }
}

// Same as standard insertion sort, except get rid of the swap
// function call
function insertionsort2(A) {
  var temp;
  for (var i = 1; i < A.length; i++) // Insert i'th record
    for (var j = i; (j > 0) && (A[j] < A[j - 1]); j--) {
      temp = A[j]; A[j] = A[j - 1]; A[j - 1] = temp;
    }
}

function success() {
  var bw = require("buffered-writer");
  bw.open("InssortTestSuccess").write("Success").close();
}

function sorttest(A) {
  insertionsort(A);
}

/* *** ODSATag: Insertionsort *** */
function insertionsort(A) {
  for (var i = 1; i < A.length; i++) // Insert i'th record
    for (var j = i; (j > 0) && (A[j] < A[j - 1]); j--)
      swap(A, j, j - 1);
}
/* *** ODSAendTag: Insertionsort *** */
