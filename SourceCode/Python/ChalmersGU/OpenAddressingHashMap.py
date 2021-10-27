
from API import Map

#/* *** ODSATag: OpenAddressingHashMap *** */
#/* *** ODSATag: Header *** */
class OpenAddressingHashMap(Map):
#/* *** ODSAendTag: Header *** */
#/* *** ODSATag: Constants *** */
    _minCapacity = 8
    _minLoadFactor = 0.3
    _maxLoadFactor = 0.7
    _capacityMultiplier = 1.5
#/* *** ODSAendTag: Constants *** */
#/* *** ODSATag: Header *** */

    def __init__(self):
        self._initialiseTable(self._minCapacity)

    def _initialiseTable(self, capacity):
        self._internalTable = [None] * capacity
        self._mapSize = 0
        self._numDeleted = 0
#/* *** ODSAendTag: Header *** */

#/* *** ODSATag: Probe *** */
    def _probe(self, key, i):
        return i       # Linear probing
        # return i*i   # Quadratic probing
#/* *** ODSAendTag: Probe *** */

#/* *** ODSATag: HashIndex *** */
    def _hashAndProbe(self, key):
        home = hash(key) & 0x7fffffff   # Turn the hash code into a positive integer
        for i in range(len(self._internalTable)):
            pos = (home + self._probe(key, i)) % len(self._internalTable)
            elem = self._internalTable[pos]
            if elem is None or key == elem.key:
                return pos
        raise ValueError("Hash table full")
#/* *** ODSAendTag: HashIndex *** */

#/* *** ODSATag: Put *** */
    def put(self, key, value):
        i = self._hashAndProbe(key)
        elem = self._internalTable[i]
        old = None
        if elem is None:
            self._internalTable[i] = KVPair(key, value)
            self._mapSize += 1
        else:
            old = elem.value
            elem.value = value
        if self.loadFactor() > self._maxLoadFactor:
            self._resizeTable(len(self._internalTable) * self._capacityMultiplier)
        return old
#/* *** ODSAendTag: Put *** */

#/* *** ODSATag: Get *** */
    def get(self, key):
        i = self._hashAndProbe(key)
        elem = self._internalTable[i]
        return None if elem is None else elem.value
#/* *** ODSAendTag: Get *** */

#/* *** ODSATag: Remove *** */
    def remove(self, key):
        i = self._hashAndProbe(key)
        elem = self._internalTable[i]
        if elem is None:
            return None
        removed = elem.value
        elem.key = None
        elem.value = None
        self._mapSize -= 1
        self._numDeleted += 1
        if self._mapSize < self._minLoadFactor * len(self._internalTable):
            self._resizeTable(len(self._internalTable) / self._capacityMultiplier);
        return removed
#/* *** ODSAendTag: Remove *** */

    def containsKey(self, key):
        i = self._probe(key)
        return self._internalTable[i] is not None

#/* *** ODSATag: Resize *** */
    def _resizeTable(self, newCapacity):
        if newCapacity < self._minCapacity: return
        oldTable = self._internalTable
        self._initialiseTable(int(newCapacity))
        for elem in oldTable:
            if elem is not None and elem.key is not None:
                self.put(elem.key, elem.value)
#/* *** ODSAendTag: Resize *** */

    def isEmpty(self):
        return self._mapSize == 0

    def size(self):
        return self._mapSize

#/* *** ODSATag: LoadFactor *** */
    def loadFactor(self):
        return (self._mapSize + self._numDeleted) / len(self._internalTable)
#/* *** ODSAendTag: LoadFactor *** */

    def __iter__(self):
        for elem in self._internalTable:
            if elem is not None and elem.key is not None:
                yield elem.key


#/* *** ODSATag: KVPair *** */
# Python does not have internal classes, so we have to make the k/v pairs standalone.
class KVPair:
    def __init__(self, key, value):
        self.key = key
        self.value = value
#/* *** ODSAendTag: KVPair *** */
#/* *** ODSAendTag: OpenAddressingHashMap *** */


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printMap(m):
    print(len(m._internalTable), "[",
          " ".join("- " if e is None else "# " if e.key is None else f"{e.key}:{e.value}" for e in m._internalTable),
          "]", m.size(), "{", m.loadFactor(), "}")

if __name__ == '__main__':
    m = OpenAddressingHashMap()
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
