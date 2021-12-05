.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['sorting', 'finding-the-top-100-items'];</script>

.. _PriorityQueue:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "PriorityQueue";ODSA.SETTINGS.MODULE_LONG_NAME = "Priority queues";ODSA.SETTINGS.MODULE_CHAPTER = "Priority Queues"; ODSA.SETTINGS.BUILD_DATE = "2021-12-05 12:47:14"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


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

In this chapter, we will see how to implement a priority queue so that
both adding and removing the minimum take :math:`O(\log n)` time.

.. codeinclude:: ChalmersGU/API/API
   :tag: PriorityQueueADT

Note that this API assumes that the priority queue orders the elements in *ascending* order.
There is also the possibility of ordering in descending order --
that kind of queue is called a *maximum priority queue*.
If you have a minimum priority queue, it's straightforward to turn it into
a maximum priority queue.

Now let's look at a couple of applications of priority queues.

Sorting
-------

We can use a priority queue to make an efficient sorting algorithm. To
sort a list of items:

* First create an empty priority queue, and add all the items to it.
* Then repeatedly find and remove the smallest item. The items will
  come out in ascending order.

Here is an implementation of this algorithm in code:

.. codeinclude:: ChalmersGU/Sorting/PQSort
   :tag: PQSort

What is the time complexity of this algorithm? Well, for an input list
of size :math:`n`, the algorithm calls ``add`` :math:`n` times and
``removeMin`` :math:`n` times.  In a binary heap, ``add`` and
``removeMin`` both take :math:`O(\log n)` time.  Therefore, the total
runtime is :math:`O(n \log n)` -- as efficient as any of the sorting
algorithms we have seen so far!

Finding the top 100 items
-------------------------

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
day. At any point, we want to be able to find the 100 highest-valued
transactions *so far* today. How can we do this?

We could still use the sorting approach, but we would need to sort the
list of transactions *every time* we wanted to find the 100 top
transactions. This may be prohibitively expensive if there are a lot
of transactions: it takes :math:`O(n \log n)` time every time we do it.

We can do better with the help of a priority queue. The idea is to
have a priority queue that holds the *100 highest-value transactions*
only. Whenever a new transaction comes in, we need to update the
priority queue accordingly:

1. If the priority queue has fewer than 100 transactions (i.e. there
   have been fewer than 100 transactions so far today), then add the
   new transaction to the priority queue.
2. Otherwise, if the new transaction is *greater in value than the
   lowest-valued of the top 100 transactions*, then remove that
   transaction and add the new transaction.
3. Otherwise, don't add the new transaction to the priority queue
   (it's not in the top 100).

Notice that in step 2, we are comparing the new transaction to the
*lowest-valued* of the top 100 transactions -- if the transactions
are ordered by value, then this transaction can be found by calling
``getMin``, and removed using ``removeMin``. So this algorithm can
be implemented efficiently using a priority queue.

In fact, we can simplify these three steps into two steps. First, we
add the new transaction to the priority queue. This might make the
priority queue grow to 101 transactions. If so, we remove the
lowest-valued transaction. Here it is in code:

.. codeinclude:: Binary/OnlineTopK
   :tag: OnlineTopK

What is the complexity of ``add``? Well, in fact it takes constant
time, because the priority queue has a constant maximum size of 100
elements. If we generalize this problem to keeping track of the top
:math:`k` transactions, then the complexity of ``add`` is
:math:`O(\log k)`.

