.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: MCST; Union/Find
   :topic: Graphs


Kruskal's Algorithm
===================

Kruskal's Algorithm
-------------------

Our next MCST algorithm is commonly referred to as
:term:`Kruskal's algorithm`.
Kruskal's algorithm is also a simple, greedy algorithm.
First partition the set of vertices into :math:`|\mathbf{V}|`
:ref:`disjoint sets <UNION/FIND> <UnionFind>`,
each consisting of one vertex.
Then process the edges in order of weight.
An edge is added to the MCST, and two disjoint sets combined,
if the edge connects two vertices in different disjoint sets.
This process is repeated until only one disjoint set remains.

.. inlineav:: kruskalCON ss
   :long_name: Kruskal Slideshow
   :links: AV/Graph/kruskalCON.css
   :scripts: AV/Graph/kruskalCON.js
   :output: show

The edges can be processed in order of weight by
putting them in an array and then sorting the array.
Another possibility is to use a
:ref:`minimum priority queue <priority queue> <Heaps>`,
similar to what we did in
:ref:`Prim's algorithm <Prim's algorithm> <MCST>`.

The only tricky part to this algorithm is determining if two vertices
belong to the same equivalence class.
Fortunately, the ideal algorithm is available for the purpose --
the :ref:`UNION/FIND <UNION/FIND> <UnionFind>`.
Here is an implementation for Kruskal's algorithm.
Note that since the MCST will never have more than :math:`|\mathbf{V}|-1`
edges, we can return as soon as the MCST contains enough edges.

.. codeinclude:: ChalmersGU/Graphs/Kruskal
   :tag: Kruskal

Kruskal's algorithm is dominated by the time required to
process the edges.
The ``FIND`` and ``UNION`` functions are nearly
constant in time if path compression and weighted union is used.
Thus, the total cost of the algorithm is
:math:`\Theta(|\mathbf{E}| \log |\mathbf{E}|)` in the worst case,
when nearly all edges must be processed before all the edges of the
spanning tree are found and the algorithm can stop.
More often the edges of the spanning tree are the shorter ones, and
only about :math:`|\mathbf{V}|` edges must be processed.
If so, the cost is often close to
:math:`\Theta(|\mathbf{V}| \log |\mathbf{E}|)` in the average case
(provided we use a priority queue instead of sorting all edges in advance).

.. avembed:: AV/Graph/KruskalPE.html pe
   :long_name: Kruskal's Algorithm Proficiency Exercise
