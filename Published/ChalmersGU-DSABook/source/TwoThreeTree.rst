.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['searching', 'insertion', 'deletion-optional'];</script>

.. _TwoThreeTree:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "TwoThreeTree";ODSA.SETTINGS.MODULE_LONG_NAME = "2-3 Trees";ODSA.SETTINGS.MODULE_CHAPTER = "Search Trees"; ODSA.SETTINGS.BUILD_DATE = "2021-10-31 15:30:52"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Indexing/twoThreeTreeCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :satisfies: 2-3 tree
   :topic: Indexing

2-3 Trees
=========

2-3 Trees
---------

This section presents a data structure called the 2-3 tree. Recall
that in a binary tree, every node has one key and two children.
In a 2-3 tree, there are instead two different kinds of nodes, called
*2-nodes* and *3-nodes*:

* A 2-node has one key and two children, just the same as a node in a
  binary tree.
* A 3-node has *two* keys and *three* children.

Here is an example of a 2-3 tree. In this tree, the root is a 3-node:
it has two keys (18 and 32) and three children. The left child of the
root is a 2-node containing the key 12. (In practice, nodes might
contain key-value pairs, but the figures will show only the keys.)

.. _TTexamp:

.. inlineav:: twoThreedgmCON dgm
   :align: center

   An example of a 2-3 tree.

In order to be valid, a 2-3 tree must obey certain properties.
Firstly, every node must obey a *search tree* property similar to
BSTs:

* For a 2-node with key :math:`k`:
    * All keys in the left subtree must be less than :math:`k`.
    * All keys in the right subtree must be greater than :math:`k`.

* For a 3-node with keys :math:`k_1` and :math:`k_2`:
    * We must have :math:`k_1 < k_2`.
    * All keys in the left subtree must be less than :math:`k_1`.
    * All keys in the middle subtree must be between :math:`k_1` and :math:`k_2`.
    * All keys in the right subtree must be greater than :math:`k_2`.

Secondly, all leaf nodes (``null``) must be at the same level in the
tree. When a tree obeys this property, we say that it is :term:`height
balanced`. So a 2-3 tree is always height balanced.

You can check that the tree above is a valid 2-3 tree: It is made of
2-nodes and 3-nodes, obeys the search tree property and is height balanced.

