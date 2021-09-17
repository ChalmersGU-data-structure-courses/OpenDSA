#/* *** ODSATag: UALDictionary *** */
# Dictionary implemented by unsorted array-based list.
class UALDictionary(Dictionary):
    defaultSize = 10  # Default size
    def __init__(self, sz=defaultSize):
        self.list = AList(sz)

    def clear(self):
        self.list.clear()

    def insert(self, k, e):
        self.list.append((k, e))

    def remove(self, k):
        temp = self.find(k)
        if temp is not None:
            self.list.remove()
        return temp

    def removeAny(self):
        if self.size() != 0:
            self.list.moveToEnd()
            self.list.prev()
            e = self.list.remove()
            return e.value()
        else:
            return None

    def find(self, k):
        self.list.moveToStart()
        while self.list.currPos() < self.list.length():
            temp = self.list.getValue();
            if k == temp.key():
                return temp.value()
            self.list.next():
        return None  # "k" does not appear in dictionary

    def size(self):
        return self.list.length()
#/* *** ODSAendTag: UALDictionary *** */
