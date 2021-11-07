.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['trees-versus-tries'];</script>

.. _TreeTrie:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "TreeTrie";ODSA.SETTINGS.MODULE_LONG_NAME = "Trees versus Tries (optional) (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Priority Queues"; ODSA.SETTINGS.BUILD_DATE = "2021-11-07 21:13:21"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Development/TreeTrieCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer
   :requires: BST; Huffman coding tree
   :satisfies: 
   :topic: Trie

Trees versus Tries (optional) (WORK IN PROGRESS)
==================================================

Trees versus Tries
------------------

We see that all letters with codes beginning with
'0' are stored in the left branch, while all letters with codes
beginning with '1' are stored in the right branch.
Contrast this with storing records in a BST.
There, all records with key value less than the root value are stored
in the left branch, while all records with key values greater than the
root are stored in the right branch.

Recall that the Huffman coding tree stored in the left branch all
letters whose codes start with 0, and in the right branch all letters
whose codes start with 1.
We can use this same concept to store records in a search tree that is
slightly different from the behavior of a BST.
We can view all keys stored as appearing on a numberline.
The BST splits the numberline based on the positions of key values as
it receives them.
In contrast, we could split key values based on their binary
reprsentation similar to what the Huffman coding tree does.
The following slideshows present this in more detail.

.. inlineav:: TreeTimelineCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Tree timeline Slideshow
   :output: show

|

.. inlineav:: TrieTimelineCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Trie timeline Slideshow
   :output: show

.. odsascript:: AV/Development/TreeTimelineCON.js
.. odsascript:: AV/Development/TrieTimelineCON.js
