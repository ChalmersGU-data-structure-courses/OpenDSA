.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['local', 'passing-down-information', 'binary-tree-set-depth-exercise', 'collect-and-return', 'binary-tree-check-sum-exercise', 'binary-tree-leaf-nodes-count-exercise', 'binary-tree-sum-nodes-exercise', 'combining-information-flows', 'binary-tree-check-value-exercise', 'combination-problems', 'binary-tree-height-exercise', 'binary-tree-get-difference-exercise', 'binary-tree-has-path-sum-exercise'];</script>

.. _BinaryTreeInfFlw:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "BinaryTreeInfFlw";ODSA.SETTINGS.MODULE_LONG_NAME = "Information Flow in Recursive Functions";ODSA.SETTINGS.MODULE_CHAPTER = "Binary Trees"; ODSA.SETTINGS.BUILD_DATE = "2021-12-03 17:29:11"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Binary/WriteTrav.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Sally Hamouda and Cliff Shaffer
   :requires: mistakes in recursive tree traversal
   :satisfies: recursive function information flow
   :topic: Advanced Recursion

Information Flow in Recursive Functions
=======================================

Information Flow in Recursive Functions
---------------------------------------

Handling information flow in a recursive function can be a challenge.
In any given function, we might need to be concerned with either or
both of:

 #. Passing down the correct information needed by the function to do
    its work,
 #. Returning (passing up) information to the recursive function's
    caller.

Any given problems might need to do either or both.
Here are some examples and exercises.

Local
~~~~~

Local traversal involves going to each node in the tree to do some
operation.
Such functions need no information from the parent (other than a
pointer to the current node), and pass no information back.
Examples include preorder traversal and incrementing the value of
every node by one.

Passing Down Information
~~~~~~~~~~~~~~~~~~~~~~~~

Slightly more complicated is the situation where every node needs the
same piece of information to be passed to it.
An example would be incrementing the value for all nodes by some
amount.
In this case, the value parameter is simply passed on
unchanged in all recursive calls.

Many functions need information that changes from node to node.
A simple example is a function to set the value for each node of the
tree to be its depth.
In this case, the depth is passed as a parameter to the function, and
each recursive call must adjust that value (by adding one).


Binary Tree Set Depth Exercise
------------------------------

.. extrtoolembed:: 'Binary Tree Set Depth Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Set Depth Exercise
   :learning_tool: code-workout
   :workout_id: 66


Collect-and-return
------------------

Collect-and-return requires that we communicate information back up
the tree to the caller.
Simple examples are to count the number of nodes in a tree,
or to sum the values of all the nodes.

.. topic:: Example

   Consider the problem of counting (and returning) the number of
   nodes in a binary tree.
   The key insight is that the total count for any (non-empty) subtree is
   one for the root plus the counts for the left and right subtrees.
   Where do left and right subtree counts come from?
   Calls to function ``count`` on the subtrees will compute this for
   us.
   Thus, we can implement ``count`` as follows.

   .. codeinclude:: Binary/RecTutor
      :tag: EffCnt

   The following solution is correct but inefficient as it does
   redundant checks on the left and the right child of each visited
   node.
	    
   .. codeinclude:: Binary/RecTutor
      :tag: IneffCnt

When you write a recursive function that returns a value,
such as counting the number of nodes in the subtree,
you have to make sure that your function actually returns a value.
A common mistake is to make a recursive call and not capture the
returned value.
Another common mistake is to not return a value.

.. inlineav:: BinaryTreeMistakesCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Binary Tree Common Mistakes Slideshow
   :output: show


Binary Tree Check Sum Exercise
------------------------------

.. extrtoolembed:: 'Binary Tree Check Sum Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Check Sum Exercise
   :learning_tool: code-workout
   :workout_id: 71


Binary Tree Leaf Nodes Count Exercise
-------------------------------------

.. extrtoolembed:: 'Binary Tree Leaf Nodes Count Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Leaf Nodes Count Exercise
   :learning_tool: code-workout
   :workout_id: 72


Binary Tree Sum Nodes Exercise
------------------------------

.. extrtoolembed:: 'Binary Tree Sum Nodes Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Sum Nodes Exercise
   :learning_tool: code-workout
   :workout_id: 68


Combining Information Flows
---------------------------

Many functions require both that information be passed in, and that
information be passed back.
Let's start with a relatively simple case.
If we want to check if some node in the tree has a
particular value, that value has to be passed down, and the count has
to be passed back up.
The downward flow is simple, as the value being checked for never
changes.
The information passed up has the simple collect-and-return style:
Return True if and only if one of the children returns True.


Binary Tree Check Value Exercise
--------------------------------

.. extrtoolembed:: 'Binary Tree Check Value Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Check Value Exercise
   :learning_tool: code-workout
   :workout_id: 65

Combination Problems
--------------------

Slightly more complicated problems combine what we have seen so far.
Information passing down the tree changes from node to node.
Data passed back up the tree uses the collect-and-return paradigm.


Binary Tree Height Exercise
---------------------------

.. extrtoolembed:: 'Binary Tree Height Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Height Exercise
   :learning_tool: code-workout
   :workout_id: 70

Binary Tree Get Difference Exercise
-----------------------------------

.. extrtoolembed:: 'Binary Tree Get Difference Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Get Difference Exercise
   :learning_tool: code-workout
   :workout_id: 73

Binary Tree Has Path Sum Exercise
---------------------------------

.. extrtoolembed:: 'Binary Tree Has Path Sum Exercise'
   :module: BinaryTreeInfFlw
   :long_name: Binary Tree Has Path Sum Exercise
   :learning_tool: code-workout
   :workout_id: 67

.. odsascript:: AV/Binary/BinaryTreeMistakesCON.js
