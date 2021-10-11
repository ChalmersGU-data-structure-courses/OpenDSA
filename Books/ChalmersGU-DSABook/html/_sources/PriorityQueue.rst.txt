.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['sorting', 'finding-the-top-k-items'];</script>

.. _PriorityQueue:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "PriorityQueue";ODSA.SETTINGS.MODULE_LONG_NAME = "Priority queues";ODSA.SETTINGS.MODULE_CHAPTER = "Priority Queues"; ODSA.SETTINGS.BUILD_DATE = "2021-10-11 15:14:50"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer, Nick Smallbone
   :requires: 
   :satisfies: priority queue
   :topic: Priority queues

Priority queues
===============

Priority queues
---------------

So far we have seen two :term:`ADTs <adt>` that represent a collection
of objects, and support adding and removing objects:

* :term:`Stacks <stack>`, where the object removed is always the one
  *most recently* inserted (:term:`LIFO`).
* :term:`Queues <queue>`, where the object removed is always the one
  *first* inserted (:term:`FIFO`).

There are many situations, both in real life and in computing
applications, where we wish to choose the next "most important"
from a collection of people, tasks, or objects.
For example, doctors in a hospital emergency room often choose to see
next the "most critical" patient rather than the one who arrived
first.
When scheduling programs for execution in a multitasking
operating system, at any given moment there might be several programs
(usually called :term:`jobs <job>`) ready to run.
The next job selected is the one with the highest
:term:`priority`. 
Priority is indicated by a particular value associated with the job
(and might change while the job remains in the wait list).

When a collection of objects is organized by importance or priority,
we call this a :term:`priority queue`. A priority queue supports the
following operations:

* adding a new object to the priority queue
* removing the *smallest* object from the priority queue.

As we shall see in the next section, it is possible to implement a
priority queue so that both adding and removing take
:math:`O(n \log n)` time.

.. codeinclude:: Binary/PriorityQueue
   :tag: PriorityQueue

.. raw:: html

   <a id="todo0"></a>

.. TODO::
   Add max priority queues, and the version with an explicit priority
   (and/or comparator?)

Sorting
-------

We can use a priority queue to make an efficient sorting algorithm. To
sort a list of items:

* First create an empty priority queue, and add all the items to it.
* Then repeatedly find and remove the smallest item. The items will
  come out in ascending order.

Here is an implementation of this algorithm in code:

.. codeinclude:: Sorting/PQsort
   :tag: PQsort

What is the time complexity of this algorithm? Well, for an input list
of size :math:`n`, the algorithm calls ``add`` :math:`n` times and
``removeMin`` :math:`n` times.  In a binary heap, ``add`` and
``removeMin`` both take :math:`O(\log n)` time.  Therefore, the total
runtime is :math:`O(n \log n)` -- as efficient as any of the sorting
algorithms we have seen so far!

Finding the top k items
-----------------------

Suppose that we are running a bank. Every day, every transaction that
occurs at the bank is recorded in a list. When the bank closes at the
end of the day, we would like to find the 100 highest-valued
transactions from that day. How can we do it?

One way is to use sorting. If we store the transactions in an array
and sort it by value, then the highest-value transactions will be at
the end of the array. If there are *n* transactions in total, then
transactions number :math:`n-100\ldots n-1` are the ones we need. If
we use an efficient sorting algorithm, this will take
:math:`O(n \log n)` time. (More generally, this gives us an algorithm
for finding the largest :math:`k` items in a list of :math:`n` items, in
:math:`O(n \log n)` time.)

Now suppose that we want to monitor the transactions *throughout* the
day. We want to have a screen, continuously updating, which shows the
100 highest-valued transactions *so far* today. How can we do this?

The sorting approach is no longer suitable. Every time a new
transaction comes in, we would need to sort the entire list of
transactions. If there are :math:`n` transactions in total,
then we would sort the transaction list :math:`n` times, and
this would take :math:`O(n^2 \log n)` time in total. Not good!

What we would like is a data structure representing a collection of
items, and supporting the following operations:

* ``add(x)``: add the item ``x`` (in this case a transaction)
  to the collection
* ``topK()``: return the top :math:`k` items.

.. raw:: html

   <a id="todo1"></a>

.. TODO::
   Simplify this. Maybe stick to the bank example without generalising.

Here, :math:`k` is a value which is chosen when we initialise the data
structure (in this example, :math:`k` is 100). Here is how the API
might look in code:

.. codeinclude:: Binary/OnlineTopKTypes
   :tag: OnlineTopKTypes

blah blah blah blah

.. codeinclude:: Binary/OnlineTopK
   :tag: OnlineTopK

