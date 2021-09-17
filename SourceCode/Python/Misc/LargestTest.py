
#/* *** ODSATag: Largest *** */
# Return position of largest value in integer array A
def largest(A):
    currlarge = 0   # Position of largest element seen
    for i in range(1, A.length):  # For each element
        if A[currlarge] < A[i]:   # if A[i] is larger
             currlarge = i        # remember its position
    return currlarge              # Return largest position
#/* *** ODSAendTag: Largest *** */
