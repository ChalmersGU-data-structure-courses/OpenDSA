.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: queue
   :satisfies: linked queue
   :topic: Lists

Linked Queues
=============

Linked Queues
-------------

The linked queue implementation is an adaptation of the linked list.
The only thing is that we have to add a pointer to the rear node
in the queue, to be able to add new elements efficiently.

.. inlineav:: LinkedQueue-Intro-CON ss
   :long_name: Linked Queue Intro
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedQueue-Intro-CON.js
   :output: show    

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueInit

Enqueueing Elements
-------------------------

.. inlineav:: LinkedQueue-Enqueue-CON ss
   :long_name: Linked Queue Enqueue
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedQueue-Enqueue-CON.js
   :output: show   

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueEnqueue

|

.. avembed:: Exercises/ChalmersGU/LinkedQueue-Enqueue-PRO.html ka
   :long_name: Linked Queue Enqueue Exercise


Dequeueing Elements
-------------------------

.. inlineav:: LinkedQueue-Dequeue-CON ss
   :long_name: Linked Queue Dequeue
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedQueue-Dequeue-CON.js
   :output: show 

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueDequeue

|
   
.. avembed:: Exercises/ChalmersGU/LinkedQueue-Dequeue-PRO.html ka
   :long_name: Linked Queue Dequeue Exercise


Linked Queue, Full Implementation
-------------------------------------

Here is the linked queue class declaration.

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueue


Comparison of Array-Based and Linked Queues
-------------------------------------------

All member functions for both the array-based and linked queue
implementations require constant time.
The space comparison issues are the same as for the equivalent stack
implementations.

Unlike the array-based stack implementation, there is no convenient
way to store two queues in the same array,
unless items are always transferred directly from one queue to the other.

Stack and Queue Summary Questions
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. avembed:: Exercises/List/StackQSumm.html ka
   :long_name: Stack/Queue Summary Exercise
