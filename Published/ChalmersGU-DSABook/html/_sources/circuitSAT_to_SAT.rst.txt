.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['reduction-of-circuit-sat-to-sat'];</script>

.. _circuitSAT_to_SAT:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "circuitSAT_to_SAT";ODSA.SETTINGS.MODULE_LONG_NAME = "Reduction of Circuit SAT to SAT (optional) (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Limits to Computing (optional)"; ODSA.SETTINGS.BUILD_DATE = "2021-11-24 14:11:59"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/NP/circuitSATtoSATCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nabanita Maji
   :topic: NP-completeness

Reduction of Circuit SAT to SAT (optional) (WORK IN PROGRESS)
===============================================================

Reduction of Circuit SAT to SAT
-------------------------------

The following slideshow shows that an instance of Circuit Satisfiability 
problem can be reduced to an instance of SAT problem in polynomial time.
 
.. inlineav:: circuitSATtoSATCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Circuit SAT to SAT
   :output: show

This reduction can help in providing an NP Completeness proof for SAT.

.. odsascript:: AV/NP/circuit.js
.. odsascript:: AV/NP/circuitSATtoSATCON.js
