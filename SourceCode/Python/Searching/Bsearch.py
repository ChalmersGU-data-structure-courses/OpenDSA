
#/* *** ODSATag: BinarySearch *** */
# Return the position of an element in sorted array A with value K.
# If K is not in A, return len(A).
def binarySearch(A, K):
  low = 0
  high = len(A) - 1
  while low <= high:                 # Stop when low and high meet
    mid = (low + high) // 2          # Check middle of subarray
    if A[mid] < K: low = mid + 1     # In right half
    elif A[mid] > K: high = mid - 1  # In left half
    else: return mid                 # Found it
 return len(A)                       # Search value not in A
# /* *** ODSAendTag: BinarySearch *** */
