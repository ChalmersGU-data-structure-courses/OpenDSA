.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _TestCoverage:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "TestCoverage";ODSA.SETTINGS.MODULE_LONG_NAME = "Testing for Code Coverage";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:44"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires:
   :satisfies: Testing Introduction
   :topic: Testing

Testing for Code Coverage
=========================

Testing for Code Coverage
-------------------------

For the exercise in the last section, you probably found it to be
fairly difficult to come up with a series of test inputs that gave
good code coverage.
This is because you could not see the code.
If you can see the code, it is relatively easy to come up with test
cases to cover each branch.
Try it this time with code visible.
Try to reach 100% code coverage by giving inputs that take you to each
branch in the nested series of ``if`` statements.

.. avembed:: AV/Testing/Triangle/TriangleApplet.html pe
   :module: TestCoverage
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=java&amp;code=true
   :long_name: Triangle Testing With Code

So, did you find it easier to get code coverage when you had the code
in front of you?
Probably, because you could see what paths are in the code, and "write
a test case" for each branch.
However, while this can be a useful skill to be able to write tests to
cover each branch in the code, this is not really the same as
determining if your program "works correctly".
That is, tests to cover all of the code **might** be able to tell you
if the code that you wrote is doing what you intended.
But that does not tell you if the code is implementing the full
functionality of the problem as intended.

The code in this example is reasonably good at checking for various
bad user inputs, like typing something into a box that is not a number
at all.
But what if the code had not tested for bad user input?
What would have happened if the user had indeed typed "aa" into a box
instead of a number, but the programmer never thought to check and
properly handle that?
Probably the program would have behaved in a way not intended.
If the programmer never thought to check for this in the program,
then they probably never thought to check for it when they wrote their
tests for the program either.
This is part of what makes testing so difficult.
You need to get good at predicting and testing for all possible types
of input to the program, not just the ones that the user will provide
when they "use the program correctly".
Only testing for "proper" inputs or uses of the program is called
:term:`happy path testing`.
But happy path testing alone is not good enough to write reliable
programs.

