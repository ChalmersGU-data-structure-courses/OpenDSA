.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [];</script>

.. _RecursiveDS:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "RecursiveDS";ODSA.SETTINGS.MODULE_LONG_NAME = "Binary Tree as a Recursive Data Structure";ODSA.SETTINGS.MODULE_CHAPTER = "Binary Trees"; ODSA.SETTINGS.BUILD_DATE = "2021-10-20 13:26:11"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Binary/RecursiveDSCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Sally Hamouda
   :satisfies: binary tree as a recursive data Structures
   :topic: Binary Tree as a Recursive Data Structures

Binary Tree as a Recursive Data Structure
=========================================

Binary Tree as a Recursive Data Structure
-----------------------------------------

A :term:`recursive data structure` is a data structure that is partially
composed of smaller or simpler instances of the same data structure.
For example, :term:`linked lists <linked list>` and
:term:`binary trees <binary tree>` can be viewed as recursive
data structures. 
A list is a recursive data structure because a list can be defined as
either (1) an empty list or (2) a node followed by a list.
A binary tree is typically defined as
(1) an empty tree or
(2) a node pointing to two binary trees, one its left child and the
other one its right child.

.. _ListRecDS:

.. inlineav:: ListRecDSCON dgm
   :align: justify


.. _BinRecDS:

.. inlineav:: BinRecDSCON dgm
   :align: justify
   
The recursive relationships used to define a structure provide a
natural model for any recursive algorithm on the structure.

.. inlineav:: SumBinaryTreeCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Sum values in a Binary Tree Slide Show
   :output: show

.. odsascript:: AV/Binary/ListRecDSCON.js
.. odsascript:: AV/Binary/BinRecDSCON.js
.. odsascript:: AV/Binary/SumBinaryTreeCON.js
