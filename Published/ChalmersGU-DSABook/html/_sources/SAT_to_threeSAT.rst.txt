.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['reduction-of-sat-to-3-sat'];</script>

.. _SAT_to_threeSAT:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "SAT_to_threeSAT";ODSA.SETTINGS.MODULE_LONG_NAME = "Reduction of SAT to 3-SAT (optional) (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Limits to Computing (optional)"; ODSA.SETTINGS.BUILD_DATE = "2021-12-09 10:30:09"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/NP/SATto3SATCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nabanita Maji
   :topic: NP-completeness

Reduction of SAT to 3-SAT (optional) (WORK IN PROGRESS)
=========================================================

Reduction of SAT to 3-SAT
-------------------------

The following slideshow shows that an instance of Formula Satisfiability 
problem can be reduced to an instance of 3 CNF Satisfiability problem in 
polynomial time.
 
.. inlineav:: SATto3SATCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :output: show

This reduction can help in providing an NP Completeness proof for 3-SAT.

.. odsascript:: AV/NP/SATto3SATCON.js
