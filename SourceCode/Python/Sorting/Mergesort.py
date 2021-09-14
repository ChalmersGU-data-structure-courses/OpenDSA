
#/* *** ODSATag: Mergesort *** */
def mergesort(A, temp, left, right):
  if left == right:                 # List has one record
    return
  mid = (left+right)/2              # Select midpoint
  mergesort(A, temp, left, mid)     # Mergesort first half
  mergesort(A, temp, mid+1, right)  # Mergesort second half
  for i in range(left, right+1):    # Copy subarray to temp
    temp[i] = A[i]
  # Do the merge operation back to A
  i1 = left
  i2 = mid + 1
  for curr in range(left, right+1):
    if i1 == mid+1:                 # Left sublist exhausted
      A[curr] = temp[i2++]
    elif i2 > right:                # Right sublist exhausted
      A[curr] = temp[i1++]
    elif temp[i1] <= temp[i2]:      # Get smaller value
      A[curr] = temp[i1++]
    else:
      A[curr] = temp[i2++]
#/* *** ODSAendTag: Mergesort *** */
