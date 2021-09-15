
def simplebinsort(A, B):
#/* *** ODSATag: simplebinsort *** */
  for i in range(len(A)):
    B[A[i]] = A[i]
#/* *** ODSAendTag: simplebinsort *** */


def simplebinsort2(A):
#/* *** ODSATag: simplebinsort2 *** */
for i in range(len(A)):
  while (A[i] != i):  # Swap element A[i] with A[A[i]]
    swap(A, i, A[i])
#/* *** ODSAendTag: simplebinsort2 *** */
}
