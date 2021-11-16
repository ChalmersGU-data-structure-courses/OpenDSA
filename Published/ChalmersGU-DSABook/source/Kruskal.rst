.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['kruskal-s-algorithm'];</script>

.. _Kruskal:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "Kruskal";ODSA.SETTINGS.MODULE_LONG_NAME = "Kruskal's Algorithm (WORK IN PROGRESS)";ODSA.SETTINGS.MODULE_CHAPTER = "Graphs"; ODSA.SETTINGS.BUILD_DATE = "2021-11-16 15:06:47"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Graph/kruskalCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: MCST; Union/Find
   :topic: Graphs


Kruskal's Algorithm (WORK IN PROGRESS)
========================================

Kruskal's Algorithm
-------------------

Our next MCST algorithm is commonly referred to as
:term:`Kruskal's algorithm`.
Kruskal's algorithm is also a simple, greedy algorithm.
First partition the set of vertices into :math:`|\mathbf{V}|`
:ref:`disjoint sets  <UnionFind>`,
each consisting of one vertex.
Then process the edges in order of weight.
An edge is added to the MCST, and two disjoint sets combined,
if the edge connects two vertices in different disjoint sets.
This process is repeated until only one disjoint set remains.

.. inlineav:: kruskalCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Kruskal Slideshow
   :output: show

The edges can be processed in order of weight by using a
min-heap.
This is generally faster than sorting the edges first, because in
practice we need only visit a small fraction of the edges before
completing the MCST.
This is an example of finding only a
:ref:`few smallest elements <HeapSort>` in a list.

The only tricky part to this algorithm is determining if two vertices
belong to the same equivalence class.
Fortunately, the ideal algorithm is available for the purpose --
the :ref:`UNION/FIND  <UnionFind>`.
Here is an implementation for Kruskal's algorithm.
Class ``KruskalElem`` is used to store the edges on the min-heap.

.. codeinclude:: Graphs/Kruskal
   :tag: Kruskal

Kruskal's algorithm is dominated by the time required to
process the edges.
The ``differ`` and ``UNION`` functions are nearly
constant in time if path compression and weighted union is used.
Thus, the total cost of the algorithm is
:math:`\Theta(|\mathbf{E}| \log |\mathbf{E}|)` in the worst case,
when nearly all edges must be processed before all the edges of the
spanning tree are found and the algorithm can stop.
More often the edges of the spanning tree are the shorter ones,and
only about :math:`|\mathbf{V}|` edges must be processed.
If so, the cost is often close to
:math:`\Theta(|\mathbf{V}| \log |\mathbf{E}|)` in the average case.

.. avembed:: AV/Graph/KruskalPE.html pe
   :module: Kruskal
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Kruskal's Algorithm Proficiency Exercise

.. raw:: html

   <a id="todo0"></a>

.. TODO::
  type: Exercise
    Summary battery of questions for Prim's and Kruskal's algorithms.

.. odsascript:: AV/Graph/kruskalCON.js
