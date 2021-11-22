# /* *** ODSATag: Heapsort *** */
def heapsort(a):
    # Convert the array to a heap
    build_heap(a)

    # Repeatedly find and remove the minimum element
    heapsize = len(a)
    while heapsize > 0:
        a[0], a[heapsize - 1] = a[heapsize - 1], a[0]
        heapsize -= 1
        sift_down(a, heapsize, 0)

def build_heap(a):
    # Go backwards from the first non-leaf, sifting down each one
    for i in reversed(range(len(a)//2)):
        sift_down(a, len(a), i)

# Standard sift-down method for max heaps
def sift_down(a, heapsize, i):
    while left_child_index(i) < heapsize:
        l = left_child_index(i)
        r = right_child_index(i)

        if a[l] > a[i]:
            largest = l
        else:
            largest = i

        if r < heapsize and a[r] > a[largest]:
            largest = r

        if largest == i:
            return
        else:
            a[i], a[largest] = a[largest], a[i]
            i = largest

# Left and right child indexes.
def left_child_index(i):
    return 2*i+1

def right_child_index(i):
    return 2*i+2
# /* *** ODSAendTag: Heapsort *** */

if __name__ == '__main__':
    x = [3,1,4,1,5,9,2,6,5]
    heapsort(x)
    print(x)
