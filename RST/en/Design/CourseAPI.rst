.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Peter Ljunglöf
   :requires: ADT
   :satisfies: API
   :topic: Course book API

All ADTs Used in This Book
==========================

Here we list all abstract data types that we will introduce in this course book.
Together they form an API (application programming interface) for this book.

Don't worry about them now, they will be explained in detail later on.

Basic Abstract Data Types
-------------------------

These include comparators and comparables.
Note that these are the same as the ones in the standard Java API.

Also note that Python doesn't use comparators and comparables in the same way as Java does.
Instead any class that implements the comparison operators is comparable.

.. codeinclude:: Design/API
   :tag: ComparatorADT

And iterators:

.. codeinclude:: Design/API
   :tag: IteratorADT

As well as iterables and collections:

.. codeinclude:: Design/API
   :tag: CollectionADT

Lists
-----

General lists:

.. codeinclude:: Design/API
   :tag: ListADT

Stacks:

.. codeinclude:: Design/API
   :tag: StackADT

Queues:

.. codeinclude:: Design/API
   :tag: QueueADT

Priority queues:

.. codeinclude:: Design/API
   :tag: PriorityQueueADT

Sets
----

Sets with no internal order:

.. codeinclude:: Design/API
   :tag: SetADT

Sets where the elements are ordered:

.. codeinclude:: Design/API
   :tag: OrderedSetADT

Maps or Dictionaries
--------------------

Maps are also called dictionaries or associative arrays.

Maps with no internal order:

.. codeinclude:: Design/API
   :tag: MapADT

Maps where the keys are ordered:

.. codeinclude:: Design/API
   :tag: OrderedMapADT

Graphs
--------------------

Finally, graphs:

.. codeinclude:: Design/API
   :tag: GraphADT
