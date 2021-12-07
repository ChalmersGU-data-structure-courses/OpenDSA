.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['queue-terminology-and-implementation', 'the-circular-queue', 'array-based-queue-implementation', 'enqueueing-an-element', 'dequeueing-an-element', 'resizing-the-internal-array', 'array-based-queue-practice-exercises', 'array-based-queues-full-implementation'];</script>

.. _Queue:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "Queue";ODSA.SETTINGS.MODULE_LONG_NAME = "Array-Based Queues";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-12-05 15:41:43"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/List/aqueueCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: list ADT
   :satisfies: queue; array-based queue
   :topic: Lists

Array-Based Queues
=========================

Queue Terminology and Implementation
------------------------------------

Like the stack, the :term:`queue` is a list-like structure that
provides restricted access to its elements.
Queue elements may only be inserted at the back (called an
:term:`enqueue` operation) and removed from the
front (called a :term:`dequeue` operation).
Queues operate like standing in line at a movie theater ticket
counter.
If nobody cheats, then newcomers go to the back of the line.
The person at the front of the line is the next to be served.
Thus, queues release their elements in order of arrival.
In Britain, a line of people is called a "queue",
and getting into line to wait for service is called "queuing up".
Accountants have used queues since long before the
existence of computers.
They call a queue a "FIFO" list, which stands for
"First-In, First-Out".
Here is a sample queue ADT.
This section presents two implementations for queues:
the array-based queue and the linked queue.

.. codeinclude:: ChalmersGU/API/API
   :tag: QueueADT


Array-Based Queues
----------------------

The array-based queue is somewhat tricky to implement effectively.
A simple conversion of the array-based list implementation is not
efficient.

.. inlineav:: aqueueFirstCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Array-based Queue Positions Slideshow
   :output: show

|

.. inlineav:: aqueueDriftCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Array-based Queue Drift Slideshow
   :output: show

|

.. inlineav:: aqueueBadCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Array-based Queue Bad Representation Slideshow
   :output: show


The Circular Queue
---------------------

.. inlineav:: aqueueCircularCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Circular Array-based Queue Slideshow
   :output: show

|

.. inlineav:: aqueueEmptyCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Empty Circular Array-based Queue Slideshow
   :output: show

If the value of ``front`` is fixed, then :math:`n+1` different
values for ``rear`` are needed to distinguish among the :math:`n+1`
states.
However, there are only :math:`n` possible values for ``rear`` unless
we invent a special case for, say, empty queues.
This is an example of the :term:`Pigeonhole Principle`.
The Pigeonhole Principle states that, given :math:`n` pigeonholes
and :math:`n+1` pigeons, when all of the pigeons go into the holes we
can be sure that at least one hole contains more than one pigeon.
In similar manner, we can be sure that two of the :math:`n+1` states
are indistinguishable by the :math:`n` relative values of ``front``
and ``rear``.
We must seek some other way to distinguish full from empty queues.

One obvious solution is to keep an explicit count of the number of
elements in the queue, or at least a Boolean variable that indicates
whether the queue is empty or not.
Another solution is to make the array be of size :math:`n+1`,
and only allow :math:`n` elements to be stored.
A third solution is to set ``front`` and ``rear`` to –1 when the queue becomes empty.
Which of these solutions to adopt is purely a matter of the
implementor's taste in such affairs.
Our choice here is to keep an explicit count of the number of elements,
in the variable ``queueSize``, because this will make the code more similar
to our list and stack implementations.


Array-based Queue Implementation
-------------------------------------

In this implementation, the front of the queue is defined to be toward
the lower numbered positions in the array (in the counter-clockwise
direction in the circular array), and the rear is
defined to be toward the higher-numbered positions.
Thus, ``enqueue`` increments the rear pointer (modulus the size of the internal array),
and ``dequeue`` increments the front pointer.

.. codeinclude:: ChalmersGU/API/DynamicArrayQueue
   :tag: DynamicArrayQueueInit

Implemening the member functions is mostly straightforward.

Enqueueing an element
~~~~~~~~~~~~~~~~~~~~~~

When enqueueing, we increase the ``rear`` pointer
(modulo the size of the internal array to make it circular).

.. codeinclude:: ChalmersGU/API/DynamicArrayQueue
   :tag: DynamicArrayQueueEnqueue


Dequeueing an element
~~~~~~~~~~~~~~~~~~~~~~

When dequeueing, we increase the ``front`` pointer
(modulo the size of the internal array).

.. codeinclude:: ChalmersGU/API/DynamicArrayQueue
   :tag: DynamicArrayQueueDequeue

Resizing the internal array
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

When we resize the internal array, we cannot keep the positions of the elements.
If the queue is wrapped around (i.e., if ``rear < front``) then
we might end up in a large gap in the middle of the queue.

Instead we reset the ``front`` and ``rear`` pointers so that we copy the first
queue element to position 0 of the new array, the second to position 1, etc.
Apart from that, the implementation is similar to the one for lists and queues.

.. codeinclude:: ChalmersGU/API/DynamicArrayQueue
   :tag: DynamicArrayQueueEnqueue


Array-based Queue Practice Exercises
--------------------------------------

.. avembed:: Exercises/ChalmersGU/AqueueEnqueuePRO.html ka
   :module: Queue
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Array-based Queue Enqueue Exercise


.. avembed:: Exercises/ChalmersGU/AqueueDequeuePRO.html ka
   :module: Queue
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Array-based Queue Dequeue Exercise


Array-based Queues, Full Implementation
-----------------------------------------

Here is an array-based queue implementation.

.. codeinclude:: ChalmersGU/API/DynamicArrayQueue
   :tag: DynamicArrayQueue

.. odsascript:: AV/List/aqueueFirstCON.js
.. odsascript:: AV/List/aqueueDriftCON.js
.. odsascript:: AV/List/aqueueBadCON.js
.. odsascript:: DataStructures/CircularQueue.js
.. odsascript:: AV/List/aqueueCircularCON.js
.. odsascript:: AV/List/aqueueEmptyCON.js
