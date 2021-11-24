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

    mergeSort(inlist)
        if length of inlist <= 1:
            return inlist
        L1 = half of the items from inlist
        L2 = other half of the items from inlist
        return merge(mergeSort(L1), mergeSort(L2))

Here is a visualization that illustrates how Mergesort works.

.. avembed:: AV/Sorting/mergesortAV.html ss
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

    merge(L1, L2)
        answer = new empty list
        while L1 is not empty and L2 is not empty
            x = first element of L1
            y = first element of L2
            if x <= y
                append x to answer
                remove first element from L1
            else
                append y to answer
                remove first element from L2
        // Now one of L1 and L2 is empty, so append all remaining elements
        append all elements of L1 to answer
        append all elements of L2 to answer
        return answer

Here is a visualization for the merge operation.

.. inlineav:: mergesortCON ss
   :long_name: Merging Slideshow
   :scripts: AV/Sorting/mergesortCON.js
   :output: show

Here is a Mergesort warmup exercise to practice merging.

.. avembed:: Exercises/Sorting/MergesortMergePRO.html ka
   :long_name: Mergesort Merging Proficiency Exercise


Mergesort Practice Exercise
---------------------------

Now here is a full proficiency exercise to put it all together.

.. avembed:: AV/Sorting/mergesortPRO.html pe
   :long_name: Mergesort Proficiency Exercise

This visualization provides a running time analysis for Merge Sort.

.. inlineav:: MergeSortAnalysisCON ss
   :long_name: Mergesort Analysis Slideshow
   :links: AV/Sorting/MergeSortAnalysisCON.css
   :scripts: AV/Sorting/MergeSortAnalysisCON.js
   :output: show
