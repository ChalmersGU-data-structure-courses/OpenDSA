.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: linked list
   :satisfies: doubly linked list
   :topic: Lists

Doubly Linked Lists
===================


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
because it is easier to implement than a singly linked list.
While the code for the doubly linked implementation is a little longer
than for the singly linked version, it tends to be a bit more
"obvious" in its intention, and so easier to implement and debug.
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

..
  Insert

  inlineav:: dlistInsertCON ss
   :long_name: Doubly Linked List Insert
   :links: DataStructures/DoubleLinkList.css AV/List/dlistCON.css
   :scripts: DataStructures/DoubleLinkList.js AV/List/dlist.js AV/List/dlistInsertCON.js
   :output: show   

..
  Append
  inlineav:: dlistAppendCON ss
   :long_name: Doubly Linked List Append
   :links: DataStructures/DoubleLinkList.css AV/List/dlistCON.css
   :scripts: DataStructures/DoubleLinkList.js AV/List/dlist.js AV/List/dlistAppendCON.js
   :output: show  

..
  Remove
  inlineav:: dlistRemoveCON ss
   :long_name: Doubly Linked List Remove
   :links: DataStructures/DoubleLinkList.css AV/List/dlistCON.css
   :scripts: DataStructures/DoubleLinkList.js AV/List/dlist.js AV/List/dlistRemoveCON.js
   :output: show
   
..
  Prev
  inlineav:: dlistPrevCON ss
   :long_name: Doubly Linked List Prev
   :links: DataStructures/DoubleLinkList.css AV/List/dlistCON.css
   :scripts: DataStructures/DoubleLinkList.js AV/List/dlist.js AV/List/dlistPrevCON.js
   :output: show
