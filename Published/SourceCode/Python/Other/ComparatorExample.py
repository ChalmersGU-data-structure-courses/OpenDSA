
from BaseAPI import Comparable
import functools 
import operator

class Person(Comparable):
    def __init__(self, given, family, birth):
        self.givenName  = given
        self.familyName = family
        self.birthYear  = birth

    def __str__(self):
        return f"{self.givenName} {self.familyName} ({self.birthYear})"

    # ...as above...
    def __eq__(self, other): return self.familyName == other.familyName
    def __ne__(self, other): return self.familyName != other.familyName
    def __lt__(self, other): return self.familyName <  other.familyName
    def __le__(self, other): return self.familyName <= other.familyName
    def __gt__(self, other): return self.familyName >  other.familyName
    def __ge__(self, other): return self.familyName >= other.familyName


# Note: Python doesn't have comparators like Java does.
# The most similar is to define a comparator-like function:
def birthYearComparator(one, other):
    return (-1 if one.birthYear < other.birthYear else
             1 if one.birthYear > other.birthYear else 0)


def givenNameComparator(one, other):
    return (-1 if one.givenName < other.givenName else
             1 if one.givenName > other.givenName else 0)

def fullNameComparator(one, other):
    return (-1 if one.familyName < other.familyName else
             1 if one.familyName > other.familyName else
            -1 if one.givenName  < other.givenName  else
             1 if one.givenName  > other.givenName  else 0)

def getPeople():
    return [Person("Unsuk", "Chin", 1961),
            Person("Anna", "Thorvaldsdóttir", 1977),
            Person("Andrea", "Tarrodi", 1981),
            Person("Diana", "Čemerytė", 1974),
            Person("Elfrida", "Andrée", 1841),
            Person("Guy", "d’Hardelot", 1858),
            Person("Nadia", "Boulanger", 1887),
            Person("Lili", "Boulanger", 1893),
            ]

print("\n### No order");
people = getPeople()
for p in people: print(p)

print("\n### Natural ordering")
people = getPeople()  # reset the people list
people.sort()
for p in people: print(p)

print("\n### Ordered by birth year (pre-Java-8 solution)")
byBirthYear = functools.cmp_to_key(birthYearComparator)
people = getPeople()  # reset the people list
people.sort(key=byBirthYear)
for p in people: print(p)

print("\n### Ordered by birth year (functional solution)")
byBirthYear = lambda person: person.birthYear
people = getPeople()  # reset the people list
people.sort(key=byBirthYear)
for p in people: print(p)

print("\n### Ordered by birth year (using a key extractor)")
byBirthYear = operator.attrgetter('birthYear')
people = getPeople()  # reset the people list
people.sort(key=byBirthYear)
for p in people: print(p)

print("\n### Ordered by given name (pre-Java-8 solution)")
byGivenName = functools.cmp_to_key(givenNameComparator)
people = getPeople()  # reset the people list
people.sort(key=byGivenName)
for p in people: print(p)

print("\n### Ordered by given name (functional solution)")
byGivenName = lambda person: person.givenName
people = getPeople()  # reset the people list
people.sort(key=byGivenName)
for p in people: print(p)

print("\n### Ordered by given name (using a key extractor)")
byGivenName = operator.attrgetter('givenName')
people = getPeople()  # reset the people list
people.sort(key=byGivenName)
for p in people: print(p)

print("\n### Ordered by full name: family name + given name (pre-Java-8 solution)")
byFullName = functools.cmp_to_key(fullNameComparator)
people = getPeople()  # reset the people list
people.sort(key=byFullName)
for p in people: print(p)

print("\n### Ordered by full name: family name + given name (functional solution and tuples)")
# In Python we can simply create a tuple, and it will sort the way we want
byFullName = lambda person: (person.familyName, person.givenName)
people = getPeople()  # reset the people list
people.sort(key=byFullName)
for p in people: print(p)

print("\n### Ordered by Swedish locale, case-insensitive")
print("# Note: There's a bug in Python's Swedish locale, so Č comes after all other letters")
import locale
locale.setlocale(locale.LC_COLLATE, 'sv_SE')
bySwedishLocale = lambda person: (locale.strxfrm(person.familyName.casefold()),
                                      locale.strxfrm(person.givenName.casefold()))
# Note: There's a bug in Python's Swedish locale, so Č comes after all other letters
people = getPeople()  # reset the people list
people.sort(key=bySwedishLocale)
for p in people: print(p)
# Note: Because of a bug in Python's Swedish locale, Diana Čemerytė is still printed last

print("\n### Ordered by Swedish locale, given name first")
bySwedishLocale = lambda person: (locale.strxfrm(person.givenName.casefold()),
                                  locale.strxfrm(person.familyName.casefold()))
people = getPeople()  # reset the people list
people.sort(key=bySwedishLocale)
for p in people: print(p)
