
from API import Map
from KVPair import KVPair
from LinkedList import LinkedList

#/* *** ODSATag: ListMap *** */
#/* *** ODSATag: Header *** */
class LinkedMap(Map):
    def __init__(self):
        # This could also be a DynamicArrayList.
        self._internalList = LinkedList()
#/* *** ODSAendTag: Header *** */

#/* *** ODSATag: Put *** */
    def put(self, key, value):
        for entry in self._internalList:
            if key == entry.key:
                oldValue = entry.value
                entry.value = value
                return oldValue
        # If we're using a DynamicArrayList we should add at the end of the list instead.
        self._internalList.add(0, KVPair(key, value))
        return None
#/* *** ODSAendTag: Put *** */

#/* *** ODSATag: Get *** */
    def get(self, key):
        for entry in self._internalList:
            if key == entry.key:
                return entry.value
        return None
#/* *** ODSAendTag: Get *** */

#/* *** ODSATag: Remove *** */
    # Python iterators do not have a .remove() method,
    # so here is a slower version.
    def remove(self, key):
        for i, entry in enumerate(self._internalList):
            if key == entry.key:
                break
        else:
            return None
        removed = entry.value
        self._internalList.remove(i)
        return removed
#/* *** ODSAendTag: Remove *** */

#/* *** ODSATag: ContainsKey *** */
    def containsKey(self, key):
        return self.get(key) is not None
#/* *** ODSAendTag: ContainsKey *** */

#/* *** ODSATag: EmptySize *** */
    def isEmpty(self):
        return self._internalList.isEmpty()

    def size(self):
        return self._internalList.size()
#/* *** ODSAendTag: EmptySize *** */

#/* *** ODSATag: Iterator *** */
    def __iter__(self):
        for entry in self._internalList:
            yield entry.key
#/* *** ODSAendTag: Iterator *** */
#/* *** ODSAendTag: ListMap *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printMap(m):
    print("[", " ".join(f"{k}:{m.get(k)}" for k in m), "]", m.size())

if __name__ == '__main__':
    m = LinkedMap()
    _printMap(m)
    print("Putting values")
    for i in range(40):
        m.put(chr((i%25)+65), i)
        if i % 5 == 0: _printMap(m)
    _printMap(m)
    print("Removing values")
    i = 7
    while not m.isEmpty():
        m.remove(chr((i%25)+65))
        i += 11
        if m.size() % 5 == 0: _printMap(m)
