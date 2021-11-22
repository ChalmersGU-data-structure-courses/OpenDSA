.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['3-sat-to-hamiltonian-cycle'];</script>

.. _threeSAT_to_hamiltonianCycle:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "threeSAT_to_hamiltonianCycle";ODSA.SETTINGS.MODULE_LONG_NAME = "Reduction of 3-SAT to Hamiltonian Cycle (optional) (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Limits to Computing (optional)"; ODSA.SETTINGS.BUILD_DATE = "2021-11-22 18:08:35"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/NP/threeSATtoHCCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nabanita Maji
   :topic: NP-completeness

Reduction of 3-SAT to Hamiltonian Cycle (optional) (WORK IN PROGRESS)
=====================================================================

3-SAT to Hamiltonian Cycle
--------------------------

The following slideshow shows that an instance of 3-CNF Satisfiability 
problem can be reduced to an instance of Hamiltonian Cycle problem in 
polynomial time.
 
.. inlineav:: threeSATtoHCCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: 3-SAT to HC Reduction
   :output: show

This reduction can help in providing an NP Completeness proof for 
the Hamiltonian Cycle problem.

.. odsascript:: AV/NP/threeSATtoHCCON.js
