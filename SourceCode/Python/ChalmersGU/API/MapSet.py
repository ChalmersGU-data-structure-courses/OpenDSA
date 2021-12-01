
from API import Map, Set
from SeparateChainingHashMap import SeparateChainingHashMap

#/* *** ODSATag: MapSet *** */
#/* *** ODSATag: Header *** */
class MapSet(Set):
    _internalMap : Map
    # We're using a map from elements to the empty tuple ()

    def __init__(self, initialMap=None):
        if initialMap is None:
            # Use a separate chaining hash map as the default:
            self._internalMap = SeparateChainingHashMap()
        else:
            if not initialMap.isEmpty(): raise ValueError("The initial map must be empty!")
            self._internalMap = initialMap
#/* *** ODSAendTag: Header *** */

#/* *** ODSATag: Add *** */
    def add(self, x) -> bool:
        return self._internalMap.put(x, ()) is None
#/* *** ODSAendTag: Add *** */

#/* *** ODSATag: Remove *** */
    def remove(self, x) -> bool:
        return self._internalMap.remove(x) is not None
#/* *** ODSAendTag: Remove *** */

#/* *** ODSATag: Contains *** */
    def contains(self, x) -> bool:
        return self._internalMap.containsKey(x)
#/* *** ODSAendTag: Contains *** */

    def isEmpty(self) -> bool:
        return self._internalMap.isEmpty()

    def size(self) -> int:
        return self._internalMap.size()

    def __iter__(self):
        return iter(self._internalMap)
#/* *** ODSAendTag: MapSet *** */


######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed

    def _printSet(self):
        print(str(self._internalMap.size()) + " { " +
              ", ".join(str(elem) for elem in self._internalMap) + " }")


if __name__ == '__main__':
    theset = MapSet()
    print("Adding values")
    for i in range(40):
        theset.add(chr((i%25)+65))
        if i % 5 == 0: theset._printSet()
    theset._printSet()
    print("Removing values")
    i = 7
    while not theset.isEmpty():
        theset.remove(chr((i%25)+65))
        i += 11
        if theset.size() % 5 == 0: theset._printSet()
