
def swap(A, i, j):
    A[i], A[j] = A[j], A[i]

#/* Warning: Partition is sensitive. If we don't make the right
#   position actually cross the left, then it seems hard to get things
#   to work right when there is only one element in the partition
#   (i.e., a list of 2 elements). */
def partition(A, left, right, pivot):
    swap(A, left, pivot)   # Put pivot at the leftmost index
    pivot = left
    left += 1              # Start partitioning from the element after the pivot

    pivotValue = A[pivot]
    while True:
        # Move `left` right as far as possible. Stop if equal to pivot!
        # Also stop if `left` moves past `right` – this is important, 
        # so that `left` stops if it moves past the end of the array.
        while left <= right and A[left] < pivotValue:
            left += 1

        # Move `right` left as far as possible. Stop if equal to pivot!
        # Also stop if `right` moves all the way left to `left`,
        # see above for why.
        while left <= right and A[right] > pivotValue:
            right -= 1

        # Stop here if `left` and `right` passed each other.
        if left > right:
            break

        # Otherwise, swap the elements and move `left` and `right` on by 1.
        swap(A, left, right)
        left += 1; right -= 1

    swap(A, pivot, right)   # Finally, move the pivot into place
    return right            # Return the position of the pivot

def findPivot(A, i, j):
    """Not-so-good pivot selection: always choose the middle element."""
    return (i + j) // 2

def quickSort(A, left, right):
    if left >= right:                          # Base case: Subarray length is <= 1
        return
    pivot = findPivot(A, left, right)          # Pick a pivot index
    pivot = partition(A, left, right, pivot)   # Partition the subarray; update pivot with its new position
    quickSort(A, left, pivot-1)                # Sort left partition
    quickSort(A, pivot+1, right)               # Sort right partition

if __name__ == '__main__':
    arr = [3, 6, 9, 2, 8, 4, 5, 1, 7]
    quickSort(arr, 0, len(arr)-1)
    print(arr)
