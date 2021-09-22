
#/* *** ODSATag: Sequential *** */
# Return the position of the element `e` in the array `elements`.
# If `e` is not found, return -1.
def sequentialSearch(elements, e):
    for i in range(len(elements)):  # For each element
        if elements[i] == e:        # if we found it
            return i                # return this position
    return -1                       # Otherwise, return -1
#/* *** ODSAendTag: Sequential *** */