So far, it seems that we have just taken the idea of a BST and
complicated it by adding another type of node. Why have we done this?
The reason is the :term:`height balance` property. Height balance
ensures that the tree has logarithmic height \[[#log]_], so that search
takes logarithmic time. But also, the algorithms for insertion and
deletion in a 2-3 tree keep the tree height balanced (and also take
logarithmic time). It is not possible in general to keep a BST height
balanced -- we will see that adding 3-nodes is what allows us to do so.

Searching
~~~~~~~~~

Searching for a key in a 2-3 tree is similar to searching in a BST.
Search begins at the root.
If the root does not contain the search key :math:`K`, then the search
progresses to the only subtree that can possibly contain :math:`K`.
The key(s) stored in the root node determine which is the correct
subtree.
For example, if searching for the key 30 in the tree of
Figure :num:`Figure #TTexamp`, we begin with the root node.
Because 30 is between 18 and 33, it can only be in the middle
subtree.
Searching the middle child of the root node yields the desired
record.
If searching for 15, then the first step is again to search the root
node.
Because 15 is less than 18, the first (left) branch is taken.
At the next level, we take the second branch to the leaf node
containing 15.
If the search key were 16, then upon encountering the leaf
containing 15 we would find that the search key is not in the tree.

Insertion
~~~~~~~~~

Insertion into a 2-3 tree is similar to insertion into a BST to the
extent that the new record is placed in the appropriate leaf node.
Unlike BST insertion, a new child is not created to hold the record
being inserted, that is, the 2-3 tree does not grow downward.
The first step is to find the leaf node that would contain the record
if it were in the tree.
If this leaf node contains only one key, then the new record can be
added to that node with no further modification to the tree, as
illustrated in the following visualization.

.. _TTEasyIn:

.. inlineav:: simpleInsertCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: 2-3 Tree Insert Slideshow
   :output: show
   :align: justify

If we insert the new record into a leaf node :math:`L` that already
contains two records, then more space must be created.
Consider the two records of node :math:`L` and the record to be
inserted without further concern for which two
were already in :math:`L` and which is the new record.
The first step is to split :math:`L` into two nodes.
Thus, a new node |---| call it :math:`L'` |---| must be created from
free store.
:math:`L` receives the record with the least of the three keys.
:math:`L'` receives the greatest of the three.
The record with the middle of the three keys is passed up to the
parent node along with a pointer to :math:`L'`.
This is called a :term:`promotion`.
The promoted key is then inserted into the parent.
If the parent currently contains only one record (and thus has only
two children), then the promoted record and the pointer to
:math:`L'` are simply added to the parent node.
If the parent is full, then the split-and-promote process is repeated.
Here is an example of a a simple promotion.

.. _TTPromote:

.. inlineav:: promoteCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: 2-3 Tree Insert Promotion Slideshow
   :output: show

Here is an illustration for what happens when promotions
require the root to split, adding a new level to the tree.
Note that all leaf nodes continue to have equal depth.

.. _TTSplit:

.. inlineav:: splitCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: 2-3 Tree Insert Split Slideshow
   :output: show

Deletion (optional)
~~~~~~~~~~~~~~~~~~~

When deleting a record from the 2-3 tree, there are three cases to
consider.
The simplest occurs when the record is to be removed from a leaf node
containing two records.
In this case, the record is simply removed, and no other nodes are
affected.
The second case occurs when the only record in a leaf node is to be
removed.
The third case occurs when a record is to be removed from an internal
node.
In both the second and the third cases, the deleted record is replaced
with another that can take its place while maintaining the correct
order, similar to removing a node from a BST.
If the tree is sparse enough, there is no such record available that
will allow all nodes to still maintain at least one record.
In this situation, sibling nodes are merged together.
The delete operation for the 2-3 tree is excessively complex and
will not be described further.
Instead, a complete discussion of deletion will be postponed until the
next section, where it can be generalized for a particular variant of
the B-tree.

The 2-3 tree insert and delete routines do not add new nodes at the
bottom of the tree.
Instead they cause leaf nodes to split or merge, possibly causing a
ripple effect moving up the tree to the root.
If necessary the root will split, causing a new root node to be
created and making the tree one level deeper.
On deletion, if the last two children of the root merge,
then the root node is removed and the tree will lose a level.
In either case, all leaf nodes are always at the same level.
When all leaf nodes are at the same level, we say that a tree is
:term:`height balanced`.
Because the 2-3 tree is height balanced, and every internal node has
at least two children, we know that the maximum depth of the tree
is :math:`\log n`.
Thus, all 2-3 tree insert, find, and delete operations require
:math:`\Theta(\log n)` time.

|galles_BTree| for another visualization that will let you construct
and interact with a 2-3 tree.
Actually, this visualization is for a data structure that is more general
than just a 2-3 tree.
To see how a 2-3 would behave, be sure to use the "Max Degree = 3"
setting.
This visualization was written by David Galles of the University of
San Francisco as part of his |galles_AVs| package.

.. |galles_BTree| raw:: html

   <a href="http://www.cs.usfca.edu/~galles/visualization/BTree.html" target="_blank">Click here</a>

.. |galles_AVs| raw:: html

   <a href="http://www.cs.usfca.edu/~galles/visualization/Algorithms.html" target="_blank">Data Structure Visualizations</a>

.. rubric:: Footnotes

.. [#log] A 2-3 tree of height :math:`k` has at least :math:`2^{k-1}`
   leaves, because if the tree only has 2-nodes, it degenerates to the
   shape of a complete binary tree. A 2-3 tree of height :math:`k` has
   at most :math:`3^{k-1}` leaves, because each internal node can have
   at most three children. This implies that the height of a 2-3 tree
   of size :math:`n` is :math:`\Theta(\log n)`.

.. odsascript:: AV/Indexing/twoThreeTreeCON.js
.. odsascript:: AV/Indexing/twoThreedgmCON.js
.. odsascript:: AV/Indexing/simpleInsertCON.js
.. odsascript:: AV/Indexing/promoteCON.js
.. odsascript:: AV/Indexing/splitCON.js
