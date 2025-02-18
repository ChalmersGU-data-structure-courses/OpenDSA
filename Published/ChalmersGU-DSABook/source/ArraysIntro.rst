.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _ArraysIntro:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ArraysIntro";ODSA.SETTINGS.MODULE_LONG_NAME = "Chapter Introduction: Arrays";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


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
   :topic: Arrays, Search, Sorting

Chapter Introduction: Arrays
============================

*Arrays* are one of the fundamental data structures in programming
\[[#python]_]. This is because they are natively supported by the
computer, and have good performance: reading or writing an element of
the array takes very little time. Many important algorithms use arrays.

In this chapter we will study two algorithmic problems and how to
solve them efficiently using arrays:

* *Searching*: Given a list of items, check if a given item is present
  in the list. There are two kinds of search problems:

  - *Membership testing:* 
    The search algorithm is given an item to search for, and should
    return *true* or *false* depending on whether the item is found.
    For example, a spellchecker: given a list of all valid English
    words, search the list for a given string.

  - *Lookup:* 
    The items are typically objects, and each object has a field
    called the *key*. The search algorithm is given a key, and should
    return the item having that key (or a reference to the item,
    such as the position in the list). For example, a
    database: given a list of people, find the person having a given
    personal identity number.

* *Sorting*: Given a list of items, put them in ascending order.
  Again, there are two kinds of sorting problems:

  - *Natural sorting:* Here, the items have some kind of natural
    order. For example, sorting a list of words in alphabetical order.

  - *Key-based sorting:* Here, each item has a *key*, and we want to
    sort the items so that the keys come in ascending order. For
    example, sorting a list of towns by population.

Note that if we search or sort according to a *key*, it doesn't have to be
explicitly stored in the object, but can instead be calculated on demand.
E.g., if we want to sort a list of words case-insensitively, we can
use a lower-case transformation when doing the comparisons.
This is usually done by a :ref:`comparator  <Comparison>`
(in Java), or by a `key function`_ (in Python).

.. _key function: https://docs.python.org/3/howto/sorting.html#key-functions

This chapter concentrates on *membership testing* and *natural
sorting*, but all the algorithms in this chapter work just as well for
*lookup* and *key-based sorting*.

.. rubric:: Footnotes

.. [#python] Note to Python programmers: in Python, arrays are called
   *lists*, and are written like this: ``[1,2,3]``. There is one
   difference between arrays and Python lists: in most programming
   languages, any given array has a fixed size. However, Python lists
   can change in size – for example, the ``append`` method adds a new
   element to the list, increasing its size. In this chapter, we will
   work with arrays that have a fixed size. 
   Python lists are so-called :ref:`dynamic arrays  <ListArrayDynamic>`.

