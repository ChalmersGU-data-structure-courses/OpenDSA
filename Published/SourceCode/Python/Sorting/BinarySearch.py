
# Return the position of an element in a list.
# If the element is not found, return -1.
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

if __name__ == '__main__':
    arr = [2, 3, 4, 5, 7, 10]
    print(arr)
    for key in [4, 6, 10]:
        pos = binarySearch(arr, key)
        print(f"Search for {key} --> position {pos}")
