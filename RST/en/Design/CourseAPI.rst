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

Don't worry about understanding the different interfaces now, they will be explained in detail later on.

**Important Java note**:
There are many similarities, but also some differences, between the API below and
the interfaces and classes in the "standard" Java API.
For more details about the differences, please see the end of this module.

**Important Python note**:
Python doesn't make use of abstract classes (interfaces) in the same way as Java does.
Instead they use a concept called `Duck Typing`_, which means that it's enough to just
implement the required methods – you don't need a formal interface.
However, in this course we will still pretend that there are interfaces, in Python too.
So, we will define classes which act as interfaces, and call them abstract classes.

.. _`Duck Typing`: https://en.wikipedia.org/wiki/Duck_typing

Basic Abstract Data Types
-------------------------

These include comparables.

.. codeinclude:: ChalmersGU/BaseAPI
   :tag: ComparableADT

And iterators and iterables:

.. codeinclude:: ChalmersGU/BaseAPI
   :tag: IteratorADT, IterableADT

As well as collections:

.. codeinclude:: ChalmersGU/API
   :tag: CollectionADT

Lists
-----

General lists:

.. codeinclude:: ChalmersGU/API
   :tag: ListADT

Stacks:

.. codeinclude:: ChalmersGU/API
   :tag: StackADT

Queues:

.. codeinclude:: ChalmersGU/API
   :tag: QueueADT

Priority queues:

.. codeinclude:: ChalmersGU/API
   :tag: PriorityQueueADT

Sets
----

Sets with no internal order:

.. codeinclude:: ChalmersGU/API
   :tag: SetADT

Sets where the elements are sorted:

.. codeinclude:: ChalmersGU/API
   :tag: SortedSetADT

Maps or Dictionaries
--------------------

Maps are also called dictionaries or associative arrays.

Maps with no internal order:

.. codeinclude:: ChalmersGU/API
   :tag: MapADT

Maps where the keys are sorted:

.. codeinclude:: ChalmersGU/API
   :tag: SortedMapADT

Graphs
--------------------

Finally, graphs:

.. codeinclude:: ChalmersGU/API
   :tag: GraphADT

Comparison with the standard Java API
----------------------------------------

The standard Java API can be found here (this is Java SE 8):
https://docs.oracle.com/javase/8/docs/api/.
Here is a quick comparison beteween the interfaces we have defined above,
and the most similar ones that are defined the standard Java API:

- **Iterable, Collection, List**: These interfaces are the same as
  Iterable_, Collection_ and List_ in the standard Java API, but with fewer methods.

- **Stack**: The main difference is that define it as an interface
  (because there are several possible implementations),
  but it's a single class Stack_ in the Java standard.

- **Queue**: The standard API has an interface Queue_ which uses different method names.

- **PriorityQueue**: We define priority queues as an interface
  (because there are several possible implementations),
  but in the standard API it's a single class PriorityQueue_ that implements
  their Queue_ interface. So the method names are different too.

- **Set, SortedSet, Map, SortedMap**: These interface are the same as
  Set_, SortedSet_, Map_ and SortedMap_ in the standard API, but with fewer methods.
  Also, some methods are simpler than the corresponding ones in the standard API.

- **Graph**: There is no interface (or class) for graphs in the standard Java API.

.. _Iterable: https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html
.. _Collection: https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html
.. _List: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
.. _Stack: https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html
.. _Queue: https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
.. _PriorityQueue: https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
.. _Set: https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
.. _SortedSet: https://docs.oracle.com/javase/8/docs/api/java/util/SortedSet.html
.. _Map: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
.. _SortedMap: https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html
