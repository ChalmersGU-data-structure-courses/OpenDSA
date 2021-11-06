
import java.util.*;
import java.text.Collator;

class Person implements Comparable<Person> {
    private String givenName;
    private String familyName;
    private int birthYear;

    // Constructor
    Person(String given, String family, int birth) {
        givenName  = given;
        familyName = family;
        birthYear  = birth;
    }

    // Getters
    public String givenName()  { return givenName  ; }
    public String familyName() { return familyName ; }
    public int    birthYear()  { return birthYear  ; }

    // Pretty-printing
    public String toString() {
        return givenName() + " " + familyName() + " (" + birthYear() + ")";
    }

    // Natural ordering
    // ...as above...
    public int compareTo(Person other) {
        return this.familyName().compareTo(other.familyName());
    }
}


class BirthYearComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        return Integer.compare(one.birthYear(), other.birthYear());
    }
}


class GivenNameComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        return one.givenName().compareTo(other.givenName());
    }
}

class FullNameComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        int cmp = one.familyName().compareTo(other.familyName());
        if (cmp != 0) return cmp;
        cmp = one.givenName().compareTo(other.givenName());
        if (cmp != 0) return cmp;
        return 0;
    }
}

public class DemoComparator {
static ArrayList<Person> getPeople() {
    ArrayList<Person> people = new ArrayList<>();
    people.add(new Person("Unsuk", "Chin", 1961));
    people.add(new Person("Anna", "Thorvaldsdóttir", 1977));
    people.add(new Person("Andrea", "Tarrodi", 1981));
    people.add(new Person("Diana", "Čemerytė", 1974));
    people.add(new Person("Elfrida", "Andrée", 1841));
    people.add(new Person("Guy", "d’Hardelot", 1858));
    people.add(new Person("Nadia", "Boulanger", 1887));
    people.add(new Person("Lili", "Boulanger", 1893));
    return people;
}
    
public static void main(String[] args) {
System.out.println("\n### No order");
ArrayList<Person> people = getPeople();
for (Person p : people) System.out.println(p);

System.out.println("\n### Natural ordering");
people = getPeople(); // reset the people list
Collections.sort(people);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by birth year (pre-Java-8 solution)");
Comparator<Person> byBirthYear = new BirthYearComparator();
people = getPeople(); // reset the people list
Collections.sort(people, byBirthYear);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by birth year (functional solution)");
byBirthYear = (one, other) -> Integer.compare(one.birthYear(), other.birthYear());
people = getPeople(); // reset the people list
Collections.sort(people, byBirthYear);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by birth year (using a key extractor)");
byBirthYear = Comparator.comparingInt(Person::birthYear);
people = getPeople(); // reset the people list
Collections.sort(people, byBirthYear);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by given name (pre-Java-8 solution)");
Comparator<Person> byGivenName = new GivenNameComparator();
people = getPeople(); // reset the people list
Collections.sort(people, byGivenName);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by given name (functional solution)");
byGivenName = (one, other) -> one.givenName().compareTo(other.givenName());
people = getPeople(); // reset the people list
Collections.sort(people, byGivenName);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by given name (using a key extractor)");
byGivenName = Comparator.comparing(Person::givenName);
people = getPeople(); // reset the people list
Collections.sort(people, byGivenName);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by full name: family name + given name (pre-Java-8 solution)");
Comparator<Person> byFullName = new FullNameComparator();
people = getPeople(); // reset the people list
Collections.sort(people, byFullName);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by full name: family name + given name (using key extractors and thenComparing)");
byFullName = Comparator.comparing(Person::familyName)
    .thenComparing(Person::givenName);
people = getPeople(); // reset the people list
Collections.sort(people, byFullName);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by Swedish locale, case-insensitive");
Collator swedishLocale = Collator.getInstance(new Locale("sv", "SE"));
swedishLocale.setStrength(Collator.PRIMARY);
Comparator<Person> bySwedishLocale = 
    Comparator.comparing(Person::familyName, swedishLocale)
    .thenComparing(Person::givenName, swedishLocale);
people = getPeople(); // reset the people list
Collections.sort(people, bySwedishLocale);
for (Person p : people) System.out.println(p);

System.out.println("\n### Ordered by Swedish locale, given name first");
Comparator<Person> bySwedishGivenName = 
    Comparator.comparing(Person::givenName, swedishLocale)
    .thenComparing(Person::familyName, swedishLocale);
people = getPeople(); // reset the people list
Collections.sort(people, bySwedishGivenName);
for (Person p : people) System.out.println(p);
}
}
