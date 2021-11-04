.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['what-is-a-list', 'collections', 'defining-the-list-adt'];</script>

.. _ListADT:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ListADT";ODSA.SETTINGS.MODULE_LONG_NAME = "The List ADT";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-11-04 17:34:53"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/ChalmersGU/CGU-Styles.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :requires: ADT
   :satisfies: list ADT
   :topic: Lists

The List ADT
============

What is a List?
---------------

We all have an intuitive understanding of what we mean by a "list".
We want to turn this intuitive understanding into a concrete data
structure with implementations for its operations.
The most important concept related to lists is that of
:term:`position`.
In other words, we perceive that there is a first element in the list,
a second element, and so on.
So, define a :term:`list` to be a finite, ordered
sequence of data items known as :term:`elements <element>`.
This is close to the mathematical concept of
a :term:`sequence`.

"Ordered" in this definition means that each element has a
position in the list.
So the term "ordered" in this context does **not** mean that the list
elements are sorted by value.
(Of course, we can always choose to sort the elements on the list if
we want; it's just that keeping the elements sorted is not an inherent
property of being a list.)

Each list element must have some data type.
In the simple list implementations discussed in this chapter, all
elements of the list are usually assumed to have the same data type,
although there is no conceptual objection to lists whose elements have
differing data types if the application requires it.
The operations defined as part of the list :term:`ADT` 
depend on the elemental :term:`data type`.
For example, the list ADT can be used for lists of integers, lists of
characters, lists of payroll records, even lists of lists.

A list is said to be :term:`empty` when it contains no elements.
The number of elements currently stored is called the
:term:`length` of the list.
The beginning of the list is called the :term:`head`,
the end of the list is called the :term:`tail`.

We need some notation to show the contents of a list,
so we will use the same angle bracket notation that is normally used
to represent :term:`sequences <sequence>`.
To be consistent with standard array indexing, the first position
on the list is denoted as 0.
Thus, if there are :math:`n` elements in the list, they are given
positions 0 through :math:`n-1` as
:math:`\langle\ a_0,\ a_1,\ ...,\ a_{n-1}\ \rangle`.
The subscript indicates an element's position within the list.
Using this notation, the empty list would appear as
:math:`\langle\ \rangle`.


Collections
------------------------

There are some properties that lists share with many other data structures
(some of them will be introduced later in this course).
Then it's good habit to extract the most important common properties into
a more general kind of ADT, which we will call collections.

A collection contains a number of elements, and it supports only two things:
we can inquire how many elements it contains, and
we can iterate through all elements, one at the time (i.e., it is Iterable).

.. codeinclude:: ChalmersGU/API
   :tag: CollectionADT

Note that this very interface will not be implemented as it is, but instead
we will use this as a base interface that we extend in different ways,
e.g., for lists or sets or priority queues.


Defining the List ADT
-----------------------

Now, back to the lists that we started talking about. 

What basic operations do we want our lists to support?
Our common intuition about lists tells us that a list should be able
to grow and shrink in size as we insert and remove elements.
We should be able to insert and remove elements from anywhere in
the list.
We should be able to gain access to any element's value,
either to read it or to change it.
Finally, we should be able to know the size of the list, and
to iterate through the elements in the list – i.e., the list should be a Collection.

Now we can define the ADT for a list object in terms of a set
of operations on that object.
We will use an interface to formally define the list ADT.
``List`` defines the member functions that any list
implementation inheriting from it must support, along with their
parameters and return types.

True to the notion of an ADT, an interface
does not specify how operations are implemented.
Two complete implementations are presented later
(array-based lists and linked lists),
both of which use the same list ADT to define their operations.
But they are  considerably different in approaches and in their
space/time tradeoffs.

The code below presents our list ADT.
The comments given with each member function describe what it is
intended to do.
However, an explanation of the basic design should help make this
clearer.
There are four main operations we want to support:

- ``get(i)`` to read the value of an element at the given position ``i``
- ``set(i,x)`` to set the value at position ``i`` to value ``x``
- ``add(i,x)`` to add (insert) an element ``x``, at position ``i``, thus increasing the size of the list
- ``remove(i)`` to remove the element at position ``i``, thus decreasing the size of the list

Apart from these four, we also want to be able to loop through the list elements in order
(i.e., an ``iterator`` over the elements).

.. codeinclude:: ChalmersGU/API
   :tag: ListADT

|

.. inlineav:: ListADT-Positions-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: List ADT Positions Slideshow
   :output: show

The ``List`` member functions allow you to build a list with elements
in any desired order, and to access any desired position in the list.

A list can be iterated through as follows:

.. codeinclude:: Lists/ListTest
   :tag: listiterNew1

But both Java and Python has syntactic sugar for iterators,
so the same iteration can be written like this:

.. codeinclude:: Lists/ListTest
   :tag: listiterNew2

In this example, each element of the list in turn is stored
in ``it``, and passed to the ``doSomething`` function.
The loop terminates when the current position reaches the end of the
list.

The list class declaration presented here is just one of
many possible interpretations for lists.
Our list interface provides most of the operations that one
naturally expects to perform on lists and serves to illustrate the
issues relevant to implementing the list data structure.
As an example of using the list ADT, here is a function to
return ``true`` if there is an occurrence of a given element in the
list, and ``false`` otherwise.
The ``find`` method needs no knowledge about the specific list
implementation, just the list ADT.

.. codeinclude:: Lists/ListTest
   :tag: listfind

There are two standard approaches to implementing lists, the
:ref:`array-based list <ListArray>`, and the
:ref:`linked list  <ListLinked>`.


.. odsascript:: AV/ChalmersGU/ListADT-Positions-CON.js
