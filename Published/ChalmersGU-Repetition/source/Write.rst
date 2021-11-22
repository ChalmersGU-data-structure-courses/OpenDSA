.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _Write:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "Write";ODSA.SETTINGS.MODULE_LONG_NAME = "Writing a recursive function";ODSA.SETTINGS.MODULE_CHAPTER = "Recursion"; ODSA.SETTINGS.BUILD_DATE = "2021-11-22 18:01:33"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/RecurTutor/recurWriteCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Sally Hamouda and Cliff Shaffer
   :requires: recursion intro
   :satisfies: recursion writing
   :topic: Recursion

Writing a recursive function
============================

Writing a recursive function
----------------------------

Solving a "big" problem recursively means to solve one or more smaller
versions of the problem, and using those solutions of the smaller
problems to solve the "big" problem.
In particular, solving problems recursively means that
smaller versions of the problem are solved in a similar way.
For example, consider the problem of summing values of an array.
What's the difference between summing the first 50 elements in an
array versus summing the first 100 elements?
You would use the same technique.
You can even use the solution to the smaller problem to help you solve
the larger problem.

Here are the basic four steps that you need to write any recursive function.

.. inlineav:: recurWriteStepsCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Recursion Code Writing Slideshow 1
   :output: show


Now le't see some different ways that we could write ``Sum`` recursively.

.. inlineav:: recurWriteSumCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Recursion Code Writing Slideshow 2
   :output: show  


.. topic:: Example

   Our example for summing the first :math:`n` numbers of an array
   could have been written just as easily using a loop.
   Here is an example of a function that is more naturally written
   using recursion.

   The following code computes the Fibonacci sequence for a given number.
   The Fibonacci Sequence is the series of numbers: 1, 1, 2, 3, 5, 8,
   13, 21, 34, ...
   Any number in the sequence is found by adding up the two numbers
   before it.
   The base cases are that ``Fibonacci(0) = 1`` and
   ``Fibonacci(1) = 1``.
   
   .. codeinclude:: RecurTutor/Fibonacci

.. odsascript:: AV/RecurTutor/recurWriteStepsCON.js
.. odsascript:: AV/RecurTutor/recurWriteSumCON.js
