.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['getting-started', 'design-considerations', 'meaningful-tests', 'more-information'];</script>

.. _junitstart:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "junitstart";ODSA.SETTINGS.MODULE_LONG_NAME = "JUnit Testing And You";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2021-11-24 14:11:56"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Jordan Sablan
   :requires:
   :satisfies: JUnit intro
   :topic: JUnit

JUnit Testing And You
=====================

Getting Started
---------------

*To err is human, to correct is divine.*

When writing code we often make mistakes.
So it is important that we test our code frequently in order
to spot, fix and remove bugs.
Thankfully Java comes with a wonderful suite of tools to help test
our code.
In addition to this, various 3rd parties develop and release extensions on
the standard JUnit suite and release them for students to make use of.
One such example is |external_link1|.

.. |external_link1| raw:: html

   <a href="http://web-cat.org/eclstats/junit-quickstart/" target = "_blank">here</a>


Design Considerations
~~~~~~~~~~~~~~~~~~~~~

There are a few different approaches to testing code.
One is to focus on code coverage.
The code coverage approach to testing is fairly easy to understand.
If our test cases hit every line of code and
produce the expected results then we have a good indication that our code is
relatively bug free.
For this design we should try to keep our test cases as modular as
possible.
As such it is generally recommended that for every method in your
class you design a test method corresponding to it.

Consider a ``Human`` class with the methods
``setName, getName, setWeight, getWeight, walk, jump``.
There are two sets of getters and setters and two independent methods.
Thus it would be wise to create a test method called ``testName()`` which tests the
getter/setter for name, and a test method called ``testWeight()`` which tests the
getter/setter for weight.
Things a coder might wish to consider when testing these methods could
include what happens when you cast a data type to fit inside a setter
(i.e. if your weight is represented as integers, will the system
round as expected or should you regear your data choice).
Additionally, be sure to consider any issues that arise from abusing
the  setter
(i.e. negative weight, or giving NULL for the name).
As you test your code, try to consider any way in which you can break
your program.
Finally, we have two methods left untested: The ``walk`` and ``jump`` methods.
In order to keep them modular, simply create two separate test methods:
``testJump`` and ``testWalk``. 

Meaningful Tests
~~~~~~~~~~~~~~~~

When writing your test cases it is important not to just run the code with
random tests. All the tests should be designed to test common runtime conditions,
as well as possible edge conditions that may change the behavior. Additionally
try to develop test cases that are independent of each other. If a segment of
code has already been properly tested, testing the same chunks of code over
again will not add anything and will slow down the overall runtime of tests.
Remember quality over quantity!

More Information
~~~~~~~~~~~~~~~~

For more information |external_link2|
is an excellent resource to read and learn from.

.. |external_link2| raw:: html

   <a href="http://openmymind.net/2011/2/23/Foundations-of-Programming-2-Chapter-5-Effective-T/" target = "_blank">this blog</a>

