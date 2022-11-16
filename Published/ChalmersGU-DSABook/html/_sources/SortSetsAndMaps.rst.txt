.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _SortSetsAndMaps:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "SortSetsAndMaps";ODSA.SETTINGS.MODULE_LONG_NAME = "Arrays as Sets or Maps";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nick Smallbone
   :requires: exchange sort; shellsort; heapsort; mergesort; quicksort; radix sort; sorting lower bound
   :topic: Sorting

Arrays as Sets or Maps
======================

In Chapter :chap:`Introduction` we learnt about *sets* and *maps*, two of the
most important ADTs.

.. codeinclude:: ChalmersGU/API/API
   :tag: SetADT

.. codeinclude:: ChalmersGU/API/API
   :tag: MapADT

We can implement either of these ADTs using an array. For a set, we
can use an array of elements. For a map, we have two choices:

* In languages which support *tuples* as a data type (such as Python),
  we can have an array of *key-value* pairs.
* Alternatively, we can use two arrays. One array, ``keys``, holds the keys
  and the other array, ``values``, holds the corresponding values. The
  two arrays must be "kept in sync" so that ``values[i]`` holds the
  value associated with key ``keys[i]``.

We have one further choice: should the array be *sorted* or *unsorted*?

An unsorted array is usually not an appropriate choice, because the
``contains`` method must use *linear search*, which takes linear time.
Thus we cannot really look up items in the set or map efficiently.

A sorted array is a lot better. The ``contains`` method can use
*binary search*, which takes logarithmic time. Hence looking up items
is efficient. Unfortunately, modifying the data structure is slow.
If we want to ``add`` an item to a sorted array, we have to keep the
array sorted -- and that means we need to *insert* the new item at the
right place in the array, using the insertion algorithm from Insertion
Sort. This takes linear time in the worst case. Similarly, to
``remove`` an item without creating a hole in the array, we need to
move all the items that come after one space backwards. This also
takes linear time.

A sorted array is a suitable way to implement a set or a map that
*never changes*, that is where we never need to add or remove items
after the array is created. We start by sorting the array, using
either quicksort or mergesort, and then we can use binary search to
find items in it. Sorted arrays also support the *sorted set* and
*sorted map* operations such as *range queries* -- these can also be
implemented using binary search. As a reminder, here are the relevant
operations:

.. codeinclude:: ChalmersGU/API/API
   :tag: SortedSetADT

.. codeinclude:: ChalmersGU/API/API
   :tag: SortedMapADT

Sorted arrays can also be useful in cases where we always add *many*
items in one go. Given a sorted array :math:`A`, and an unsorted list
of items :math:`B`, we can add the items in :math:`B` to :math:`A` as
follows. First we sort :math:`B`, then we merge :math:`A` and
:math:`B`, using the merge algorithm from mergesort. Note that the
merge step takes linear time, and sorting :math:`B` takes a bit more
than linear time, so this is a lot faster than adding all the items
from :math:`B` one by one (which would take quadratic time).

An array is not a good way to implement a set or a map, if we need
both ``add``, ``remove`` and ``contains`` to be efficient. Later we
will learn about two data structures that are more suitable for
implementing sets and maps: binary search trees and hash tables.

