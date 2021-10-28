.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['circuit-satisfiability'];</script>

.. _circuitSAT:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "circuitSAT";ODSA.SETTINGS.MODULE_LONG_NAME = "Circuit Satisfiability (optional) (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Limits to Computing (optional)"; ODSA.SETTINGS.BUILD_DATE = "2021-10-28 16:21:59"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/NP/circuitSATCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nabanita Maji
   :topic: NP-completeness

Circuit Satisfiability (optional) (WORK IN PROGRESS)
=======================================================

Circuit Satisfiability
----------------------

.. inlineav:: circuitSATCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Circuit Satisfiability
   :output: show

In the above problem, given a particular assignment, while we can 
quickly check whether the assignment satisfies the circuit or not,
we have no easy way of knowing whether it has any satisfying 
assignment.

.. odsascript:: AV/NP/circuit.js
.. odsascript:: AV/NP/circuitSATCON.js
