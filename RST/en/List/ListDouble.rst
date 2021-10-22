.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: linked list
   :satisfies: doubly linked list
   :topic: Lists

Doubly Linked Lists (optional)
================================


Doubly Linked Lists
-------------------

The :ref:`singly linked list <linked list> <ListLinked>` allows
for direct access from a list node only to the next node in the list.
A :term:`doubly linked list` allows convenient access from a list node
to the next node and also to the preceding node on the list.
The doubly linked list node accomplishes this in the obvious way by
storing two pointers: one to the node following it (as in the singly
linked list), and a second pointer to the node preceding it.

.. inlineav:: DoublyLinkedList-CON dgm
   :links: DataStructures/DoubleLinkList.css AV/ChalmersGU/CGU-Styles.css
   :scripts: DataStructures/DoubleLinkList.js AV/ChalmersGU/DoublyLinkedList-CON.js
   :align: center

The most common reason to use a doubly linked list is
because it gives an additional possibility to move both forwards
and backwards in the list, and to efficiently add and remove elements from both ends.
Whether a list implementation is doubly or singly linked should
be hidden from the ``List`` class user.

Like our singly linked list implementation, the doubly linked list
implementation makes use of a **header pointer**, but
we also add a **tail pointer** to the end of the list.
 
Here is an implementation for the class variables and the internal list node class.
The only real difference between single linked lists are that we have pointers
to the previous node, and a pointer to the tail of the list.

.. codeinclude:: ChalmersGU/DoubleLinkedList
   :tag: DoubleLinkedListHeader

The main advantage with doubly linked lists are that we can implement more advanced iterators
(ListIterator_ in the Java standard API) that can move forward and backward through a list.
In fact, Java's standard LinkedList_ is implemented as a doubly linked list.

.. _ListIterator: https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
.. _LinkedList: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html


Implementation of the list methods
-----------------------------------

Getting and setting are exactly the same as for normal linked lists, so we don't show them here.

Adding/inserting elements
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Adding elements becomes a bit trickier, because we have to make sure that all pointers are updated correctly.
We get some special cases – when the list is empty, or when we add before the head or after the tail.

.. codeinclude:: ChalmersGU/DoubleLinkedList
   :tag: DoubleLinkedListAdd

Removing elements
~~~~~~~~~~~~~~~~~~~

The same goes for removing elements – we get special cases when we remove the head or the tail.

.. codeinclude:: ChalmersGU/DoubleLinkedList
   :tag: DoubleLinkedListRemove


Full implementation
-----------------------

Here is the full implementation of doubly linked lists.

.. codeinclude:: ChalmersGU/DoubleLinkedList
   :tag: DoubleLinkedList
