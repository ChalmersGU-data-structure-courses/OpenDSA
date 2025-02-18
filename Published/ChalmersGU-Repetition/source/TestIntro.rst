.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['testing-vs-debugging'];</script>

.. _TestIntro:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "TestIntro";ODSA.SETTINGS.MODULE_LONG_NAME = "Testing";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:44"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


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

Testing
=======

Testing vs. Debugging
---------------------

When we "write a program", we actually spend most of our time testing
and debugging.
These are two separate things.
:term:`Testing` refers to determining whether the program operates as
we intend.
:term:`Debugging` refers to correcting the program once we determine
that it is not operating as we intend.
So we can only debug to the extent that we have tested and determined
that there is a problem that needs to be corrected.
Debugging to fix a known problem can sometimes be extremely hard, but
is often somewhat mechanical.
Testing requires a lot of skill and empathy, in order to think of all
of the ways that a program might go wrong (in particular, all of the
input paths to the program that might affect its behavior).

To give you some idea for how hard it is, try doing the following
exercise.
Your goal is to come up with a series of "tests", in the form of
inputs to the program, that will result in executing the various paths
in the program (that is, generating "code coverage").
Here the inputs are meant to be triangle side lengths, and the program
is supposed to classify these sidelengths into a type of triangle.
You will probably find this quite challenging to come up with various
sets of inputs that will get you to even 50% code coverage.

.. avembed:: AV/Testing/Triangle/TriangleApplet.html pe
   :module: TestIntro
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=java
   :long_name: Triangle Testing No Code

