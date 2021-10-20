.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer, Nick Smallbone
   :requires:
   :satisfies:
   :topic: Search

Searching
=========

Many tasks that we want to solve using a computer involve *searching*:
We have some set of items, and we want to find the items that match
some criteria. Here are some examples:

* *Spell-checking:*
  Given a set containing all valid English words, check if a given
  string is present in the set (i.e. is a valid word).
* *Database lookup:*
  Given a list of people, find the person with a given personnummer.
* *Search engine:*
  Given a collection of documents (e.g. web pages), find all web
  pages containing a given word.
* *Between X and Y:*
  Given a list of all Swedish towns and their populations, find
  the towns whose population is between 1,000 and 2,000.

All of these problems can be solved using ADTs called *sets* and *maps*.

Spell-checking: Sets
~~~~~~~~~~~~~~~~~~~~

A *set* represents a collection of items, where we can *add* and
*remove* items, and *check* if a given item is present in the set.
A set cannot contain duplicate items: if we try to add an item that is
already present, nothing happens, and the set is left unchanged.

.. codeinclude:: ChalmersGU/API
   :tag: SetADT

We can use a set for the spell-checking example. To create the
spell-checking dictionary, we start with an initially empty set, and
then call ``add`` repeatedly to add each valid word to the set.
Then to spell-check a given word, we just call ``contains``.

.. codeinclude:: Searching/SpellCheck
   :tag: SpellCheck

Database lookup: Maps
~~~~~~~~~~~~~~~~~~~~~

A *map* represents a set of *keys*, where each key has an associated
*value*. We can *add* and *remove* keys, but when we add a key we must
specify what *value* we want to associated with it. We can *check* if
a given key is present in the map. We can also *look up* a key to find
the associated value.

.. codeinclude:: ChalmersGU/API
   :tag: MapADT

A map cannot contain duplicate *keys*, so each key is associated with
exactly one value. If we call ``add(k,v)``, but the key ``k`` is
already present, then the value associated with ``k`` gets changed to
``v``. On the other hand, a map *can* contain duplicate *values*: two
keys can have the same value.

The map is a perfect match for our database example. Here, the key
should be a personnummer, and the value should be a record containing
information about that person. If the personnummer is stored in a
field ``pnr``, then to add a person ``p`` we call ``add(p.pnr, p)``.
To find the person with personnummer ``pnr`` we call ``lookup(pnr)``.

.. codeinclude:: Searching/Database
   :tag: Database

Search engine: Multimaps
~~~~~~~~~~~~~~~~~~~~~~~~

Maps have the restriction that each key has only one value. However,
sometimes we want to store a list of records, where some records might
have the same key. Then we want something like a map, but where a key
can have multiple values associated with it. This structure is called
a *multimap*.

A multimap is the perfect data structure for our search engine
example. We want to find all documents containing a given word. To do
that, we will build a multimap, where the key is a word, and the
values are all documents containing that word. Then, searching for a
word will just mean looking it up in the multimap.

Unfortunately, most programming languages do not provide a multimap
data structure. Instead, we can implement it ourselves. The idea is to
use a map, where the key is a word, and the value is not a document
but a *set* of documents.

.. codeinclude:: Searching/SearchEngine
   :tag: SearchEngine

Between X and Y: Sorted Sets and Maps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Consider our problem: Given a list of all Swedish towns and their
populations, find the towns whose population is between 1,000 and 2,000.
One way to solve this problem would be to use a multimap. The key
would be a population number, and the values would be all towns having
that population. Then we could find the required towns by making a
sequence of calls to ``contains``:

* ``contains(1000)``` - find all towns with 1,000 population
* ``contains(1001)``` - find all towns with 1,001 population
* ``contains(1002)``` - find all towns with 1,002 population
* etc.

But this is not a sensible approach. We would need to make ~1,000
calls to ``contains``, and if we wanted to instead find all cities in
the USA having a population of between 1 and 2 million, we would need
to make ~1,000,000 calls.

There is a better way. If the towns are stored in a array, and sorted
by population, we can use the following algorithm:

* Use a binary search to find the first town with a population of at
  least 1,000, and remember what position it has in the array.
* Use another binary search to find the *last* town with a population
  of *at most* 2,000, and remember its position.
* Now return all towns between those two positions in the array.

The cost of finding the towns using this algorithm is only
:math:`O(\log n)` (two calls to binary search).

This is an example of a *range query*: given a map, finding all items
whose key lies in a given range. Some map implementations (such as
sorted arrays) support answering range queries efficiently; we say
that these data structures implement *sorted maps*.

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

.. codeinclude:: ChalmersGU/API
   :tag: SortedMapADT

As well as a sorted map, it is also possible to have a *sorted set*:

.. codeinclude:: ChalmersGU/API
   :tag: SortedSetADT

Here is how to use a sorted map ADT to find all Swedish towns having
between 1,000 and 2,000 population. As there may be towns that have
the same population, we need a *multimap*. As before, we solve this by
having the key be a population number and the value be a list of towns.

.. codeinclude:: Searching/Between
   :tag: Between

How to implement sets and maps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

In Chapter :chap:`Arrays: Sorting and Searching`, we saw two ways to
implement a set: using an array, or using a *sorted* array. We could
implement a map using either an array of (key, value) tuples, or by
storing the keys in one array and the values in another.

An unsorted array is not a good implementation of a set (or a map),
because the ``contains`` method must use *linear search*, which takes
:math:`O(n)` time.

A sorted array is suitable for a set or a map that *never changes*,
because the ``contains`` method can use *binary search*, which takes
:math:`O(\log n)` time. Updating the set or map is slow, because
``add`` and ``remove`` must keep the array in the correct order, which
takes :math:`O(n)` time. However, if the set or map never changes, we
can sort it once at the beginning (in :math:`O(n \log n)` time) and
use binary search from then on.

In this chapter and the next one, we learn about *balanced binary
search trees (BSTs)*, a data structure that implements the set and map
ADTs, where ``add``, ``remove`` and ``contains`` all take
:math:`O(\log n)` time. Balanced BSTs also support ordered operations
such as :ref:`range queries <range query> <range query>`.

In chapter :chap:`Indexing`, we learn about *hash tables*, another way
to implement the set and map ADTs. In a hash table, ``add``,
``remove`` and ``contains`` take *constant* time on average,
but they are a little harder to use than BSTs, the performance
guarantees are not as strong, and they do not support ordered operations.
Balanced BSTs and hash tables are the main ways that sets and maps are
implemented in practice.
