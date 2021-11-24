
#/* *** ODSATag: Sequential *** */
# Return the position of an element in a list.
# If the element is not found, return -1.
def sequentialSearch(elements, e):
    for i in range(len(elements)):  # For each element
        if elements[i] == e:        # if we found it
            return i                # return this position
    return -1                       # Otherwise, return -1
#/* *** ODSAendTag: Sequential *** */

if __name__ == '__main__':
    arr = [2, 3, 4, 5, 7, 10]
    print(arr)
    for key in [4, 6, 10]:
        pos = sequentialSearch(arr, key)
        print(f"Search for {key} --> position {pos}")
