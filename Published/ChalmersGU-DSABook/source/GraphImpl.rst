.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['graph-api', 'adjacency-matrix', 'adjacency-list'];</script>

.. _GraphImpl:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "GraphImpl";ODSA.SETTINGS.MODULE_LONG_NAME = "Graph Implementations";ODSA.SETTINGS.MODULE_CHAPTER = "Graphs"; ODSA.SETTINGS.BUILD_DATE = "2021-12-09 10:30:09"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: graph terminology
   :satisfies: graph implementation
   :topic: Graphs

Graph Implementations
=====================

Graph API
---------

We next turn to the problem of implementing a general-purpose
:term:`graph` class.
There are two traditional approaches to representing graphs:
The :term:`adjacency matrix` and the :term:`adjacency list`.
In this module we will show actual implementations for each approach.
We will begin with an interface defining an ADT for graphs that a
given implementation must meet.

.. codeinclude:: ChalmersGU/API/API
   :tag: GraphADT

Note that this API is quite generic, and perhaps not suited for all
kinds of implementations. For example, the adjacency matrix implementation
works best if the vertices are integers in the range :math:`0\ldots |\mathbf{V}|-1`
where :math:`|\mathbf{V}|` is the number of vertices.

The interface ``Graph`` has methods to return the number of vertices and
edges (methods ``vertexCount`` and ``edgeCount``, respectively).
You can add vertices and edges by the methods ``addVertex`` and ``addEdge``.
Normally you don't have to add vertices explicitly, because ``addEdge`` should do
that for you if necessary.

Given an edge, we can use the attributes `start` and `end`
to know the adjacent vertices, and `weight` to know the weight.
Note that these attributes are **final**, which means that they cannot be changed
after initialisation.

Nearly every graph algorithm presented in this chapter will require
visits to all neighbors of a given vertex.
The ``outgoingEdges`` method returns a collection containing the
edges that originate in the given vertex. To get the neighbors
you can simply call ``e.end`` for each outgoing edge ``e``.
The following lines appear in many graph algorithms::

  for each edge e in G.outgoingEdges(v):
      w = e.end
      if w is not in visited:
          add w to visited
          ...do something with v, w, or e...

Here, ``visited`` is a set of vertices to keep track that we
don't visit a vertex twice.

It is reasonably straightforward to implement our graph ADT
using either the adjacency list or adjacency matrix.
The sample implementations presented here do not address the issue of
how the graph is actually created.
The user of these implementations must add functionality for
this purpose, perhaps reading the graph description from a file.
The graph can be built up by using the ``addEdge`` function
provided by the ADT.

Adjacency Matrix
-----------------

Here is an implementation for the adjacency matrix.

.. codeinclude:: ChalmersGU/API/MatrixGraph
   :tag: MatrixGraph

The edge matrix is implemented as an integer array of size
:math:`n \times n` for a graph of :math:`n` vertices.
Position :math:`(i, j)` in the matrix stores the weight for edge
:math:`(i, j)` if it exists.
A weight of zero for edge :math:`(i, j)` is used to indicate that no
edge connects Vertices :math:`i` and :math:`j`.

This means that this simple implementation of an adjacency matrix
does not work for all kinds of vertex types, but only for integer
vertices. In addition, the vertices must be numbered :math:`0\ldots |\mathbf{V}|-1`.
Therefore, the ``addVertex`` method is not used in this implementation,
and instead the user has to specify the number of vertices from the start.
The ``vertices`` method returns a collection of all vertices,
which in this case is just the numbers :math:`0\ldots |\mathbf{V}|-1`.

Given a vertex :math:`v`, the ``outgoingEdges`` method scans through row
``v`` of the matix to locate the positions of the various neighbors.
It creates an edge for each neighbour and adds it to a queue.
(There is no special reason why we use a queue, we could use a stack
or a list too).

Adjacency List
---------------

Here is an implementation of the adjacency list representation for graphs.
This implementation uses a generic type for vertices, so that you can
use strings or anything else.

Its main data structure is a map from vertices to sets of edges.
Exactly which kind of map or set we use can depend on our needs,
but in this implementation we use a :ref:`separate chaining hash map  <OpenHash>`,
backed with a set implemented as a :ref:`linked list <ListMap>`.

So, for each vertex, we store a linked list of all the edges originating
from that vertex.
This makes the method ``outgoingEdges`` very efficient, because the only
thing we have to do is to look up the given vertex in the internal map.
To make the methods ``vertexCount`` and ``vertices`` efficient,
we in addition store the vertices separately in the set ``verticesSet``.

The implementations of the API methods are quite straightforward,
as can be seen here:

.. codeinclude:: ChalmersGU/API/AdjacencyGraph
   :tag: AdjacencyGraph


