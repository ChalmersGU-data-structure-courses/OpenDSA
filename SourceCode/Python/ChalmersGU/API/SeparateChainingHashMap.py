
from API import Map
from LinkedMap import LinkedMap

#/* *** ODSATag: SeparateChainingHashMap *** */
#/* *** ODSATag: Header *** */
class SeparateChainingHashMap(Map):
#/* *** ODSAendTag: Header *** */
#/* *** ODSATag: Constants *** */
    _minCapacity = 8
    _minLoadFactor = 0.5
    _maxLoadFactor = 2.0
    _capacityMultiplier = 1.5
#/* *** ODSAendTag: Constants *** */

#/* *** ODSATag: Constructor *** */
    def __init__(self):
        self._initialiseTable(self._minCapacity)

    def _initialiseTable(self, capacity):
        capacity = int(capacity)
        self._internalTable = [None] * capacity
        for i in range(capacity):
            self._internalTable[i] = LinkedMap()
        self._mapSize = 0
#/* *** ODSAendTag: Constructor *** */

#/* *** ODSATag: HashIndex *** */
    def _hash(self, key):
        return (hash(key) & 0x7fffffff) % len(self._internalTable)
#/* *** ODSAendTag: HashIndex *** */

#/* *** ODSATag: Put *** */
    def put(self, key, value):
        i = self._hash(key)
        old = self._internalTable[i].put(key, value)
        if old is None:
            self._mapSize += 1
            if self.loadFactor() > self._maxLoadFactor:
                self._resizeTable(len(self._internalTable) * self._capacityMultiplier)
        return old
#/* *** ODSAendTag: Put *** */

#/* *** ODSATag: Get *** */
    def get(self, key):
        i = self._hash(key)
        return self._internalTable[i].get(key)
#/* *** ODSAendTag: Get *** */

#/* *** ODSATag: Remove *** */
    def remove(self, key):
        i = self._hash(key)
        removed = self._internalTable[i].remove(key)
        if removed is not None:
            self._mapSize -= 1
            if self.loadFactor() < self._minLoadFactor:
                self._resizeTable(len(self._internalTable) / self._capacityMultiplier)
        return removed
#/* *** ODSAendTag: Remove *** */

    def containsKey(self, key):
        i = self._hash(key)
        return self._internalTable[i].containsKey(key)

#/* *** ODSATag: Resize *** */
    def _resizeTable(self, newCapacity):
        if newCapacity < self._minCapacity: return
        oldTable = self._internalTable
        self._initialiseTable(int(newCapacity))
        for bin in oldTable:
            for k in bin:
                self.put(k, bin.get(k))
#/* *** ODSAendTag: Resize *** */

    def isEmpty(self):
        return self._mapSize == 0

    def size(self):
        return self._mapSize

#/* *** ODSATag: LoadFactor *** */
    def loadFactor(self):
        return self._mapSize / len(self._internalTable)
#/* *** ODSAendTag: LoadFactor *** */

    def __iter__(self):
        for bin in self._internalTable:
            for key in bin:
                yield key
# #/* *** ODSAendTag: SeparateChainingHashMap *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printMap(map):
    print(len(map._internalTable), "[",
          " | ".join(" ".join(f"{k}:{bin.get(k)}" for k in bin) for bin in map._internalTable),
          "]", m.size(), "{", m.loadFactor(), "}")

if __name__ == '__main__':
    m = SeparateChainingHashMap()
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
