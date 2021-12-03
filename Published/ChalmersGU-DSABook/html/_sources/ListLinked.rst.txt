.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['getting-and-setting-values', 'adding-and-removing-nodes', 'adding-a-node', 'removing-a-node', 'complexity-analysis', 'linked-list:-full-code'];</script>

.. _ListLinked:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ListLinked";ODSA.SETTINGS.MODULE_LONG_NAME = "Linked Lists";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-12-03 17:29:11"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/ChalmersGU/CGU-Styles.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: list ADT
   :satisfies: linked list
   :topic: Lists

Linked Lists
============

Linked Lists
------------

In this module we present one of the two traditional implementations
for lists, usually called a :term:`linked list`.
The linked list uses :term:`dynamic memory allocation`,
that is, it allocates memory for new list elements as needed.
The following diagram illustrates the linked list concept.
There are three :term:`nodes <node>` that
are "linked" together.
Each node has two boxes.
The box on the right holds a link to the next node in the list.
Notice that the rightmost node has a diagonal slash through its link
box, signifying that there is no link coming out of this box.

.. inlineav:: LinkedList-Overview-CON dgm
   :long_name: Linked List Overview
   :align: center

Because a list node is a distinct object (as opposed to simply a cell
in an array), it is good practice to make a separate list node class.
This can either be a standalone class or an inner class,
and both have their advantages and disadvantages.

The list built from such nodes is called a :term:`singly linked list`,
or a :term:`one-way list`, because each list node
has a single pointer to the next node on the list.

.. inlineav:: LinkedList-Iteration-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked List Slideshow 1
   :output: show

Our class for linked lists contains two private variables,
one pointer to the head of the list, and
a variable storing the number of elements.
(This second variable is in theory unnecessary, but it improves the efficiency
of getting the list size).

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedListVars

Here is our implementation for list nodes, an inner private class ``Node``.
Objects in the ``Node`` class contain a field ``elem`` to
store the element value, and a field ``next`` to store a pointer to
the next node on the list.

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedListNode


Getting and setting values
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If we want to get or set the value at a certain index,
we simply iterate through the nodes in sequence until we get to the node we want.

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedListGetSet



Adding and removing nodes
-----------------------------

However, if we want to add or remove nodes,
there is a problem with using a pointer to the ``current`` node.

.. inlineav:: LinkedList-Problems-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked List Add/Remove Problems
   :output: show

So, using a ``current`` pointer, it is possible to add and remove nodes, using some complicated coding.
But this does not work for the very last node!
There are several possible ways to deal with this problem.
One is to always have an empty node (a "dummy node") at the very end of the list,
but this will increase memory usage.

Another simple solution is to have a pointer to the node *before*
the current node.
This is the solution we will adopt.


Adding a Node
-----------------------

.. inlineav:: LinkedList-Add-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked List Add Slideshow
   :output: show
   

Here are some special cases for linked list insertion:
Inserting at the beginning of a list, and appending at the end.

.. inlineav:: LinkedList-AddSpecial-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked List Add Special Cases Slideshow
   :output: show


Here's the code for addition.

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedListAdd


Here's an exercise for adding a value to a linked list.

.. avembed:: Exercises/ChalmersGU/LinkedList-Add-PRO.html ka
   :module: ListLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked List Add Exercise



Removing a Node
-----------------------

.. inlineav:: LinkedList-Remove-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked List Remove Slideshow
   :output: show

Here's the code for deletion:

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedListRemove


And here's an exercise.

.. avembed:: Exercises/ChalmersGU/LinkedList-Remove-PRO.html ka
   :module: ListLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked List Remove Exercise

   
Complexity analysis
------------------------------------------------

Locating a certain position :math:`i` in the list requires :math:`i` steps.
The worst case is if we want to go to the last node, so the
time complexity for above all operations is :math:`\Theta(n)`.

This is much worse than the :ref:`array-based list <ListArray>`,
where these operations are :math:`\Theta(1)`.
So are linked lists totally useless?
No! But they don't work well with our current List interface.

To make linked lists useful, we need an enhanced iterator interface,
where we can move forwards and backwards in the list, and add/remove nodes
through this enhanced iterator.
In the standard Java API, this kind of iterator is called a ListIterator_,
which is part of Java's standard LinkedList_.

.. _ListIterator: https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
.. _LinkedList: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html


Linked List: Full code
------------------------------------------------

Finally, here is the full source code for the class ``LinkedList``.

.. codeinclude:: ChalmersGU/API/LinkedList
   :tag: LinkedList


.. odsascript:: AV/ChalmersGU/LinkedList-Overview-CON.js
.. odsascript:: AV/ChalmersGU/LinkedList-Iteration-CON.js
.. odsascript:: AV/ChalmersGU/LinkedList-Problems-CON.js
.. odsascript:: AV/ChalmersGU/LinkedList-Add-CON.js
.. odsascript:: AV/ChalmersGU/LinkedList-AddSpecial-CON.js
.. odsascript:: AV/ChalmersGU/LinkedList-Remove-CON.js
