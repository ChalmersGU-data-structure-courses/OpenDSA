
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

class SortedTreeMap<K, V> extends TreeMap<K, V> {
    public Iterator<K> keysBetween(K k1, K k2) {
        Map<K, V> keymap = subMap(k1, k2);
        Set<K> keys = keymap.keySet();
        return keys.iterator();
    }
}
    

class City {
    public String name;
    public Integer population;
    City(String name, Integer population) {
        this.name = name;
        this.population = population;
    }

    public String toString() {
        return name + " (" + population + ")";
    }
}

class CityPopulations {
    // Similar to the search engine, use a map where the value is a list of cities.
    SortedTreeMap<Integer, Set<City>> cities;
    CityPopulations() {
        cities = new SortedTreeMap<>();
    }

    // Add a new city to the database.
    public void add(City city) {
        // Get the set of documents containing this city.
        Set<City> set = cities.get(city.population);
        if (set == null)
            // This is the first city with this population
            set = new HashSet<>();

        // Add the city to the set
        set.add(city);
        cities.put(city.population, set);
    }

    // Find all cities with a population between lower and upper.
    public Set<City> findBetween(Integer lower, Integer upper) {
        Set<City> result = new HashSet<>();
        // The range query returns a set of keys, i.e. populations.
        Iterator<Integer> it = cities.keysBetween(lower, upper);
        while (it.hasNext()) {
            Integer population = it.next();
            // cities.get(population) returns the list of cities with that population.
            for (City city : cities.get(population))
                result.add(city);
        }
        return result;
    }

    public static void main(String args[]) {
        CityPopulations cp = new CityPopulations();
        cp.add(new City("Gothenburg", 604829));
        cp.add(new City("Stockholm", 1515017));
        cp.add(new City("Oslo", 1019513));
        cp.add(new City("Helsinki", 653835));
        int from = 600000, to = 1200000;
        if (args.length == 2) {
            from = Integer.parseInt(args[0]);
            to = Integer.parseInt(args[1]);
        }
        System.out.println("Cities with population between " + from + " and " + to + ":");
        for (City city : cp.findBetween(from, to))
            System.out.println("    " + city);
    }
}
