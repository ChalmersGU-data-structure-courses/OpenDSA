.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _SortingEmpirical:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "SortingEmpirical";ODSA.SETTINGS.MODULE_LONG_NAME = "An Empirical Comparison of Sorting Algorithms";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :topic: Sorting

.. index:: ! sorting; empirical comparison

An Empirical Comparison of Sorting Algorithms
=============================================

An Empirical Comparison of Sorting Algorithms
---------------------------------------------

Which sorting algorithm is fastest?  Asymptotic complexity analysis
lets us distinguish between :math:`\Theta(n^2)` and
:math:`\Theta(n \log n)` algorithms, but it does not help distinguish
between algorithms with the same asymptotic complexity.
Nor does asymptotic analysis say anything about which algorithm is
best for sorting small lists.
For answers to these questions, we can turn to empirical testing.

.. _SortCompTable:

.. topic:: Table

   Empirical comparison of sorting algorithms run on a 3.4 GHz Intel
   Pentium 4 CPU running Linux.
   All times shown are milliseconds.

   .. math::

      \begin{array}{l|rrrrrrrr}
      \hline
      \textbf{Sort} & \textbf{10}& \textbf{100} & \textbf{1K}&
      \textbf{10K} & \textbf{100K}& \textbf{1M}& \textbf{Up} & \textbf{Down}\\
      \hline
      \textrm{Insertion} & .00023 & .007 & 0.66 &  64.98 &  7381.0 &  674420 & 0.04 & 129.05\\
      \textrm{Bubble}    & .00035 & .020 & 2.25 & 277.94 & 27691.0 & 2820680 &  70.64 & 108.69\\
      \textrm{Selection} & .00039 & .012 & 0.69 &  72.47 &  7356.0 &  780000 &  69.76 &  69.58\\
      \textrm{Merge}     & .00050 & .010 & 0.12 &   1.61 &    19.3 &     219 &   0.83 &   0.79\\
      \textrm{Quick}     & .00048 & .008 & 0.11 &   1.37 &    15.7 &     162 &   0.37 &   0.40\\
      \textrm{Quick/O}   & .00031 & .006 & 0.09 &   1.14 &    13.6 &     143 &   0.32 &   0.36\\
      \hline
      \end{array}

Table :num:`#SortCompTable` shows timing results for
actual implementations of the sorting algorithms presented in this
chapter.
The algorithms compared include
:ref:`Insertion Sort  <InsertionSort>`,
:ref:`Bubble Sort  <BubbleSort>`,
:ref:`Selection Sort  <SelectionSort>`,
:ref:`Quicksort  <Quicksort>`, and
:ref:`Mergesort  <Mergesort>`.

For Quicksort, two versions are compared: the basic implementation
and an optimized version that does not partition sublists below length
nine (with Insertion Sort performed at the end).

Except for the rightmost columns,
the input to each algorithm is a random array of integers.
This affects the timing for some of the sorting algorithms.
For example, Selection Sort is not being used to best advantage
because the record size is small, so it does not get the best possible
showing.

The various sorting algorithms are shown for lists of sizes
10, 100, 1000, 10,000, 100,000, and 1,000,000.
The final two columns of each table show the performance for the
algorithms on inputs of size 10,000 where the numbers are in
ascending (sorted) and descending (reverse sorted) order,
respectively.
These columns demonstrate best-case performance for some
algorithms and worst-case performance for others.
They also show that for some algorithms, the order of input
has little effect.

These figures show a number of interesting results.
As expected, the :math:`O(n^2)` sorts are quite poor performers for
large arrays.
Insertion Sort is by far the best of this group, unless the array is
already reverse sorted.
Optimized Quicksort is clearly the best overall algorithm for all but
lists of 10 records.
Even for small arrays, optimized Quicksort performs well because
it does one partition step before calling Insertion Sort.
In general, optimizing the various algorithms makes a
noticeable improvement for larger array sizes.

Here are a few multiple choice questions that ask you to
compare the sorting algorithms that we learned about in this chapter.

.. avembed:: Exercises/Sorting/SortAlgCompSumm.html ka
   :module: SortingEmpirical
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Sort Comparison Summary Exercise

