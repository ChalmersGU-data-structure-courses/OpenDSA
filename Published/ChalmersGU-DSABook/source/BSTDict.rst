.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _BSTDict:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "BSTDict";ODSA.SETTINGS.MODULE_LONG_NAME = "Dictionary Implementation Using a BST (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Search Trees"; ODSA.SETTINGS.BUILD_DATE = "2021-10-21 10:59:46"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: dictionary; BST; linked list; array-based list
   :satisfies: BST Dictionary
   :topic: Binary Trees

Dictionary Implementation Using a BST (WORK IN PROGRESS)
==========================================================

A simple implementation for the
:ref:`Dictionary  <Dictionary>` ADT can be
based on :term:`sorted <sorted list>` or
:term:`unsorted lists <unsorted list>`.
When implementing the dictionary with an unsorted list,
inserting a new record into the dictionary can be performed quickly by
putting it at the end of the list.
However, searching an unsorted list for a particular record
requires :math:`\Theta(n)` time in the average case.
For a large database, this is probably much too slow.
Alternatively, the records can be stored in a sorted list.
If the list is implemented using a
:ref:`linked list  <ListLinked>`, then no speedup to the
search operation will result from storing the records in sorted order.
On the other hand, if we use a sorted
:term:`array-based list  <array-based list>` to implement
the dictionary, then
:ref:`binary search  <AnalProgram>`
can be used to find a record in only :math:`\Theta(\log n)` time.
However, insertion will now require :math:`\Theta(n)` time on average
because, once the proper location for the new record in the sorted
list has been found, many records might be shifted to make room for
the new record.

Is there some way to organize a collection of records so
that inserting records and searching for records can both be done
quickly?
We can do this with a :term:`binary search tree` (:term:`BST`).
The advantage of using the BST is that all major operations (insert,
search, and remove) are :math:`\Theta(\log n)` in the average case.
Of course, if the tree is badly balanced, then the cost can be as bad
as :math:`\Theta(n)`.

Here is an implementation for the Dictionary interface, using a BST to
store the records.

.. raw:: html

   <a id="todo0"></a>

.. TODO::
   This code is seriously buggy! The BST stores (k,v) pairs,
   but find and remove searches for keys only!

.. codeinclude:: Binary/BSTDict

