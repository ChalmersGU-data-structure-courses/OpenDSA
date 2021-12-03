.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['spell-checking:-sets', 'database-lookup:-maps', 'search-engine:-multimaps', 'between-x-and-y:-sorted-sets-and-maps', 'how-to-implement-sets-and-maps'];</script>

.. _SetsAndMaps:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "SetsAndMaps";ODSA.SETTINGS.MODULE_LONG_NAME = "Information retrieval: Sets and Maps";ODSA.SETTINGS.MODULE_CHAPTER = "Introduction"; ODSA.SETTINGS.BUILD_DATE = "2021-12-03 17:29:11"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer, Nick Smallbone
   :requires:
   :satisfies:
   :topic: Design

Information retrieval: Sets and Maps
====================================

Many programming tasks involve *finding the right piece of
information* in a large dataset. That is, we have a collection of
items, and we want to quickly retrieve the items matching certain
criteria. Here are some examples of information retrieval problems:

* *Spell-checking:*
  Given a set containing all valid English words, check if a given
  string is present in the set (i.e. is a valid word).
* *Database lookup:*
  Given a list of people, find the person with a given *personnummer*.
* *Search engine:*
  Given a collection of documents (e.g. web pages), find all web
  pages containing a given word.
* *Between X and Y:*
  Given a list of all Swedish towns and their populations, find
  the towns whose population is between 1,000 and 2,000.

All of these problems can be solved using two ADTs, the *set* and the
*map*. Both ADTs can be used to maintain a collection of *records*.
They provide operations for finding records, adding records, and
removing records from the collection. In this section we will see what
sets and maps are, and how to use them to solve the four problems
above.

You may have already used sets and maps in programming, because almost
every programming language provides an implementation for them.
For example, Java provides the HashSet_ and HashMap_ classes,
and Python provides sets_ and dictionaries_ (another word for maps)
as part of its standard library.

Spell-checking: Sets
~~~~~~~~~~~~~~~~~~~~

A *set* represents a collection of items, where we can *add* and
*remove* items, and *check* if a given item is present in the set.
A set cannot contain duplicate items: if we try to add an item that is
already present, nothing happens, and the set is left unchanged.
Recall the interface for sets from :ref:`the course API <CourseAPI>`:

.. codeinclude:: ChalmersGU/API/API
   :tag: SetADT

We can use a set for the spell-checking example:

* Given a set containing all valid English words, check if a given
  string is present in the set (i.e. is a valid word).

To create the spell-checking dictionary, we start with an initially
empty set, and then call ``add`` repeatedly to add each valid word to
the set.  Then to spell-check a given word, we just call ``contains``.

.. codeinclude:: ChalmersGU/Other/SpellChecker
   :tag: SpellChecker

Database lookup: Maps
~~~~~~~~~~~~~~~~~~~~~

A *map* represents a set of *keys*, where each key has an associated
*value*. We can *add* and *remove* keys, but when we add a key we must
specify what *value* we want to associated with it. We can *check* if
a given key is present in the map. We can also *look up* a key to find
the associated value.

A map cannot contain duplicate *keys*, so each key is associated with
exactly one value. If we call ``put(k,v)``, but the key ``k`` is
already present, then the value associated with ``k`` gets changed to
``v``. On the other hand, a map *can* contain duplicate *values*: two
keys can have the same value.
Recall the interface for maps from :ref:`the course API <CourseAPI>`:

.. codeinclude:: ChalmersGU/API/API
   :tag: MapADT

The map is a perfect match for our database example:

* Given a list of people, find the person with a given *personnummer*.

Here, the key should be a personnummer, and the value should be a
record containing information about that person. If the personnummer
is stored in a field ``pnr``, then to put a person ``p`` in the database we call
``database.put(p.pnr, p)``.  To find the person with personnummer ``pnr`` we
call ``database.get(pnr)``.

.. codeinclude:: ChalmersGU/Other/PersonDatabase
   :tag: Database

Search engine: Multimaps
~~~~~~~~~~~~~~~~~~~~~~~~

Maps have the restriction that each key has only one value. However,
sometimes we want to store a list of records, where some records might
have the same key. Then we want something like a map, but where a key
can have multiple values associated with it. This structure is called
a *multimap*.

Unfortunately, most programming languages do not provide a multimap
data structure. Instead, we can implement it ourselves. The idea is to
use a map, where the key is a word, and the value is not a document
but a *set* of documents.

A multimap is the perfect data structure for our search engine
example:

* Given a collection of documents (e.g. web pages), find all web
  pages containing a given word.

To find all documents containing a given word, we will build a
multimap, where the key is a word, and the values are all documents
containing that word. Then, searching for a word will just mean
looking it up in the multimap.

.. codeinclude:: ChalmersGU/Other/SearchEngine
   :tag: SearchEngine

Between X and Y: Sorted Sets and Maps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Consider the final example problem:

