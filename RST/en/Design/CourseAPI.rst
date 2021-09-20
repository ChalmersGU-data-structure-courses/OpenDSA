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

.. `Duck Typing`: https://en.wikipedia.org/wiki/Duck_typing

Basic Abstract Data Types
-------------------------

These include comparators and comparables.

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

Comparison with the standard Java API
----------------------------------------

The standard Java API can be found here (this is Java SE 8):
https://docs.oracle.com/javase/8/docs/api/.
Here is a quick comparison beteween the interfaces we have defined above,
and the most similar ones that are defined the standard Java API:

- **Iterable, Collection, List**: These interfaces are the same as
  Iterable_, Collection_ and List_ in the standard Java API, but with less methods.

- **Stack**: The main difference is that define it as an interface
  (because there are several possible implementations),
  but it's a single class Stack_ in the Java standard.

- **Queue**: The standard API has a similar interface Queue_ which uses different method names.

- **PriorityQueue**: We define priority queues as an interface
  (because there are several possible implementations),
  but in the standard API it's a single class PriorityQueue_ that implements
  their Queue_ interface. So the method names are different too.

- **Set**: This interface is the same as Set_ in the standard API, but with less methods.

- **OrderedSet**: The standard API calls this a SortedSet_, and they have different method names.

- **Map**: The standard API interface Map_ is very similar, but uses some different method names.

- **OrderedMap**: The standard API calls it SortedMap_ instead, and just as for ordered sets they use different method names.

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
