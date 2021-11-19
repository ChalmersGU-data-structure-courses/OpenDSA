
from SpellCheck import Set
from Database import Map

class SortedMap(Map):
    # def firstKey(self):                """Returns the first (smallest) key. Raises an exception if the map is empty."""
    # def lastKey(self):                 """Returns the last (largest) key. Raises an exception if the map is empty."""
    # def floorKey(self, key):           """Returns the closest key <= k, or None if there is no key."""
    # def ceilingKey(self, key):         """Returns the closest key >= k, or None if there is no key."""
    # def lowerKey(self, key):           """Returns the closest key < k, or None if there is no such element."""
    # def higherKey(self, key):          """Returns the closest key > k, or None if there is no such element."""
    def keysBetween(self, key1, key2):
        """Returns all keys k such that k1 <= k <= k2."""
        for k in self._map:
            if key1 <= k <= key2:
                yield k


class City:
    def __init__(self, name, population):
        self.name = name
        self.population = population

    def __str__(self):
        return f"{self.name} ({self.population})"

class CityPopulations:
    # Similar to the search engine, use a map where the value is a list of cities.
    def __init__(self):
        self.cities = SortedMap()

    def add(self, city):
        """Add a new city to the database."""
        # Get the set of documents containing this city
        set = self.cities.get(city.population)
        if set is None:
            # This is the first city with this population
            set = Set()

        # Add the city to the set
        set.add(city)
        self.cities.put(city.population, set)

    def findBetween(self, lower, upper):
        """Find all cities with a population between lower and upper"""
        result = Set()
        # The range query returns a set of keys, i.e. populations.
        for population in self.cities.keysBetween(lower, upper):
            # cities.get(population) returns the list of cities with that population.
            for city in self.cities.get(population):
                result.add(city)
        return result

if __name__ == '__main__':
    cp = CityPopulations()
    cp.add(City("Gothenburg", 604829))
    cp.add(City("Stockholm", 1515017))
    cp.add(City("Oslo", 1019513))
    cp.add(City("Helsinki", 653835))
    frm, to = 600000, 1200000
    import sys
    if len(sys.argv) == 3:
        frm = int(sys.argv[1])
        to = int(sys.argv[2])
    print(f"Cities with population between {frm} and {to}:")
    for city in cp.findBetween(frm, to):
        print(f"    {city}")
