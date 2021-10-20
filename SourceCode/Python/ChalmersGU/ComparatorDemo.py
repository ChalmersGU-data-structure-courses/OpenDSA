 #
# This program is accompanying the tutorial on comparables and comparators.
# Run without any arguments.

from BaseAPI import Comparable
import functools 
import operator

#/* *** ODSATag: PersonCompareTo *** */
#/* *** ODSATag: Person *** */
class Person(Comparable):
#/* *** ODSAendTag: PersonCompareTo *** */
    def __init__(self, given, family, birth):
        self.givenName  = given
        self.familyName = family
        self.birthYear  = birth

    def __str__(self):
        return f"{self.givenName} {self.familyName} ({self.birthYear})"
#/* *** ODSAendTag: Person *** */

#/* *** ODSATag: PersonCompareTo *** */
    # ...as above...
    def __eq__(self, other): return self.familyName == other.familyName
    def __ne__(self, other): return self.familyName != other.familyName
    def __lt__(self, other): return self.familyName <  other.familyName
    def __le__(self, other): return self.familyName <= other.familyName
    def __gt__(self, other): return self.familyName >  other.familyName
    def __ge__(self, other): return self.familyName >= other.familyName
#/* *** ODSAendTag: PersonCompareTo *** */


#/* *** ODSATag: BirthYearComparator *** */
# Note: Python doesn't have comparators as Java does.
# The most similar is to define a comparator-like function:
def birthYearComparator(one, other):
    return (-1 if one.birthYear < other.birthYear else
             1 if one.birthYear > other.birthYear else 0)
#/* *** ODSAendTag: BirthYearComparator *** */


#/* *** ODSATag: GivenNameComparator *** */
def givenNameComparator(one, other):
    return (-1 if one.givenName < other.givenName else
             1 if one.givenName > other.givenName else 0)
#/* *** ODSAendTag: GivenNameComparator *** */

#/* *** ODSATag: FullNameComparator *** */
def fullNameComparator(one, other):
    return (-1 if one.familyName < other.familyName else
             1 if one.familyName > other.familyName else
            -1 if one.givenName  < other.givenName  else
             1 if one.givenName  > other.givenName  else 0)
#/* *** ODSAendTag: FullNameComparator *** */

#/* *** ODSATag: GetPeople *** */
def getPeople():
    people = [Person("Unsuk", "Chin", 1961),
              Person("Anna", "Thorvaldsdóttir", 1977),
              Person("Andrea", "Tarrodi", 1981),
              Person("Diana", "Čemerytė", 1974),
              Person("Elfrida", "Andrée", 1841),
              Person("Guy", "d’Hardelot", 1858),
              Person("Nadia", "Boulanger", 1887),
              Person("Lili", "Boulanger", 1893),
              ]
    return people
#/* *** ODSAendTag: GetPeople *** */

if __name__ == '__main__':
    print("\n### No order");
#/* *** ODSATag: PrintPeople *** */
    people = getPeople()
    for p in people: print(p)
#/* *** ODSAendTag: PrintPeople *** */

    print("\n### Natural ordering")
#/* *** ODSATag: SortNatural *** */
    people = getPeople()  # reset the people list
    people.sort()
    for p in people: print(p)
#/* *** ODSAendTag: SortNatural *** */

    print("\n### Ordered by birth year (pre-Java-8 solution)")
#/* *** ODSATag: SortByBirthYear *** */
    byBirthYear = functools.cmp_to_key(birthYearComparator)
    people = getPeople()  # reset the people list
    people.sort(key=byBirthYear)
    for p in people: print(p)
#/* *** ODSAendTag: SortByBirthYear *** */

    print("\n### Ordered by birth year (functional solution)")
#/* *** ODSATag: ByBirthYearFunctional *** */
    byBirthYear = lambda person: person.birthYear
#/* *** ODSAendTag: ByBirthYearFunctional *** */
    people = getPeople()  # reset the people list
    people.sort(key=byBirthYear)
    for p in people: print(p)

    print("\n### Ordered by birth year (using a key extractor)")
#/* *** ODSATag: ByBirthYearKeyExtractor *** */
    byBirthYear = operator.attrgetter('birthYear')
#/* *** ODSAendTag: ByBirthYearKeyExtractor *** */
    people = getPeople()  # reset the people list
    people.sort(key=byBirthYear)
    for p in people: print(p)

    print("\n### Ordered by given name (pre-Java-8 solution)")
#/* *** ODSATag: SortByGivenName *** */
    byGivenName = functools.cmp_to_key(givenNameComparator)
    people = getPeople()  # reset the people list
    people.sort(key=byGivenName)
    for p in people: print(p)
#/* *** ODSAendTag: SortByGivenName *** */

    print("\n### Ordered by given name (functional solution)")
#/* *** ODSATag: ByGivenNameFunctional *** */
    byGivenName = lambda person: person.givenName
#/* *** ODSAendTag: ByGivenNameFunctional *** */
    people = getPeople()  # reset the people list
    people.sort(key=byGivenName)
    for p in people: print(p)

    print("\n### Ordered by given name (using a key extractor)")
#/* *** ODSATag: ByGivenNameKeyExtractor *** */
    byGivenName = operator.attrgetter('givenName')
#/* *** ODSAendTag: ByGivenNameKeyExtractor *** */
    people = getPeople()  # reset the people list
    people.sort(key=byGivenName)
    for p in people: print(p)

    print("\n### Ordered by full name: family name + given name (pre-Java-8 solution)")
#/* *** ODSATag: ByFullName *** */
    byFullName = functools.cmp_to_key(fullNameComparator)
#/* *** ODSAendTag: ByFullName *** */
    people = getPeople()  # reset the people list
    people.sort(key=byFullName)
    for p in people: print(p)

    print("\n### Ordered by full name: family name + given name (functional solution and tuples)")
#/* *** ODSATag: ByFullNameThenComparing *** */
    byFullName = lambda person: (person.familyName, person.givenName)
#/* *** ODSAendTag: ByFullNameThenComparing *** */
#/* *** ODSATag: SortByFullName *** */
    people = getPeople()  # reset the people list
    people.sort(key=byFullName)
    for p in people: print(p)
#/* *** ODSAendTag: SortByFullName *** */

    print("\n### Ordered by Swedish locale, case-insensitive")
    print("# Note: There's a bug in Python's Swedish locale, so Č comes after all other letters")
#/* *** ODSATag: BySwedishLocale *** */
    import locale
    locale.setlocale(locale.LC_COLLATE, 'sv_SE')
    bySwedishLocale = lambda person: (locale.strxfrm(person.familyName.casefold()),
                                      locale.strxfrm(person.givenName.casefold()))
    # Note: There's a bug in Python's Swedish locale, so Č comes after all other letters
#/* *** ODSAendTag: BySwedishLocale *** */
#/* *** ODSATag: SortBySwedishLocale *** */
    people = getPeople()  # reset the people list
    people.sort(key=bySwedishLocale)
    for p in people: print(p)
#/* *** ODSAendTag: SortBySwedishLocale *** */

    print("\n### Ordered by Swedish locale, given name first")
    bySwedishLocale = lambda person: (locale.strxfrm(person.givenName.casefold()),
                                      locale.strxfrm(person.familyName.casefold()))
    people = getPeople()  # reset the people list
    people.sort(key=bySwedishLocale)
    for p in people: print(p)
