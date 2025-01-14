.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['the-cost-of-exchange-sorting', 'analysis'];</script>

.. _ExchangeSort:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ExchangeSort";ODSA.SETTINGS.MODULE_LONG_NAME = "The Cost of Exchange Sorting (optional)";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Sorting/ExchangeSortCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: insertion sort; bubble sort; selection sort
   :satisfies: exchange sort
   :topic: Sorting

.. index:: ! exchange sorting
.. index:: sorting; exchange

The Cost of Exchange Sorting (optional)
=======================================

The Cost of Exchange Sorting
----------------------------

Here is a summary for the cost of Insertion Sort,
Bubble Sort, and Selection Sort in terms of their required number of
comparisons and swaps in the best, average, and worst cases.
The running time for each of these sorts is
:math:`\Theta(n^2)` in the average and worst cases.

.. math::

   \begin{array}{rccc}
   &\textbf{Insertion}&\textbf{Bubble}&\textbf{Selection}\\
   \textbf{Comparisons:}\\
   \textrm{Best Case}&\Theta(n)&\Theta(n^2)&\Theta(n^2)\\
   \textrm{Average Case}&\Theta(n^2)&\Theta(n^2)&\Theta(n^2)\\
   \textrm{Worst Case}&\Theta(n^2)&\Theta(n^2)&\Theta(n^2)\\
   \\
   \textbf{Swaps:}\\
   \textrm{Best Case}&0&0&\Theta(n)\\
   \textrm{Average Case}&\Theta(n^2)&\Theta(n^2)&\Theta(n)\\
   \textrm{Worst Case}&\Theta(n^2)&\Theta(n^2)&\Theta(n)\\
   \end{array}

The remaining sorting algorithms presented in this tutorial are
significantly better than these three under typical conditions.
But before continuing on, it is instructive to investigate what makes
these three sorts so slow.
The crucial bottleneck is that only *adjacent* records are compared.
Thus, comparisons and moves (for Insertion and Bubble Sort) are by
single steps.
Swapping adjacent records is called an :term:`exchange`.
Thus, these sorts are sometimes referred to as an
:term:`exchange sort`.
The cost of any exchange sort can be at best the total number of
steps that the records in the array must move to reach their
"correct" location.
Recall that this is at least the number of
inversions for the record, where an :index:`inversion` occurs when a
record with key value greater than the current record's key value
appears before it.

.. avembed:: Exercises/Sorting/FindInversionsPRO.html ka
   :module: ExchangeSort
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Inversions Proficiency Exercise


Analysis
--------

.. inlineav:: ExchangeSortCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Exchange Sort Analysis Slideshow
   :output: show

.. avembed:: Exercises/Sorting/ExchangeSumm.html ka
   :module: ExchangeSort
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Exchange Sorting Summary Exercise

.. odsascript:: AV/Sorting/ExchangeSortCON.js
