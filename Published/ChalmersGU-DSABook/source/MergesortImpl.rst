.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _MergesortImpl:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "MergesortImpl";ODSA.SETTINGS.MODULE_LONG_NAME = "Implementing Mergesort";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2021-11-06 19:40:18"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: mergesort
   :topic: Sorting

Implementing Mergesort
======================

Implementing Mergesort
----------------------

Implementing Mergesort presents a number of technical difficulties.
The first decision is how to represent the lists.
Mergesort lends itself well to sorting a singly linked list because
merging does not require random access to the list elements.
Thus, Mergesort is the method of choice when the input is in the form
of a linked list.
Implementing ``merge`` for linked lists is straightforward,
because we need only remove items from the front of the input lists
and append items to the output list.
Breaking the input list into two equal halves presents some
difficulty.
Ideally we would just break the lists into front and back halves.
However, even if we know the length of the list in advance, it would
still be necessary to traverse halfway down the linked list to reach
the beginning of the second half.
A simpler method, which does not rely on knowing the length of the
list in advance, assigns elements of the input list alternating
between the two sublists.
The first element is assigned to the first sublist, the
second element to the second sublist, the third to first sublist, the
fourth to the second sublist, and so on.
This requires one complete pass through the input list to build the
sublists.

When the input to Mergesort is an array, splitting input into two
subarrays is easy if we know the array bounds.
Merging is also easy if we merge the subarrays into a second array.
Note that this approach requires twice the amount of space as any of
the sorting methods presented so far, which is a serious disadvantage
for Mergesort.
It is possible to merge the subarrays without using a second array,
but this is extremely difficult to do efficiently and is
not really practical.
Merging the two subarrays into a second array, while
simple to implement, presents another difficulty.
The merge process ends with the sorted list in the auxiliary array.
Consider how the recursive nature of Mergesort breaks
the original array into subarrays.
Mergesort is recursively called until subarrays of size 1 have been
created, requiring :math:`\log n` levels of recursion.
These subarrays are merged into subarrays of size 2, which are in
turn merged into subarrays of size 4, and so on.
We need to avoid having each merge operation
require a new array.
With some difficulty, an algorithm can be
devised that alternates between two arrays.  A much simpler approach
is to copy the sorted sublists to the auxiliary array first, and then
merge them back to the original array.

Here is a complete implementation for mergesort following this
approach.
The input records are in array ``A``.
Array ``temp`` is used as a place to temporarily copy records during
the merge process.
Parameters ``left`` and ``right`` define the left and right
indices, respectively, for the subarray being sorted.
The initial call to ``mergesort`` would be
``mergesort(array, temparray, 0, n-1)``.

.. codeinclude:: Sorting/Mergesort
   :tag: Mergesort

Here is a visualization for the merge step.

.. inlineav:: mergeImplS1CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Mergesort Implementation Slideshow 1
   :output: show

.. avembed:: Exercises/Sorting/MergesortSumm.html ka
   :module: MergesortImpl
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Mergesort Summary Exercise

.. odsascript:: AV/Sorting/mergeImplS1CON.js
