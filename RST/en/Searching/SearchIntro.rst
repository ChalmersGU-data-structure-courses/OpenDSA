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
  the towns whose population is between 10,000 and 20,000.

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

Between X and Y: Ordered Sets and Maps
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. codeinclude:: ChalmersGU/API
   :tag: OrderedSetADT

.. codeinclude:: ChalmersGU/API
   :tag: OrderedMapADT

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
