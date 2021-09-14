
#/* *** ODSATag: Sequential *** */
# Return the position of an element in array A with value K.
# If K is not in A, return len(A).
def sequential(A, K):
  for i in range(len(A)):  # For each element
    if A[i] == K:          # if we found it
       return i            # return this position
  return len(A)            # Otherwise, return the array length
#/* *** ODSAendTag: Sequential *** */
