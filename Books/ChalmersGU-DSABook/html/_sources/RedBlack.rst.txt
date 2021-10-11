.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['exercise-1', 'exercise-2'];</script>

.. _RedBlack:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "RedBlack";ODSA.SETTINGS.MODULE_LONG_NAME = "The Red-Black Tree";ODSA.SETTINGS.MODULE_CHAPTER = "Balanced Binary Trees"; ODSA.SETTINGS.BUILD_DATE = "2021-10-11 15:14:50"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: BST
   :satisfies:
   :topic: Red-Black Tree

The Red-Black Tree
==================

Exercise 1
----------

.. avembed:: AV/Development/redBlackTreeColoring.html pe
   :module: RedBlack
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Red-Black Tree Coloring Exercise


Exercise 2
----------

.. avembed:: AV/Development/redBlackTreePRO.html pe
   :module: RedBlack
   :points: 1.0
   :required: True
   :threshold: 0.9
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Red-Black Tree Proficiency Exercise

Red-black trees

A 2-3 tree is a conceptually elegant way to maintain a balanced search
tree. By extending the binary search tree with 3-nodes, we can keep
the tree perfectly balanced at all times. By alternating _splitting_
and _merging_, we can add new elements to the tree without breaking
the invariant.

2-3 trees are, however, rarely used in practice. There are two major
problems with them:

* The code to implement them is quite complicated, even though the
  algorithms are conceptually simple. This is because there are many
  different cases - for example, inserting into the left child, the
  middle child, or the right child of a node all require different
  code, even though the algorithm is conceptually the same. This only
  gets worse when we consider that some of the code also needs to
  handle 4-nodes, which are created temporarily during insertion.

* Most programming languages do not allow an object to change in size.
  However, in a 2-3 tree, we sometimes need to change a 2-node into a
  3-node. Therefore, all node objects must be big enough to store a
  3-node. This wastes a lot of memory.

In short, 2-3 trees are excellent on paper, but annoying to implement
in code - better as slideware than software. In this section, we shall
learn about red-black trees, which take the idea of 2-3 trees and
adapt it to be easier to code. Red-black trees are very efficient, and
widely used in practice. For example, TreeMap in Java and std::map in
C++ are both based on red-black trees.

Think back to binary heaps. A binary heap is conceptually a binary
tree, but it is represented as an array. The algorithms for modifying
binary heaps are conceptually tree algorithms, but we translated them
to work on the array representation of binary heaps.

Red-black trees are based on a similar idea. A red-black tree is
conceptually a 2-3 tree, but we represent it using a binary search
tree. The nodes of the 2-3 tree become nodes in the binary search
tree.

A red-black tree is a binary search tree, but every node in the tree
also has a colour, either red or black. The colour of a node is stored
as a field in the node.

Given a 2-3 tree, here is how we represent it as a red-black tree. A
2-node is just going to become a single node in the tree, coloured
black:

A 3-node is going to turn into two nodes: a parent and its left child.
The parent is coloured black and the child is coloured red.

Here is an example of a 2-3 tree, and how it looks as a red-black
tree.

Notice that, starting from the red-black tree, we can reconstruct the
2-3 tree. A black node with a red left child corresponds to a 3-node,
and any other black node corresponds to a 2-node.

Quiz: Which of the following red-black trees correspond to valid 2-3
trees? That is, is there a 2-3 tree which, when translated, gives the
red-black tree? Don't forget that a valid 2-3 tree must also be
perfectly balanced.

We are now ready to see the invariant for red-black trees. One way to
state the invariant is: *a red-black tree is valid if it corresponds
to a valid 2-3 tree*. However, we are going to state the invariant
more directly, in terms of the colours of the nodes in the red-black
tree. A red-black tree must obey the following rules:

1. The root of the tree must be black. (Because it must correspond to
either a 2-node or a 3-node.)
2. If a node is red, it must be the left child of a black node.
(This is how 3-nodes are represented.)
3. On every path from the root to a null, there must be the
same number of black nodes.

... OR ...

Recall that in a 2-3 tree we have the following properties:

1. The tree is ordered (similar to a BST).  2. Every node is either a
   2-node or a 3-node.  3. On any path from the root to a null, there
   are the same number of nodes.  (This is the perfect balance
   property.)

Since a red-black tree is supposed to be the translation of a valid
2-3 tree, we are going to take the 2-3 tree invariants and translate
them to talk about BSTs. By doing so, we get the following invariants
for a red-black tree:

1. It must be a valid BST.  2.  A red node can only occur as the left
   child of a black node.  (In particular, the root must not be red.)
   3.  On any path from the root to a null, there are the same number
          of _black_ n odes.

example black, partial nodes, etc

We consider null to be black. XXX it correponds to a whole node in the
2-3 tree

