.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['bubble-sort', 'bubble-sort-analysis'];</script>

.. _BubbleSort:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "BubbleSort";ODSA.SETTINGS.MODULE_LONG_NAME = "Bubble Sort (optional)";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Sorting/BubbleSortAnalysisCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: sorting terminology; comparison; insertion sort
   :satisfies: bubble sort
   :topic: Sorting

.. index:: ! Bubble Sort

Bubble Sort (optional)
=========================

Bubble Sort
-----------

Our next sorting algorithm is called :term:`Bubble Sort`.
Bubble Sort is often taught to novice programmers in
introductory computer science courses.
This is unfortunate, because Bubble Sort has no redeeming features
whatsoever.
It is rather slow, even compared to the other :math:`\Theta(n^2)`
sorts that are commonly known.
It is not particularly intutitive --
nobody is going to come naturally to Bubble Sort as a way to sort
their Bridge hand or their pile of bills like they might with
:ref:`Insertion Sort  <InsertionSort>` or
:ref:`Selection Sort  <SelectionSort>`.
However, Bubble Sort can viewed as a close relative of
Selection Sort.

Like Insertion Sort, Bubble Sort consists of a simple double ``for``
loop.
The inner ``for`` loop moves through the record array from left to
right, comparing adjacent keys.
If a record's key value is greater than the key of its right
neighbor, then the two records are swapped.
Once the record with the largest key value is encountered, this
process will cause it to "bubble" up to the right of the array
(which is where Bubble Sort gets its name).
The second pass through the array repeats this process.
However, because we know that the record with the largest value
already reached the right of the array on the first pass, there is no
need to compare the rightmost two records on the second pass.
Likewise, each succeeding pass through the array compares adjacent
records, looking at one less record toward the end than did the
preceding pass.
Here is an implementation.

.. codeinclude:: ChalmersGU/Sorting/BubbleSort 
   :tag: BubbleSort

|

.. inlineav:: bubblesortS1CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Bubble Sort Slideshow 1
   :output: show

Now we continue with the second pass. However, since the largest
record has "bubbled" to the very right, we will not need to look at
it again.

.. inlineav:: bubblesortS2CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Bubble Sort Slideshow 2
   :output: show

Bubble Sort continues in this way until the entire array is sorted.

The following visualization shows the complete Bubble Sort.
You can input your own data if you like.

.. avembed:: AV/Sorting/bubblesortAV.html ss
   :module: BubbleSort
   :points: 0.0
   :required: False
   :threshold: 1
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Bubble Sort Visualization

Now try for yourself to see if you understand how Bubble Sort works.

.. avembed:: Exercises/Sorting/BubsortPRO.html ka
   :module: BubbleSort
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Bubble Sort Proficiency Exercise


Bubble Sort Analysis
--------------------

The following visualization illustrates the running time analysis of
Bubble Sort.

.. inlineav:: BubbleSortAnalysisCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Bubble Sort Analysis Slideshow
   :output: show

Thus, Bubble Sort's running time is roughly the same
in the best, average, and worst cases.

The number of swaps required depends on how often a
record's value is less than that of the record immediately preceding
it in the array.
We can expect this to occur for about half the comparisons in the
average case, leading to :math:`\Theta(n^2)` for the
expected number of swaps.
The actual number of swaps performed by Bubble Sort will be identical
to that performed by Insertion Sort.

Here are some review questions to check your understanding of
Bubble Sort. 
 
.. avembed:: Exercises/Sorting/BubsortSumm.html ka
   :module: BubbleSort
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Bubble Sort Summary Exercise

.. odsascript:: AV/Sorting/bubblesortS1CON.js
.. odsascript:: AV/Sorting/bubblesortS2CON.js
.. odsascript:: AV/Sorting/BubbleSortAnalysisCON.js
