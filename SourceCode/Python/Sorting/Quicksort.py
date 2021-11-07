
#/* Warning: Partition is sensitive. If we don't make the right
#   position actually cross the left, then it seems hard to get things
#   to work right when there is only one element in the partition
#   (i.e., a list of 2 elements). */
#/* *** ODSATag: partition *** */
def partition(A, left, right, pivot):
  # Move bounds inward until they meet
  while left <= right:
    while right >= left && A[left] < pivot:
      left += 1
    while right >= left && A[right] > pivot:
      right -= 1
    # Swap out-of-place values
    if right > left:
      Swap.swap(A, left, right)
  # Return first position in right partition
  return left
#/* *** ODSAendTag: partition *** */

#/* *** ODSATag: findpivot *** */
def findpivot(A, i, j):
  return (i+j)/2
#/* *** ODSAendTag: findpivot *** */

#/* *** ODSATag: Quicksort *** */
def quicksort(A, i, j):
  if i >= j: return                # Base case: array of size 0 or 1
  pivotindex = findpivot(A, i, j)  # Pick a pivot
  swap(A, pivotindex, j)           # Stick pivot at end
  k = partition(A, i, j-1, A[j])   # k will be the first position in the right subarray
  swap(A, k, j)                    # Put pivot in place
  quicksort(A, i, k-1)             # Sort left partition
  quicksort(A, k+1, j)             # Sort right partition
#/* *** ODSAendTag: Quicksort *** */
