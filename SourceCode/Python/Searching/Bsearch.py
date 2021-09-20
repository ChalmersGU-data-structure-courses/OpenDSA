
#/* *** ODSATag: BinarySearch *** */
# Return the position of the element `e` in the sorted array `elements`.
# If `e` is not found, return -1.
def binarySearch(elements, e):
    low = 0
    high = len(elements) - 1
    while low <= high:             # Stop when low and high meet
        mid = (low + high) // 2    # Check middle of subarray
        if elements[mid] < e:
            low = mid + 1          # In right half
        elif elements[mid] > e:
            high = mid - 1         # In left half
        else:
            return mid             # Found it
    return -1                      # Search value not in array
# /* *** ODSAendTag: BinarySearch *** */
