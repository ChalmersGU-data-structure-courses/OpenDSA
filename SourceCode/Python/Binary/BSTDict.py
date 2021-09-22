# Dictionary implementation using BST
# This uses pairs (key, value) to manage the key/value pairs
class BSTDict(Map):
    def __init__(self):
        self.theBST = BST()

    def insert(self, k, e):
        """Insert a record
        k: the key for the record being inserted.
        e: the record being inserted.
        """
        self.theBST.insert((k, e))

    def remove(self, k):
        """Remove and return a record.
        k: the key of the record to be removed.
        Return a maching record. If multiple records match "k", remove
        an arbitrary one. Return null if no record with key "k" exists.
        """
        temp = self.theBST.remove(k)
        if temp is None:
            return temp
        else:
            return temp.value()

    def removeAny(self):
        """Remove and return an arbitrary record from dictionary.
        Return the record removed, or null if none exists.
        """
        if self.theBST.size() == 0:
            return None
        temp = self.theBST.remove(self.theBST.root().value().key())
        return temp.value()

    def find(self, k):
        """Return a record matching "k" (null if none exists).
        If multiple records match, return an arbitrary one.
        k: the key of the record to find
        """
        temp = self.theBST.find(k)
        if temp is None:
            return temp
        else:
            return temp.value()

    def size(self):
        """Return the number of records in the dictionary."""
        return theBST.size()
