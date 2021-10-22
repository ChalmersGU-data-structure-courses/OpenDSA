
/* *** ODSATag: ComparatorDemo *** */
import java.util.*;
import java.text.Collator;

/* *** ODSATag: PersonCompareTo *** */
/* *** ODSATag: Person *** */
class Person implements Comparable<Person> {
/* *** ODSAendTag: PersonCompareTo *** */
    String givenName_;
    String familyName_;
    int birthYear_;

/* *** ODSAendTag: Person *** */
    // Constructor
    Person(String given, String family, int birth) {
        givenName_  = given;
        familyName_ = family;
        birthYear_  = birth;
    }

    // Getters
/* *** ODSATag: Person *** */
    public String givenName()  { return givenName_  ; }
    public String familyName() { return familyName_ ; }
    public int    birthYear()  { return birthYear_  ; }

/* *** ODSAendTag: Person *** */
    // Pretty-printing
/* *** ODSATag: Person *** */
    public String toString() {
        return givenName() + " " + familyName() + " (" + birthYear() + ")";
    }
/* *** ODSAendTag: Person *** */

    // Natural ordering
/* *** ODSATag: PersonCompareTo *** */
    // ...as above...
    public int compareTo(Person other) {
        return this.familyName().compareTo(other.familyName());
    }
/* *** ODSATag: Person *** */
}
/* *** ODSAendTag: Person *** */
/* *** ODSAendTag: PersonCompareTo *** */


/* *** ODSATag: BirthYearComparator *** */
class BirthYearComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        return Integer.compare(one.birthYear(), other.birthYear());
    }
}
/* *** ODSAendTag: BirthYearComparator *** */


/* *** ODSATag: GivenNameComparator *** */
class GivenNameComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        return one.givenName().compareTo(other.givenName());
    }
}
/* *** ODSAendTag: GivenNameComparator *** */

/* *** ODSATag: FullNameComparator *** */
class FullNameComparator implements Comparator<Person> {
    public int compare(Person one, Person other) {
        int cmp = one.familyName().compareTo(other.familyName());
        if (cmp != 0) return cmp;
        cmp = one.givenName().compareTo(other.givenName());
        if (cmp != 0) return cmp;
        return 0;
    }
}
/* *** ODSAendTag: FullNameComparator *** */

public class DemoComparator {
/* *** ODSATag: GetPeople *** */
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
/* *** ODSAendTag: GetPeople *** */
    
public static void main(String[] args) {
    System.out.println("\n### No order");
/* *** ODSATag: PrintPeople *** */
    ArrayList<Person> people = getPeople();
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: PrintPeople *** */

    System.out.println("\n### Natural ordering");
/* *** ODSATag: SortNatural *** */
    people = getPeople(); // reset the people list
    Collections.sort(people);
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: SortNatural *** */

    System.out.println("\n### Ordered by birth year (pre-Java-8 solution)");
/* *** ODSATag: SortByBirthYear *** */
    Comparator<Person> byBirthYear = new BirthYearComparator();
    people = getPeople(); // reset the people list
    Collections.sort(people, byBirthYear);
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: SortByBirthYear *** */

    System.out.println("\n### Ordered by birth year (functional solution)");
/* *** ODSATag: ByBirthYearFunctional *** */
    byBirthYear = (one, other) -> Integer.compare(one.birthYear(), other.birthYear());
/* *** ODSAendTag: ByBirthYearFunctional *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byBirthYear);
    for (Person p : people) System.out.println(p);

    System.out.println("\n### Ordered by birth year (using a key extractor)");
/* *** ODSATag: ByBirthYearKeyExtractor *** */
    byBirthYear = Comparator.comparingInt(Person::birthYear);
/* *** ODSAendTag: ByBirthYearKeyExtractor *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byBirthYear);
    for (Person p : people) System.out.println(p);

    System.out.println("\n### Ordered by given name (pre-Java-8 solution)");
/* *** ODSATag: SortByGivenName *** */
    Comparator<Person> byGivenName = new GivenNameComparator();
    people = getPeople(); // reset the people list
    Collections.sort(people, byGivenName);
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: SortByGivenName *** */

    System.out.println("\n### Ordered by given name (functional solution)");
/* *** ODSATag: ByGivenNameFunctional *** */
    byGivenName = (one, other) -> one.givenName().compareTo(other.givenName());
/* *** ODSAendTag: ByGivenNameFunctional *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byGivenName);
    for (Person p : people) System.out.println(p);

    System.out.println("\n### Ordered by given name (using a key extractor)");
/* *** ODSATag: ByGivenNameKeyExtractor *** */
    byGivenName = Comparator.comparing(Person::givenName);
/* *** ODSAendTag: ByGivenNameKeyExtractor *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byGivenName);
    for (Person p : people) System.out.println(p);

    System.out.println("\n### Ordered by full name: family name + given name (pre-Java-8 solution)");
/* *** ODSATag: ByFullName *** */
    Comparator<Person> byFullName = new FullNameComparator();
/* *** ODSAendTag: ByFullName *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byFullName);
    for (Person p : people) System.out.println(p);

    System.out.println("\n### Ordered by full name: family name + given name (using key extractors and thenComparing)");
/* *** ODSATag: ByFullNameThenComparing *** */
    byFullName = Comparator.comparing(Person::familyName)
        .thenComparing(Person::givenName);
/* *** ODSAendTag: ByFullNameThenComparing *** */
/* *** ODSATag: SortByFullName *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, byFullName);
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: SortByFullName *** */

    System.out.println("\n### Ordered by Swedish locale, case-insensitive");
/* *** ODSATag: BySwedishLocale *** */
    Collator swedishLocale = Collator.getInstance(new Locale("sv", "SE"));
    swedishLocale.setStrength(Collator.PRIMARY);
    Comparator<Person> bySwedishLocale = 
        Comparator.comparing(Person::familyName, swedishLocale)
        .thenComparing(Person::givenName, swedishLocale);
/* *** ODSAendTag: BySwedishLocale *** */
/* *** ODSATag: SortBySwedishLocale *** */
    people = getPeople(); // reset the people list
    Collections.sort(people, bySwedishLocale);
    for (Person p : people) System.out.println(p);
/* *** ODSAendTag: SortBySwedishLocale *** */

    System.out.println("\n### Ordered by Swedish locale, given name first");
    Comparator<Person> bySwedishGivenName = 
        Comparator.comparing(Person::givenName, swedishLocale)
        .thenComparing(Person::familyName, swedishLocale);
    people = getPeople(); // reset the people list
    Collections.sort(people, bySwedishGivenName);
    for (Person p : people) System.out.println(p);
}
}
/* *** ODSAendTag: ComparatorDemo *** */
