.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _RecurrenceIntro:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "RecurrenceIntro";ODSA.SETTINGS.MODULE_LONG_NAME = "Recurrence Relations";ODSA.SETTINGS.MODULE_CHAPTER = "Mathematical Background"; ODSA.SETTINGS.BUILD_DATE = "2022-01-14 22:50:52"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Background/LinearRecurrencesCON.css

.. odsalink:: AV/Background/LinearRecurrencesNCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: summation
   :satisfies: recurrence
   :topic: Math Background

Recurrence Relations
====================

Recurrence Relations
--------------------

The running time for a recursive algorithm is most easily expressed by
a recursive expression because the total time for the recursive
algorithm includes the time to run the recursive
call(s).
A :term:`recurrence relation` defines a function by means of an
expression that includes one or more (smaller) instances of itself.
A classic example is the recursive definition for the
factorial function:

.. math::

   n! = (n-1)! \cdot n\ \mbox{for}\ n>1; \quad 1! = 0! = 1.

Another standard example of a recurrence is the Fibonacci
sequence:

   .. math::

      \mbox{Fib}(n) = \mbox{Fib}(n-1) + \mbox{Fib}(n-2)\ \mbox{for}\ n>2;
      \quad\mbox{Fib}(1) = \mbox{Fib}(2) = 1.

From this definition, the first seven numbers of the
Fibonacci sequence are

.. math::

   1, 1, 2, 3, 5, 8,\ \mbox{and}\ 13.

Notice that this definition contains two parts: the general
definition for :math:`\mbox{Fib}(n)` and the base cases for
:math:`\mbox{Fib}(1)` and :math:`\mbox{Fib}(2)`.
Likewise, the definition for factorial contains a recursive part and
base cases.

Recurrence relations are often used to model the cost of recursive
functions.
For example, the number of multiplications required by a recursive
version of the factorial function for an input of size
:math:`n` will be zero when :math:`n = 0` or :math:`n = 1` (the base
cases), and it will be one plus the cost of calling ``fact`` on a
value of :math:`n-1`.
This can be defined using the following recurrence:

.. math::

   \mathbf{T}(n) = \mathbf{T}(n-1) + 1\ \mbox{for}\ n>1;
   \quad \mathbf{T}(0) = \mathbf{T}(1) = 0.

As with summations, we typically wish to replace the recurrence
relation with a closed-form solution.
One approach is to expand the recurrence by replacing any
occurrences of :math:`\mathbf{T}` on the right-hand side with its
definition.

.. inlineav:: LinearRecurrencesCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linear Recurrences Slideshow
   :output: show


A slightly more complicated recurrence is

.. math::

   \mathbf{T}(n) = \mathbf{T}(n-1) + n; \quad \mathbf{T}(1) = 1.

Again, we will use expansion to help us find a closed form solution.

.. inlineav:: LinearRecurrencesNCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linear Recurrences Slideshow (n)
   :output: show

.. odsascript:: AV/Background/LinearRecurrencesCON.js
.. odsascript:: AV/Background/LinearRecurrencesNCON.js
