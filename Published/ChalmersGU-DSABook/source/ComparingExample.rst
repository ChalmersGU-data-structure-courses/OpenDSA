.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['comparables-and-comparators-in-java', 'comparables', 'comparators-the-old-way', 'comparators-the-new-functional-interface-in-java-8', 'comparing-fields-using-key-extractors', 'comparing-several-fields', 'case-insensitive-and-language-specific-comparisons', 'running-the-program', '…and-what-about-the-names'];</script>

.. _ComparingExample:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ComparingExample";ODSA.SETTINGS.MODULE_LONG_NAME = "Comparables and comparators, an example";ODSA.SETTINGS.MODULE_CHAPTER = "Introduction"; ODSA.SETTINGS.BUILD_DATE = "2021-10-20 13:26:11"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Peter Ljunglöf
   :requires: comparison


Comparables and comparators, an example
==============================================

*Note*: Despite treating comparables and comparators in Java, the code here is written in pseudocode and ignores some Java-specific details, so to actually implement it you have to think a little bit and read the Java documentation to translate it into Java. (You can also look at the associated Java file at the end of this document, but that's cheating :).

Comparables and comparators in Java
--------------------------------------------

In Java there are two main ways of comparing objects. The one that most people find easier to understand is *comparable*. But there is an alternative, *comparator*, which is very useful. 

As a running example we use a class for persons:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: Person

Also, let’s create an array of persons that we can use later:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: GetPeople

Now we can print the list:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: PrintPeople

Which results in:

     | Unsuk Chin (1961)
     | Anna Thorvaldsdóttir (1977)
     | Andrea Tarrodi (1981)
     | Diana Čemerytė (1974)
     | Elfrida Andrée (1841)
     | Guy d’Hardelot (1857)
     | Nadia Boulanger (1887)
     | Lili Boulanger (1893)

Comparables
----------------

With the Comparable interface we can define “natural ordering” for a class (`Java 8 Comparable`_):

.. _`Java 8 Comparable`: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html

.. codeinclude:: ChalmersGU/BaseAPI
   :tag: ComparableADT

Let’s say that we want the natural ordering to be to compare persons by their family name. Then we let ``Person`` implement the ``Comparable`` interface by overriding ``.compareTo(…)``:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: PersonCompareTo

Now we can sort our people array according to family name:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: SortNatural

Resulting in:

    | Elfrida Andrée (1841)
    | Nadia Boulanger (1887)
    | Lili Boulanger (1893)
    | Unsuk Chin (1961)
    | Andrea Tarrodi (1981)
    | Anna Thorvaldsdóttir (1977)
    | Guy d’Hardelot (1857)
    | Diana Čemerytė (1974)

Two things to note, which we address later: 

1. Guy d’Hardelot and Diana Čemerytė come last – this is because ``.compareTo(…)`` gives a case-sensitive ordering and doesn’t care ignore diacritics
2. Nadia Boulanger comes before Lili, even though L comes before N in the alphabet

Comparators, the old way
----------------------------

What if we sometimes want to sort the list according to some other ordering, e.g., birth year or given name? Enter *comparators*, and here is the interface (`Java 8 Comparator`_):

.. _`Java 8 Comparator`: https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html

.. codeinclude:: ChalmersGU/BaseAPI
   :tag: ComparatorADT

To use this we have to implement a separate class for each ordering we want to use. Here's one for comparing birth year:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: BirthYearComparator


*Notes*:

1. Don’t compare numbers by using subtraction! This might lead to overflow and rounding errors. Instead use the static ``.compare(…)`` methods that are built into the number classes (``Integer``, ``Double``, etc).
2. Since numbers are not objects, you cannot use ``one.birthYear.compareTo(…)``. You can do ``new Integer(one.birthYear).compareTo(…)``, or you can use ``Integer.compare(…)`` as above.

And here’s the class for comparing by given name:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: GivenNameComparator

To use them you have to first create an object, i.e., instantiate the comparator:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: SortByBirthYear

Result:

   | Elfrida Andrée (1841)
   | Guy d’Hardelot (1857)
   | Nadia Boulanger (1887)
   | Lili Boulanger (1893)
   | Unsuk Chin (1961)
   | Diana Čemerytė (1974)
   | Anna Thorvaldsdóttir (1977)
   | Andrea Tarrodi (1981)

And similar for given names:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: SortByGivenName

Result:

   | Andrea Tarrodi (1981)
   | Anna Thorvaldsdóttir (1977)
   | Diana Čemerytė (1974)
   | Elfrida Andrée (1841)
   | Guy d’Hardelot (1857)
   | Lili Boulanger (1893)
   | Nadia Boulanger (1887)
   | Unsuk Chin (1961)

Comparators, the new functional interface in Java 8
----------------------------------------------------------

Since Java 8, there’s a functional interface which can be used to build comparators (and many other things). So we don’t have to write the class definitions, and instead write similar to we would do in Python or Haskell:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: ByBirthYearFunctional, ByGivenNameFunctional

::

   Comparator<Person> bybirthYear = (one, other) -> 
       Integer.compare(one.birthYear, other.birthYear)

   Comparator<Person> byGivenName = (one, other) ->
       one.givenName.compareTo(other.givenName)

Yay! That’s a lot nicer than the clumsy class definition
(``class BirthYearComparator implements Comparator<Person>``, etc).

Comparing fields using key extractors
----------------------------------------

In many cases (including our example case), we only want to compare some fields in a class. Then we can use *key extractors* to simplify even more:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: ByBirthYearKeyExtractor

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: ByGivenNameKeyExtractor

::

   Comparator<Person> byBirthYear = Comparator.comparingInt(Person::birthYear)

   Comparator<Person> byGivenName = Comparator.comparing(Person::givenName)

* *Note*: We use ``.comparingInt(…)`` when defining ``byBirthYear``. It’s not strictly necessary (i.e., we can use ``.comparing(…)``), but it makes things slightly more efficient.

Comparing several fields
---------------------------

Remember the natural ordering? The problem with only comparing the family name is that if two persons have the same they keep their internal order. So, Nadia Boulanger comes before Lili Boulanger even though L precedes N in the alphabet. 

What we want is to be able to compare several fields. The old and not-so-good solution is to use clumsy if-then-elses, like this:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: FullNameComparator

After this we can instantiate a specific comparator:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: ByFullName

If we have many fields this gets quite cumbersome (and error-prone). But using the functional interface, and the magic ``.thenComparing(…)`` method, it’s really easy:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: ByFullNameThenComparing

And here it is in action:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: SortByFullName

Result:

   | Elfrida Andrée (1841)
   | Lili Boulanger (1893)
   | Nadia Boulanger (1887)
   | Unsuk Chin (1961)
   | Andrea Tarrodi (1981)
   | Anna Thorvaldsdóttir (1977)
   | Guy d’Hardelot (1857)
   | Diana Čemerytė (1974)

As you can see, Lili now comes before Nadia. But there’s still the problem with Guy and Diana coming last in the list.

Case-insensitive and language-specific comparisons
-------------------------------------------------------

The Java String class has a method ``.compareToIgnoringCase(…)`` which is what it sounds like.

But you shouldn’t use it if you’re serious about handling text correctly. This is because strings are no longer ASCII, but Unicode. And Unicode is a beast of its own – it knows how to write hundreds of different alphabets with diacritics and other special characters. (Unicode even knows about bidirectional text (left-to-right vs right-to-left), but we won’t discuss that here).

Now, correct string sorting depends on your locale. E.g., in Swedish we put Å, Ä, Ö at the end of the alphabet, while Á, Ô, È are mixed together with A, O, E, respectively. Also, it’s common to mix V and W in Swedish dictionaries. German on the other hand mixes Ä, Ö with A, O. And it sorts ß together with S.

So, here’s how to define a correct comparator for Swedish, which ignores case differences and orders according to Swedish locale:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: BySwedishLocale

And in action:

.. codeinclude:: ChalmersGU/ComparatorDemo
   :tag: SortBySwedishLocale

Result:

   | Elfrida Andrée (1841)
   | Lili Boulanger (1893)
   | Nadia Boulanger (1887)
   | Diana Čemerytė (1974)
   | Unsuk Chin (1961)
   | Guy d’Hardelot (1857)
   | Andrea Tarrodi (1981)
   | Anna Thorvaldsdóttir (1977)

Finally Diana Čemerytė and Guy d’Hardelot find their right places in the list!

Running the program
----------------------------------

If you don't want to write this up as a Java program, you can find it here: ComparatorDemo.java . Just compile and run it without any arguments.

…and what about the names?
-------------------------------

The names are taken from here: https://female-composers.forts.se/ 