* Given a list of all Swedish towns and their populations, find
  the towns whose population is between 1,000 and 2,000.

One way to solve this problem would be to use a multimap. The key
would be a population number, and the values would be all towns having
that population. Then we could find the required towns by making a
sequence of calls to ``contains``:

* ``contains(1000)`` - find all towns with 1,000 population
* ``contains(1001)`` - find all towns with 1,001 population
* ``contains(1002)`` - find all towns with 1,002 population
* etc.

But this is not a sensible approach. We would need to make ~1,000
calls to ``contains``, and if we wanted to instead find all cities in
the USA having a population of between 1 and 2 million, we would need
to make ~1,000,000 calls.

There is a better way. If the towns are stored in an array, and sorted
by population, we can use the following algorithm:

* Find the position in the array of the *first* town that has a
  population of *at least* 1,000. (We will see in the section about
  :ref:`binary search <BinarySearch>`
  that it is possible to find this position efficiently.)
* Find the position in the array of the *last* town that has a
  population of *at most* 2,000.
* Now return all towns between those two positions in the array.

This is an example of a *range query*: given a map, finding all items
whose key lies in a given range. Some map implementations support
answering range queries efficiently; we say that these data structures
implement *sorted maps*.

Apart from range queries, sorted maps support several other operations
that take advantage of the natural order of the keys:

* Finding the *smallest* or *largest* key in the map.
* Finding the *closest* key to a given one. Given a key :math:`k`
  (which may or may not be in the map), then:

  - The *successor* of :math:`k` is the next key after :math:`k` in
    the map, i.e. the smallest key :math:`k\prime` such that
    :math:`k < k\prime`.

  - The *predecessor* of :math:`k` is the previous key before
    :math:`k` in the map, i.e. the greatest key :math:`k\prime` such
    that :math:`k\prime < k`.

  A variant which is sometimes useful is *floor* and *ceiling*:

  - The *floor* of :math:`k` is the greatest key :math:`k\prime`
    such that :math:`k\prime \leq k`. If :math:`k` is in the map,
    then the floor of :math:`k` is just :math:`k`; otherwise it is the
    predecessor of :math:`k`.

  - The *ceiling* of :math:`k` is the least key :math:`k\prime`
    such that :math:`k \leq k\prime`. If :math:`k` is in the map,
    then the ceiling of :math:`k` is just :math:`k`; otherwise it is the
    successor of :math:`k`.

Recall the interface for sorted maps from :ref:`the course API <CourseAPI>`:

.. codeinclude:: ChalmersGU/API/API
   :tag: SortedMapADT

As well as a sorted map, it is also possible to have a *sorted set*.
Recall the interface for sorted sets from :ref:`the course API <CourseAPI>`:

.. codeinclude:: ChalmersGU/API/API
   :tag: SortedSetADT

Here is how to use a sorted map ADT to find all Swedish towns having
between 1,000 and 2,000 population. As there may be towns that have
the same population, we need a *multimap*. As before, we solve this by
having the key be a population number and the value be a set of towns.

.. codeinclude:: ChalmersGU/Other/CityPopulations
   :tag: CityPopulations

How to implement sets and maps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Sets and maps are useful in a huge variety of computer programs, and
are perhaps the most useful of all data structures. But how can we
design a class that implements a set or a map, in such a way that
adding, removing and searching can be done efficiently? In this book
we will see several different ways of implementing sets and maps.

In Chapter :chap:`Arrays: Searching and Sorting`, we will see how to
implement a set using an array. By sorting the items in the array, it
is possible to look up information efficiently. However, it turns out
that adding and removing items is quite expensive. An array is a
suitable way of storing a set or a map if its contents never changes.

In Chapter :chap:`Search Trees`,
we learn about *balanced binary search trees (BSTs)*,
a data structure for sets and maps where adding, removing and
searching are all efficient. BSTs also support the *sorted map*
operations that we used in our final example.

In Chapter :chap:`Hash Tables`, we learn about *hash tables*, another way
to implement the set and map ADTs. In a hash table, ``add``,
``remove`` and ``contains`` are even faster than in a BST, but hash
tables are somewhat harder to use than BSTs, and do not support the
*sorted map* operations. 

Balanced BSTs and hash tables are the main ways that sets and maps are
implemented in practice. Almost every programming language provides sets
and maps as a built-in feature, based on one of these technologies.
For example, Java's HashSet_, HashMap_, TreeSet_ and TreeMap_, and
Python's: sets_ and dictionaries_. By the end of this book you will
understand how all of these work.

.. _HashSet: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/HashSet.html
.. _HashMap: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/HashMap.html
.. _TreeSet: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/TreeSet.html
.. _TreeMap: https://docs.oracle.com/javase/8/docs/api/index.html?java/util/TreeMap.html
.. _sets: https://docs.python.org/3/tutorial/datastructures.html#sets
.. _dictionaries: https://docs.python.org/3/tutorial/datastructures.html#dictionaries

