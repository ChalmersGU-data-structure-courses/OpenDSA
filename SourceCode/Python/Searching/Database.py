
class Map:
    def __init__(self):
        self._map = {}
    def put(self, k, v):
        self._map[k] = v
    def get(self, k):
        try:
            return self._map[k]
        except:
            return None
    def remove(self, k):
        try:
            result = self._map[k]
            del self._map[k]
        except:
            result = None
        return result
    def containsKey(self, k):
        return k in self._map
    def isEmpty(self):
        return self.size() == 0
    def size(self):
        return len(self._map)

    
#/* *** ODSATag: Database *** */
class Person:
    def __init__(self, pnr, name):
        self.pnr  = pnr    # personnummer
        self.name = name   # person's name
#/* *** ODSAendTag: Database *** */

    def __str__(self):
        return f"({self.pnr} {self.name})"
#/* *** ODSATag: Database *** */

class PersonDatabase:
    def __init__(self):
        self.database = Map()

    def put(self, p):
        """Put the person in the database."""
        self.database.put(p.pnr, p)

    def remove(self, p):
        """Remove a person from the database."""
        self.database.remove(p.pnr)

    def find(self, pnr):
        """Find the person who has a given personnummer."""
        return self.database.get(pnr)
#/* *** ODSAendTag: Database *** */

if __name__ == '__main__':
    db = PersonDatabase()
    db.put(Person("19481102-2345", "Lisa"))
    db.put(Person("19711031-1234", "Peter"))
    db.put(Person("20010622-4564", "Ylva"))

    print(db.find("20010622-4564"))

    db.remove(Person("20010622-4564", "Ylva"))

    print(db.find("20010622-4564"))
