.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['mergesort-practice-exercise'];</script>

.. _Mergesort:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "Mergesort";ODSA.SETTINGS.MODULE_LONG_NAME = "Mergesort Concepts";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2021-11-03 11:24:08"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Sorting/MergeSortAnalysisCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: comparison; sorting terminology
   :satisfies: mergesort
   :topic: Sorting

.. index:: ! Mergesort

Mergesort Concepts
==================

Mergesort Concepts
------------------

A natural approach to problem solving is divide and conquer.
To use divide and conquer when sorting, we might consider breaking the
list to be sorted into pieces, process the pieces, and then put them
back together somehow.
A simple way to do this would be to split the list in half, sort
the halves, and then merge the sorted halves together.
This is the idea behind :term:`Mergesort`.

Mergesort is one of the simplest sorting algorithms conceptually,
and has good performance both in the asymptotic 
sense and in empirical running time.
Unfortunately, even though it is based on a simple concept,
it is relatively difficult to implement in practice.
Here is a pseudocode sketch of Mergesort::

    List mergesort(List inlist)
      if length of inlist <= 1 then return inlist
      List L1 = half of the items from inlist
      List L2 = other half of the items from inlist
      return merge(mergesort(L1), mergesort(L2))

Here is a visualization that illustrates how Mergesort works.

.. avembed:: AV/Sorting/mergesortAV.html ss
   :module: Mergesort
   :points: 0.0
   :required: False
   :threshold: 1
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Mergesort Visualization

The hardest step to understand about Mergesort is the merge function.
The merge function starts by examining the first record of each
sublist and picks the smaller value as the smallest record overall.
This smaller value is removed from its sublist and placed into the
output list.
Merging continues in this way, comparing the front
records of the sublists and continually appending the smaller to the
output list until no more input records remain.

Here is pseudocode for merge on lists::

    List merge(List L1, List L2)
      List answer = new empty list
      while L1 is not empty and L2 is not empty
        x = first element of L1
        y = first element of L2
        if x <= y
          append x to answer
          remove first element from L1
        else
          append y to answer
          remove first element from L2
      // Now one of L1 and L2 is empty, so append
      // all remaining elements
      append all elements of L1 to answer
      append all elements of L2 to answer
      return answer

Here is a visualization for the merge operation.

.. inlineav:: mergesortCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Merging Slideshow
   :output: show

Here is a mergesort warmup exercise to practice merging.

.. avembed:: Exercises/Sorting/MergesortMergePRO.html ka
   :module: Mergesort
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Mergesort Merging Proficiency Exercise


Mergesort Practice Exercise
---------------------------

Now here is a full proficiency exercise to put it all together.

.. avembed:: AV/Sorting/mergesortPRO.html pe
   :module: Mergesort
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo&amp;JXOP-feedback=continuous&amp;JXOP-fixmode=fix
   :long_name: Mergesort Proficiency Exercise

This visualization provides a running time analysis for Merge Sort.

.. inlineav:: MergeSortAnalysisCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Mergesort Analysis Slideshow
   :output: show

.. odsascript:: AV/Sorting/mergesortCON.js
.. odsascript:: AV/Sorting/MergeSortAnalysisCON.js
