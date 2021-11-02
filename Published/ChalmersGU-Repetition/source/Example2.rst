.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _Example2:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "Example2";ODSA.SETTINGS.MODULE_LONG_NAME = "Another Example";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2021-11-02 12:04:12"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: Testing Introduction
   :satisfies:
   :topic: Testing

Another Example
===============

Another Example
---------------

Here is another example to let you practice testing.
This is testing a bowling game.
Each frame starts with 10 pins, the user enters the number of pins hit
by each roll of the ball.
Hitting all 10 points with one roll is a strike.
Knocking down all 10 points on two rolls is a spare.

Try to get at least 75% code coverage.

.. avembed:: AV/Testing/Bowling/BowlingApplet.html pe
   :module: Example2
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=java
   :long_name: Bowling Testing Without Code

