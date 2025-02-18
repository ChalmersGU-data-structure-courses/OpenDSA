.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['lower-bounds-for-sorting'];</script>

.. _SortingLowerBound:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "SortingLowerBound";ODSA.SETTINGS.MODULE_LONG_NAME = "Lower Bounds for Sorting (optional)";ODSA.SETTINGS.MODULE_CHAPTER = "Arrays: Searching and Sorting"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:48"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Sorting/SortingLowerBoundCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: analyzing problems; sorting terminology
   :satisfies: sorting lower bound
   :topic: Sorting

.. index:: ! sorting; lower bounds proof

Lower Bounds for Sorting (optional)
===================================

Lower Bounds for Sorting
------------------------

By now you have seen many analyses for algorithms.
These analyses generally define the upper and lower bounds for
algorithms in their worst and average cases.
For many of the algorithms presented so far, analysis has been easy.
This module considers a more difficult task: An analysis for
the cost of a *problem* as opposed to an *algorithm*.
The upper bound for a problem can be defined as the asymptotic cost of
the fastest known algorithm.
The lower bound defines the best possible cost for *any*
algorithm that solves the problem, including algorithms not yet
invented.
Once the upper and lower bounds for the problem meet, we know that no
future algorithm can possibly be (asymptotically) more efficient.

A simple estimate for a problem's lower bound can be obtained by
measuring the size of the input that must be read and the output
that must be written.
Certainly no algorithm can be more efficient than the problem's
I/O time.
From this we see that the sorting problem cannot be solved by
*any* algorithm in less than :math:`\Omega(n)` time because it
takes at least :math:`n` steps to read and write the :math:`n` values
to be sorted.
Alternatively, any sorting algorithm must at least look at every input
value to recognize whether the input values are in sorted order.
So, based on our current knowledge of sorting algorithms and the
size of the input, we know that the *problem* of sorting is
bounded by :math:`\Omega(n)` and :math:`O(n \log n)`.

Computer scientists have spent much time devising efficient
general-purpose sorting algorithms, but no one has ever found one
that is faster than :math:`O(n \log n)` in the worst or average
cases.
Should we keep searching for a faster sorting algorithm?
Or can we prove that there is no faster sorting algorithm by finding
a tighter lower bound?

This section presents one of the most important and most useful
proofs in computer science:
No sorting algorithm based on key comparisons can possibly be
faster than :math:`\Omega(n \log n)` in the worst case.
This proof is important for three reasons.
First, knowing that widely used sorting algorithms are asymptotically
optimal is reassuring.
In particular, it means that you need not bang your head against
the wall searching for an :math:`O(n)` sorting algorithm.
(Or at least not one that is in any way based on key comparisons.
But it is hard to imagine how to sort without any comparisons.)
Second, this proof is one of the few non-trivial lower-bounds proofs
that we have for any problem; that is, this proof provides one of the
relatively few instances where our lower bound is tighter than simply
measuring the size of the input and output.
As such, it provides a useful model for proving lower bounds on other
problems.
Finally, knowing a lower bound for sorting gives us a lower
bound in turn for other problems whose solution could be made to work
as the basis for a sorting algorithm.
The process of deriving asymptotic bounds for one problem from the
asymptotic bounds of another is called a
:term:`reduction  <reduction>`.

All of the sorting algorithms
we have studied make decisions based on the direct comparison of two
key values.
For example, Insertion Sort sequentially compares the value to be
inserted into the sorted list until a comparison against the next
value in the list fails.

The proof that any comparison sort requires :math:`\Omega(n \log n)`
comparisons in the worst case is structured as follows.
First, comparison-based decisions can be modeled as the
branches in a tree.
This means that any sorting algorithm based on comparisons between
records can be viewed as a binary tree whose nodes correspond to the
comparisons, and whose branches correspond to the possible outcomes.
Next, the minimum number of leaves in the resulting tree is
shown to be the factorial of :math:`n`.
Finally, the minimum depth of a tree with :math:`n!` leaves is shown
to be in :math:`\Omega(n \log n)`.

Before presenting the proof of an :math:`\Omega(n \log n)` lower bound
for sorting, we first must define the concept of a
:term:`decision tree`.
A decision tree is a binary tree that can model the processing for any
algorithm that makes binary decisions.
Each (binary) decision is represented by a branch in the tree.
For the purpose of modeling sorting algorithms, we count all
comparisons of key values as decisions.
If two keys are compared and the first is less than the second, then
this is modeled as a left branch in the decision tree.
In the case where the first value is greater than the second, the
algorithm takes the right branch.

Here is a Visualization that illustrates decision trees and the
sorting lower bound proof.

.. inlineav:: SortingLowerBoundCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Sorting Lower Bound Slideshow
   :output: show

Any sorting algorithm requiring :math:`\Omega(n \log n)` comparisons
in the worst case requires :math:`\Omega(n \log n)` running time in
the worst case.
Because any sorting algorithm requires :math:`\Omega(n \log n)` running
time,
the problem of sorting also requires :math:`\Omega(n \log n)` time.
We already know of sorting algorithms with :math:`O(n \log n)` running
time, so we can conclude that the problem of sorting requires
:math:`\Theta(n \log n)` time.
As a corollary, we know that no comparison-based sorting algorithm can
improve on existing :math:`\Theta(n \log n)` time sorting algorithms by
more than a constant factor.

Here are some review questions to check that you understand
this proof.

.. avembed:: Exercises/Sorting/SortBoundSumm.html ka
   :module: SortingLowerBound
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Lower Bounds Summary Exercise

.. odsascript:: AV/Sorting/SortingLowerBoundCON.js
